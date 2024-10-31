package com.pedrozanon.practice.project.vitrinni.digital.service;

import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.AddressDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Address;
import com.pedrozanon.practice.project.vitrinni.digital.domain.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;
    private final GeolocationService geolocationService;

    public Address create(AddressDto dto) {
        final var geoloc = this.geolocationService.getGeoloc(dto.logradouro() + dto.numero() + dto.cidade() + dto.cep(), "");

        return repository.save(Address
                .builder()
                        .cep(dto.cep())
                        .bairro(dto.bairro())
                        .cidade(dto.cidade())
                        .numero(dto.numero())
                        .pontoReferencia(dto.pontoReferencia())
                        .uf(dto.uf())
                        .logradouro(dto.logradouro())
                        .complemento(dto.complemento())
                        .latitude(geoloc.results().get(0).geometry().location().lat().toString())
                        .longitude(geoloc.results().get(0).geometry().location().lng().toString())
                .build());
    }
}
