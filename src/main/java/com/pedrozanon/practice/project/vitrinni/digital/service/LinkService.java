package com.pedrozanon.practice.project.vitrinni.digital.service;

import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.LinkDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Link;
import com.pedrozanon.practice.project.vitrinni.digital.domain.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;


    public Link create(LinkDto dto) {
        return linkRepository.save(Link.builder().url(dto.url()).build());
    }
}
