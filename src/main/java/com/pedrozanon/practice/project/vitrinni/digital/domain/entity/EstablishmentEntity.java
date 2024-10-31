package com.pedrozanon.practice.project.vitrinni.digital.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "ESTABELECIMENTO")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EstablishmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int idTipoEstabelecimento;

    private String idUsuario;

    private String nome;

    private String telefone1;

    private String telefone2;

    private boolean ativo;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Address endereco;

    @OneToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @OneToMany
    private List<Coupon> cupons;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addCoupon(Coupon coupon) {
        if(this.cupons == null) {
            this.cupons = List.of(coupon);
        } else {
            this.cupons.add(coupon);
        }
    }

    public List<Coupon> onlyValidCoupons() {
        return this.cupons
                .stream()
                .filter(p -> p.getDataValidade().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }
}
