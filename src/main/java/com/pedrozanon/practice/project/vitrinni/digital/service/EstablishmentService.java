package com.pedrozanon.practice.project.vitrinni.digital.service;

import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.EstablishmentDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Coupon;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.EstablishmentEntity;
import com.pedrozanon.practice.project.vitrinni.digital.domain.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstablishmentService {

    private final EstablishmentRepository repository;

    private final PortfolioService portfolioService;

    private final AddressService addressService;

    private final UserService userService;

    public EstablishmentEntity findEstablishmentById(String uuid) {
        final var establishment = repository.findById(UUID.fromString(uuid));
        if(establishment.isPresent())
            return establishment.get();
        throw new RuntimeException("Estabelecimento nao encontrado");
    }

    public EstablishmentEntity create(EstablishmentDto dto) {

        EstablishmentEntity newEstab = EstablishmentEntity
                .builder()
                .ativo(true)
                .nome(dto.nome())
                .idTipoEstabelecimento(dto.tipoEstabelecimento().id)
                .idUsuario(dto.idUsuario())
                .telefone1(dto.telefone1())
                .telefone2(dto.telefone2())
                .portfolio(portfolioService.save(dto.portfolioDto()))
                .endereco(addressService.create(dto.enderecoDto()))
                .user(this.userService.findUserById(dto.idUsuario()))
                .build();
        return repository.save(newEstab);
    }

    public EstablishmentEntity findByUuid(String uuid) {
        return this.repository.findById(UUID.fromString(uuid)).orElseThrow(() -> new RuntimeException("Estabelecimento nao encontrado"));
    }

    public List<EstablishmentEntity> findAllEstablishments() {
        final var estabs = this.repository.findAll();
        estabs.forEach(estab -> {
            estab.setCupons(estab.onlyValidCoupons());
        });
        return estabs;
    }

    public EstablishmentEntity findByUserId(String uuid) {
        return this.repository.findByIdUsuario(uuid).orElse(null);
    }

    public void addCoupon(Coupon coupon, String idEstablishment) {
        var estab = findEstablishmentById(idEstablishment);
        estab.addCoupon(coupon);

        repository.save(estab);
    }
}
