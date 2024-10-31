package com.pedrozanon.practice.project.vitrinni.digital.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "IMAGEM")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String imageContent;
}
