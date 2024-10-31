package com.pedrozanon.practice.project.vitrinni.digital.domain.dto;

import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.EstablishmentEntity;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserAndEstablishmentDto {
    private User user;
    private EstablishmentEntity estabelecimento;
}
