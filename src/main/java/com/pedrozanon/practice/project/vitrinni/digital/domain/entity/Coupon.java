package com.pedrozanon.practice.project.vitrinni.digital.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CUPOM")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private LocalDate dataValidade;

    private int desconto;

    private String descricao;

    private LocalDate dataCadastro;
}
