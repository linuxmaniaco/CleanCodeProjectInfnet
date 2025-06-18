package com.acme.cars.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carro")
@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private int ano;
    private String cor;
    private int cavalosDePotencia;
    private String fabricante;
    private String pais;

}
