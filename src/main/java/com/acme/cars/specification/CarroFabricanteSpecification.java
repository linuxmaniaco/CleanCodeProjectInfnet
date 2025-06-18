package com.acme.cars.specification;

import com.acme.cars.model.Carro;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

/** Implementação da CarroSpecification para filtrar carros por fabricante. */
@AllArgsConstructor
public class CarroFabricanteSpecification implements CarroSpecification {
    private final String fabricante;

    @Override
    public Predicate toPredicate(CriteriaBuilder cb, Root<Carro> root) {
        return cb.like(cb.lower(root.get("fabricante")), "%" + fabricante.toLowerCase() + "%");
    }
}
