package com.pedrozanon.practice.project.vitrinni.digital.service;

import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.ImageDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Image;
import com.pedrozanon.practice.project.vitrinni.digital.domain.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository repository;

    public Image create(ImageDto dto) {
        return repository.save(Image.builder().imageContent(dto.imageContent()).build());
    }
}
