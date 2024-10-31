package com.pedrozanon.practice.project.vitrinni.digital.domain.repository;

import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.EstablishmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EstablishmentRepository extends JpaRepository<EstablishmentEntity, UUID> {

    Optional<EstablishmentEntity> findByIdUsuario(String idUsuario);
}
