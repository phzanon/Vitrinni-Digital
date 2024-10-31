package com.pedrozanon.practice.project.vitrinni.digital.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ENDERECO")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;

    private String cep;

    private String complemento;

    private String numero;

    private String pontoReferencia;

    private String cidade;

    private String bairro;

    private String latitude;

    private String longitude;

    private String uf;
}
