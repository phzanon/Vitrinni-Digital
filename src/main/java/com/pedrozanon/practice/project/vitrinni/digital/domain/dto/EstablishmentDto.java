package com.pedrozanon.practice.project.vitrinni.digital.domain.dto;

public record EstablishmentDto(
        String idUsuario,
        String nome,
        EstablishmentTypeEnum tipoEstabelecimento,
        String telefone1,
        String telefone2,
        AddressDto enderecoDto,
        PortfolioDto portfolioDto
) {
}
