package com.acme.cars.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Builder@AllArgsConstructor@Data
public class CriteriaRequest {
    Optional<String> modelo;
    Optional<String> fabricante;
    Optional<String> pais;
    Optional<String> cor;
    Optional<Integer> ano;
}
