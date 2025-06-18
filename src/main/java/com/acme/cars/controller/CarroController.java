package com.acme.cars.controller;

import com.acme.cars.exception.RecursoNaoEncontradoException;
import com.acme.cars.model.Carro;
import com.acme.cars.payload.CriteriaRequest;
import com.acme.cars.service.InterfaceCarroService;
import com.acme.cars.service.CsvService;
import com.acme.cars.specification.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
  Controlador REST para operações relacionadas a carros.
  Lida com requisições HTTP e orquestra as chamadas para o serviço de carro.
*/
@RestController
@RequestMapping("/api/carros")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class CarroController {
    private final InterfaceCarroService carroService;
    private final CsvService csvService;

    /**
     * Realiza uma busca de carros com base em critérios fornecidos nos cabeçalhos da requisição.
     * Utiliza o padrão Strategy para construir a query de busca.
     * modelo Opcional: modelo do carro.
     * fabricante Opcional: fabricante do carro.
     * pais Opcional: país de origem do carro.
     * cor Opcional: cor do carro.
     * ano Opcional: ano de fabricação do carro.
     * return ResponseEntity contendo a lista de carros encontrados.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Carro>> search(
            @RequestHeader(value = "modelo", required = false) Optional<String> modelo,
            @RequestHeader(value = "fabricante", required = false) Optional<String> fabricante,
            @RequestHeader(value = "pais", required = false) Optional<String> pais,
            @RequestHeader(value = "cor", required = false) Optional<String> cor,
            @RequestHeader(value = "ano", required = false) Optional<Integer> ano) {

        List<CarroSpecification> specifications = new ArrayList<>();

        // Adiciona especificações à lista apenas se o critério estiver presente.
        // Nomes significativos para os parâmetros e variáveis.
        // Evitando Ifs aninhados.
        modelo.ifPresent(m -> specifications.add(new CarroModeloSpecification(m)));
        fabricante.ifPresent(f -> specifications.add(new CarroFabricanteSpecification(f)));
        pais.ifPresent(p -> specifications.add(new CarroPaisSpecification(p)));
        cor.ifPresent(c -> specifications.add(new CarroCorSpecification(c)));
        ano.ifPresent(a -> specifications.add(new CarroAnoSpecification(a)));

        List<Carro> searchResult = carroService.search(specifications); // Chamada ao metodo search com especificações
        return ResponseEntity.ok(searchResult);
    }

    /**
     * Lista todos os carros com suporte a paginação.
     * page Número da página (padrão: 0).
     * size Tamanho da página (padrão: 99999).
     * return ResponseEntity contendo a lista paginada de carros e o total de itens no cabeçalho.
     */
    @GetMapping
    public ResponseEntity<List<Carro>> listarTodos(
            @RequestHeader(value = "page", defaultValue = "0") int page,
            @RequestHeader(value = "size", defaultValue = "99999") int size) {
        log.info("Requisição para listar carros - Página: {}, Tamanho: {}", page, size);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Count", String.valueOf(carroService.count())); // Adiciona o total de registros no cabeçalho

        Pageable pageable = PageRequest.of(page, size); // Cria objeto Pageable
        List<Carro> allCarros = carroService.listarTodos(pageable); // Passa Pageable ao invés de int, int

        return new ResponseEntity<>(allCarros, headers, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarPorId(@PathVariable Long id) {
        try {
            Carro carro = carroService.buscarPorId(id);
            return ResponseEntity.ok(carro);
        } catch (RecursoNaoEncontradoException e) {
            log.warn("Carro com ID {} não encontrado: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping
    public ResponseEntity<Carro> salvar(@RequestBody Carro carro) {
        Carro carroSalvo = carroService.salvar(carro);
        log.info("Carro salvo com ID: {}", carroSalvo.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizar(@PathVariable Long id, @RequestBody Carro carroAtualizado) {
        try {
            Carro carro = carroService.atualizar(id, carroAtualizado);
            log.info("Carro com ID {} atualizado.", id);
            return ResponseEntity.ok(carro);
        } catch (RecursoNaoEncontradoException e) {
            log.warn("Tentativa de atualizar carro com ID {} falhou: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            carroService.deletar(id);
            log.info("Carro com ID {} deletado.", id);
            return ResponseEntity.noContent().build();
        } catch (RecursoNaoEncontradoException e) {
            log.warn("Tentativa de deletar carro com ID {} falhou: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }


    @GetMapping("/export-cars")
    public ResponseEntity<FileSystemResource> exportCharacters() {
        String filePath = "carros.csv";
        csvService.generate(filePath);

        File file = new File(filePath);
        if (!file.exists() || !file.canRead()) {
            log.error("Arquivo CSV não encontrado ou sem permissão de leitura: {}", filePath);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        FileSystemResource fileSystemResource = new FileSystemResource(file);
        log.info("Arquivo CSV de carros exportado: {}", filePath);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(fileSystemResource);
    }

}