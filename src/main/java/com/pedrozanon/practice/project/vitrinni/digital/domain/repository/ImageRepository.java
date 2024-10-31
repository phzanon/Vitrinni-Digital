package com.pedrozanon.practice.project.vitrinni.digital.domain.repository;

import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
