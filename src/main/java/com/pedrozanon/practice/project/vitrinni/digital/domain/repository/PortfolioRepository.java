package com.pedrozanon.practice.project.vitrinni.digital.domain.repository;

import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
