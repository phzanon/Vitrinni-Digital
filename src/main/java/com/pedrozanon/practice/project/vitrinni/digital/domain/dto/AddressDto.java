package com.pedrozanon.practice.project.vitrinni.digital.domain.dto;

public record AddressDto(
        String logradouro,
        String cep,
        String numero,
        String complemento,
        String pontoReferencia,
        String cidade,
        String bairro,
        String uf
) {
}
