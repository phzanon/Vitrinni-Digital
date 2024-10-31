package com.pedrozanon.practice.project.vitrinni.digital.controller;

import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.CouponDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Coupon;
import com.pedrozanon.practice.project.vitrinni.digital.service.CouponService;
import com.pedrozanon.practice.project.vitrinni.digital.service.EstablishmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Cupom")
public class CouponController {

    private final CouponService couponService;


    public CouponController(CouponService couponService,
                            EstablishmentService establishmentService) {
        this.couponService = couponService;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Coupon> saveCoupon(@RequestBody CouponDto dto) {
        final var coupon = couponService.save(dto);
        return ResponseEntity.ok(coupon);
    }

    @CrossOrigin
    @GetMapping("/estabelecimento/{idEstabelecimento}")
    public ResponseEntity<List<Coupon>> getActiveCouponsFromEstablishment(@PathVariable("idEstabelecimento") String idEstab){
        return ResponseEntity.ok(this.couponService.findActiveCoupons(idEstab));
    }
}
