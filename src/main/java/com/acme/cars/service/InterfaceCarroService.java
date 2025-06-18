package com.acme.cars.service;

import com.acme.cars.model.Carro;
import com.acme.cars.specification.CarroSpecification;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
  Interface que define o contrato para o serviço de Carro.
  permitindo que as dependências sejam em abstrações e não em implementações concretas.
*/
public interface InterfaceCarroService {

    List<Carro> listarTodos(Pageable pageable);


    Carro buscarPorId(Long id);


    Carro salvar(Carro carro);


    void deletar(Long id);


    Carro atualizar(Long id, Carro carroAtualizado);


    long count();


    List<Carro> search(List<CarroSpecification> specifications);
}


