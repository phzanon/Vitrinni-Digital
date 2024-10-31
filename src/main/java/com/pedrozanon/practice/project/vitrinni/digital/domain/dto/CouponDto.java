package com.pedrozanon.practice.project.vitrinni.digital.domain.dto;

public record CouponDto(
        String idEstabelecimento,
        String dataValidade,
        int desconto,
        String descricao
) {
}
