package com.pedrozanon.practice.project.vitrinni.digital.domain.dto;

import java.util.List;

public record PortfolioDto(
        List<LinkDto> links,
        List<ImageDto> images
) {

}
