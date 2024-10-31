package com.pedrozanon.practice.project.vitrinni.digital.service;

import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.CreateUserDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.LoginUserDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.RecoveryJwtTokenDto;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.Role;
import com.pedrozanon.practice.project.vitrinni.digital.domain.entity.User;
import com.pedrozanon.practice.project.vitrinni.digital.domain.repository.UserRepository;
import com.pedrozanon.practice.project.vitrinni.digital.domain.role.RoleName;
import com.pedrozanon.practice.project.vitrinni.digital.security.SecurityConfig;
import com.pedrozanon.practice.project.vitrinni.digital.security.UserDetailsImpl;
import com.pedrozanon.practice.project.vitrinni.digital.security.service.JwtTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenService jwtTokenService;

    private final UserRepository userRepository;

    private final SecurityConfig securityConfig;

    public UserService(AuthenticationManager authenticationManager,
                       JwtTokenService jwtTokenService,
                       UserRepository userRepository,
                       SecurityConfig securityConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }


    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    public User createUser(CreateUserDto createUserDto) {
        Role role;

        if(createUserDto.role() == null)
            role = Role.builder().name(RoleName.ROLE_CUSTOMER).build();
        else
            role = Role.builder().name(createUserDto.role()).build();

        User newUser = User.builder()
                .email(createUserDto.email())
                .password(securityConfig.passwordEncoder().encode(createUserDto.password()))
                .roles(List.of(role))
                .build();

        return userRepository.save(newUser);
    }

    public boolean validateToken(String token) {
        if(token.contains("Bearer "))
            token = token.substring(7);
        String subject = this.jwtTokenService.getSubjectFromToken(token);
        User user = userRepository.findByEmail(subject).isPresent() ? userRepository.findByEmail(subject).get() : null;
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return true;
    }

    public User findUserById(String id) {
        final var user = userRepository.findById(UUID.fromString(id));
        if(user.isPresent())
            return user.get();
        throw new RuntimeException("usuario nao encontrado");
    }

    public User findByEmail(String email) {
        final var user = userRepository.findByEmail(email);
        if(user.isPresent())
            return user.get();
        throw new RuntimeException("usuario nao encontrado");
    }
}
