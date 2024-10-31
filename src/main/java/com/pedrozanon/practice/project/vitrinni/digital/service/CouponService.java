package com.pedrozanon.practice.project.vitrinni.digital.service;

import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.CouponDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Coupon;
import com.pedrozanon.practice.project.vitrinni.digital.domain.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    private final EstablishmentService establishmentService;


    public Coupon save(CouponDto dto) {
        var coupon = Coupon
                .builder()
                .dataCadastro(LocalDate.now())
                .dataValidade(LocalDate.parse(dto.dataValidade(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .descricao(dto.descricao())
                .desconto(dto.desconto())
                .build();
        coupon = couponRepository.save(coupon);
        establishmentService.addCoupon(coupon, dto.idEstabelecimento());
        return coupon;
    }

    public List<Coupon> findActiveCoupons(String estabId) {
        final var estab = this.establishmentService.findEstablishmentById(estabId);
        final var allCoupons = estab.getCupons();

        return allCoupons
                .stream()
                .filter(coupon -> coupon.getDataValidade().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }
}
