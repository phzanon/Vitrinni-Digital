package com.pedrozanon.practice.project.vitrinni.digital.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "PORTFOLIO")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Link> links;

    @OneToMany
    private List<Image> images;
}
