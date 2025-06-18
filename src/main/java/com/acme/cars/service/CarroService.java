package com.acme.cars.service;

import com.acme.cars.exception.RecursoNaoEncontradoException;
import com.acme.cars.model.Carro;
import com.acme.cars.repository.CarroRepository;
import com.acme.cars.specification.CarroSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

/** Serviço responsável pelas operações de negócio relacionadas a carros. */
@Service
@RequiredArgsConstructor
public class CarroService implements InterfaceCarroService {

    private final CarroRepository carroRepository;
    private final EntityManager entityManager;


    @Override
    public List<Carro> listarTodos(Pageable pageable) {
        return carroRepository.findAll(pageable).stream().toList();
    }

    @Override
    public Carro buscarPorId(Long id) {
        return carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado com id: " + id));
    }

    @Override
    public Carro salvar(Carro carro) {
        return carroRepository.save(carro);
    }

    @Override
    public void deletar(Long id) {
        carroRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado com id: " + id));
        carroRepository.deleteById(id);
    }

    /**
      Atualiza os dados de um carro existente.
      'id' O ID do carro a ser atualizado.
      'carroAtualizado' Os novos dados do carro.
      return O carro com os dados atualizados.
      'throws RecursoNaoEncontradoException' Se o carro não for encontrado para atualizar.
    */
    @Override
    public Carro atualizar(Long id, Carro carroAtualizado) {
        // Nomes significativos: 'carroAtualizado' indica o propósito do parâmetro.
        // Evitando Ifs aninhados: Lançamento de exceção como guarda de cláusula.
        if (!carroRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Carro não encontrado com id: " + id);
        }
        carroAtualizado.setId(id); // Garante que o ID do carro a ser atualizado seja o do path.
        return carroRepository.save(carroAtualizado);
    }


    @Override
    public long count(){
        return carroRepository.count();
    }

    /**
      Realiza uma busca dinâmica de carros baseada em uma lista de especificações.
      a lógica de busca podem ser adicionadas criando novas implementações de CarroSpecification
      sem a necessidade de modificar este metodo 'search'.
      'specifications' Lista de objetos CarroSpecification que definem os critérios de busca.
      'return' Retorna uma lista de carros que satisfazem todas as especificações fornecidas.
    */
    @Override
    public List<Carro> search(List<CarroSpecification> specifications){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Carro> cq = cb.createQuery(Carro.class);
        Root<Carro> carroRoot = cq.from(Carro.class); // Renomeado para 'carroRoot' para maior clareza.

        List<Predicate> predicates = new ArrayList<>();

        // Itera sobre as especificações e adiciona os predicados à lista.
        // Aplicação do padrão Strategy: cada CarroSpecification sabe como construir seu próprio Predicate.
        for (CarroSpecification spec : specifications) {
            predicates.add(spec.toPredicate(cb, carroRoot));
        }

        // Combina todos os predicados com uma operação AND.
        cq.where(predicates.toArray(Predicate[]::new));

        // Executa a query e retorna os resultados.
        return entityManager.createQuery(cq).getResultList();
    }
}