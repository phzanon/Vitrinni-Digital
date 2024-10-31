package com.pedrozanon.practice.project.vitrinni.digital.domain.dto;

import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Role;

import java.util.List;

public record RecoveryUserDto(
        Long id,
        String email,
        List<Role> roles
) {
}
