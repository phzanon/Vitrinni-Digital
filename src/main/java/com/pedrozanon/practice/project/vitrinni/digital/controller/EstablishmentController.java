package com.pedrozanon.practice.project.vitrinni.digital.controller;

import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.EstablishmentDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.EstablishmentEntity;
import com.pedrozanon.practice.project.vitrinni.digital.service.EstablishmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Estabelecimento")
public class EstablishmentController {

    private final EstablishmentService service;

    public EstablishmentController(EstablishmentService service) {
        this.service = service;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<EstablishmentEntity> create(@RequestBody EstablishmentDto establishmentDto) {
        final var newEstab = service.create(establishmentDto);

        return ResponseEntity.ok(newEstab);
    }

    @CrossOrigin
    @GetMapping("/{uuid}")
    public ResponseEntity<EstablishmentEntity> getByUuid(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(this.service.findByUuid(uuid));
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<EstablishmentEntity>> findAllEstablishments() {
        return ResponseEntity.ok(this.service.findAllEstablishments());
    }
}
