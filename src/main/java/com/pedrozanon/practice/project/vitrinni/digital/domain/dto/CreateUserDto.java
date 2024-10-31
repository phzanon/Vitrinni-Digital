package com.pedrozanon.practice.project.vitrinni.digital.domain.dto;

import com.pedrozanon.practice.project.vitrinni.digital.domain.role.RoleName;

public record CreateUserDto (
        String email,
        String password,
        RoleName role
) {}
