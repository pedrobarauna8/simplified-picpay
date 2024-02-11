package com.exemple.simplifiedpicpay.controller;

import com.exemple.simplifiedpicpay.domain.ApiAuthenticationDTO;
import com.exemple.simplifiedpicpay.domain.ApiRegisterDTO;
import com.exemple.simplifiedpicpay.domain.ApiUser;
import com.exemple.simplifiedpicpay.domain.dto.ApiLoginResponseDTO;
import com.exemple.simplifiedpicpay.respositories.ApiUserRepository;
import com.exemple.simplifiedpicpay.service.TokenService;
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

@RestController
@RequestMapping("/auth")
public class ApiAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApiUserRepository apiUserRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid ApiAuthenticationDTO request){

        var usernamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((ApiUser) auth.getPrincipal());

        return ResponseEntity.ok(new ApiLoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid ApiRegisterDTO request){

        if(apiUserRepository.findByLogin(request.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        var apiUser = new ApiUser(request.login(), encryptedPassword, request.role());

        apiUserRepository.save(apiUser);

        return ResponseEntity.ok().build();
    }
}
