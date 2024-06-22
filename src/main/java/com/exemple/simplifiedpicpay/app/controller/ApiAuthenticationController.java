package com.exemple.simplifiedpicpay.app.controller;

import com.exemple.simplifiedpicpay.app.mapper.ApiDataMapper;
import com.exemple.simplifiedpicpay.app.dto.ApiLoginRequestDTO;
import com.exemple.simplifiedpicpay.core.domain.dto.ApiRegisterRequestDTO;
import com.exemple.simplifiedpicpay.core.provider.ApiLoginProvider;
import com.exemple.simplifiedpicpay.core.useCase.ApiRegisterUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Tag(name = "Api Authentication Controller")
@RestController
@RequestMapping("/auth")
public class ApiAuthenticationController {

    ApiLoginProvider apiLoginProvider;
    ApiRegisterUseCase apiRegisterUseCase;
    ApiDataMapper mapper;

    public ApiAuthenticationController(ApiLoginProvider apiLoginProvider,
                                       ApiRegisterUseCase apiRegisterUseCase,
                                       ApiDataMapper mapper) {
        this.apiLoginProvider = apiLoginProvider;
        this.apiRegisterUseCase = apiRegisterUseCase;
        this.mapper = mapper;
    }

    @Operation(summary = "Login na API")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid ApiLoginRequestDTO request){

        var apiLoginData = mapper.map(request);

        var token = apiLoginProvider.getToken(apiLoginData);

        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Registro na API")
    @PostMapping("/register")
    public ResponseEntity<URI> register(@RequestBody @Valid ApiRegisterRequestDTO request){

        var apiRegisterData = mapper.map(request);

        apiRegisterUseCase.register(apiRegisterData);

        return ResponseEntity.created(URI.create("/login")).build();
    }
}
