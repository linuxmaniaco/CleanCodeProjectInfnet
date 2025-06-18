package com.acme.cars.specification;

import com.acme.cars.model.Carro;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/** Interface que define o contrato para as especificações de busca de Carro. */
public interface CarroSpecification {
    /**
      Cria um predicado (condição de busca) para a query de carros.
      parametro 'cb' O CriteriaBuilder usado para construir a query.
      parametro 'root' O Root da entidade Carro na query.
      'return' Um objeto Predicate que representa a condição de busca.
    */
    Predicate toPredicate(CriteriaBuilder cb, Root<Carro> root);
}
