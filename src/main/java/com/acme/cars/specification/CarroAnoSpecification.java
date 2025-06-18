package com.acme.cars.specification;

import com.acme.cars.model.Carro;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

/** Implementação da CarroSpecification para filtrar carros por ano. */
@AllArgsConstructor
public class CarroAnoSpecification implements CarroSpecification {
    private final Integer ano;

    @Override
    public Predicate toPredicate(CriteriaBuilder cb, Root<Carro> root) {
        return cb.equal(root.get("ano"), ano);
    }
}