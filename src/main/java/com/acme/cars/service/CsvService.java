package com.acme.cars.service;

import com.acme.cars.model.Carro;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest; // Adicionado import para PageRequest

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/** Serviço responsável por gerar arquivos CSV de dados de carros. */
@Service
@RequiredArgsConstructor
public class CsvService {
    private final InterfaceCarroService carroService;

    /**
      Gera um arquivo CSV contendo todos os dados de carros.
      filepath O caminho completo onde o arquivo CSV será salvo.
      'RuntimeException' Se ocorrer um erro de I/O durante a escrita do arquivo.
    */
    public void generate(String filepath){
        // Explicação desse trecho do codigo
        // Busca todos os carros. Para evitar carregar todos os dados em memória de uma vez,
        // se o objetivo for exportar todos os registros de um banco muito grande.
        // Lista todos os carros sem paginação.
        List<Carro> allCars = carroService.listarTodos(PageRequest.of(0, Integer.MAX_VALUE)); // Buscar todos com paginação máxima

        try(CSVWriter writer = new CSVWriter(new FileWriter(filepath))){

            writer.writeNext(new String[]{"ID", "MODELO", "ANO", "COR", "HP", "FABRICANTE", "PAIS"});
            for(Carro carro : allCars){

                writer.writeNext(new String[]{
                        String.valueOf(carro.getId()),
                        carro.getModelo(),
                        String.valueOf(carro.getAno()),
                        carro.getCor(),
                        String.valueOf(carro.getCavalosDePotencia()),
                        carro.getFabricante(),
                        carro.getPais()
                });
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar o arquivo CSV: " + e.getMessage(), e);
        }
    }
}