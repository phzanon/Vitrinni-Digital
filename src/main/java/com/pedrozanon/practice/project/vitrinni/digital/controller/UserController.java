package com.pedrozanon.practice.project.vitrinni.digital.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.CreateUserDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.LoginUserDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.RecoveryJwtTokenDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.UserAndEstablishmentDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.User;
import com.pedrozanon.practice.project.vitrinni.digital.service.EstablishmentService;
import com.pedrozanon.practice.project.vitrinni.digital.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    private final EstablishmentService establishmentService;

    public UserController(UserService userService,
                          EstablishmentService establishmentService) {
        this.userService = userService;
        this.establishmentService = establishmentService;
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        log.info("Logging user: {}", loginUserDto.email());
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/token/validate")
    public ResponseEntity<String> validate(@RequestParam String token) {
        try {
            userService.validateToken(token);
            return ResponseEntity.ok("Autorizado");
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(403)).build();
        }
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        log.info("Creating new User: {}", createUserDto.email());
        final var created = userService.createUser(createUserDto);
        return ResponseEntity.ok(created);
    }

    @CrossOrigin
    @GetMapping("/{email}")
    public ResponseEntity<UserAndEstablishmentDto> getUserByEmail(@PathVariable("email") String email) {
        final var userId = this.userService.findByEmail(email);
        final var estab = this.establishmentService.findByUserId(userId.getId().toString());
        return ResponseEntity.ok(UserAndEstablishmentDto
                .builder()
                        .user(userId)
                        .estabelecimento(estab)
                .build());
    }

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return ResponseEntity.ok("Autenticado Com sucesso");
    }

    @GetMapping("/test/customer")
    public ResponseEntity<String> getCustomerAuthenticationTest() {
        return ResponseEntity.ok("Cliente Autenticado com sucesso");
    }

    @GetMapping("/test/administrator")
    public ResponseEntity<String> getAdminAuthenticationTest() {
        return ResponseEntity.ok("Administrador autenticado com sucesso");
    }
}
