package com.acme.cars.specification;

import com.acme.cars.model.Carro;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

/** Implementação da CarroSpecification para filtrar carros por modelo. */
@AllArgsConstructor
public class CarroModeloSpecification implements CarroSpecification {
    private final String modelo;

    @Override
    public Predicate toPredicate(CriteriaBuilder cb, Root<Carro> root) {
        return cb.like(cb.lower(root.get("modelo")), "%" + modelo.toLowerCase() + "%");
    }
}
