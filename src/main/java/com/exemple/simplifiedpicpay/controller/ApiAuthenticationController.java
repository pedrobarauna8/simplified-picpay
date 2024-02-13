package com.exemple.simplifiedpicpay.controller;

import com.exemple.simplifiedpicpay.domain.apiUser.ApiUser;
import com.exemple.simplifiedpicpay.domain.dto.ApiLoginRequestDTO;
import com.exemple.simplifiedpicpay.domain.dto.ApiLoginResponseDTO;
import com.exemple.simplifiedpicpay.domain.dto.ApiRegisterDTO;
import com.exemple.simplifiedpicpay.respositories.ApiUserRepository;
import com.exemple.simplifiedpicpay.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Api Authentication Controller")
@RestController
@RequestMapping("/auth")
public class ApiAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApiUserRepository apiUserRepository;

    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Login na API")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid ApiLoginRequestDTO request){

        var usernamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((ApiUser) auth.getPrincipal());

        return ResponseEntity.ok(new ApiLoginResponseDTO(token));
    }

    @Operation(summary = "Registro na API")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid ApiRegisterDTO request){

        if(apiUserRepository.findByLogin(request.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        var apiUser = new ApiUser(request.login(), encryptedPassword, request.role());

        apiUserRepository.save(apiUser);

        return ResponseEntity.ok().build();
    }
}
