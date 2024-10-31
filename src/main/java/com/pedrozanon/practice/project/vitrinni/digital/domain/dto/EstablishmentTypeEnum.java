package com.pedrozanon.practice.project.vitrinni.digital.domain.dto;

public enum EstablishmentTypeEnum {
    RASCUNHO(0),
    RESTAURANTE(1),
    BAR(2),
    COMERCIO(3);

    public int id;

    EstablishmentTypeEnum(int id) {
        this.id = id;
    }
}
