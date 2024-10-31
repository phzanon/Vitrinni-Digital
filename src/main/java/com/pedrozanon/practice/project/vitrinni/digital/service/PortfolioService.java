package com.pedrozanon.practice.project.vitrinni.digital.service;

import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.PortfolioDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Image;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Link;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Portfolio;
import com.pedrozanon.practice.project.vitrinni.digital.domain.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository repository;

    private final LinkService linkService;

    private final ImageService imageService;

    Portfolio save(PortfolioDto dto) {
        Portfolio newPort = Portfolio.builder()
                .build();

        if(!dto.links().isEmpty()) {
            List<Link> links = dto
                    .links()
                    .stream()
                    .map(linkService::create)
                    .toList();
            newPort.setLinks(links);
        }

        if(!dto.images().isEmpty()) {
            List<Image> images = dto
                    .images()
                    .stream()
                    .map(imageService::create)
                    .toList();
            newPort.setImages(images);
        }

        return repository.save(newPort);
    }
}
