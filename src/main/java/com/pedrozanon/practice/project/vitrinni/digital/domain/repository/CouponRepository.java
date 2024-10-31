package com.pedrozanon.practice.project.vitrinni.digital.domain.repository;

import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {
}
