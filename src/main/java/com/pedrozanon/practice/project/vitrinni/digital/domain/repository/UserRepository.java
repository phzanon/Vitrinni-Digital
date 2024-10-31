package com.pedrozanon.practice.project.vitrinni.digital.domain.repository;

import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
