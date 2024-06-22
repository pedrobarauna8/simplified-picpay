package com.exemple.simplifiedpicpay.app.controller;

import com.exemple.simplifiedpicpay.app.mapper.UserMapper;
import com.exemple.simplifiedpicpay.core.domain.User;
import com.exemple.simplifiedpicpay.core.domain.dto.UserRequestDTO;
import com.exemple.simplifiedpicpay.core.exception.BusinessErrorException;
import com.exemple.simplifiedpicpay.core.useCase.UserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Tag(name = "User Controller")
@RestController
@RequestMapping("/user")
public class UserController {

    UserUseCase userUseCase;
    UserMapper mapper;

    public UserController(UserUseCase userUseCase,
                          UserMapper map) {
        this.userUseCase = userUseCase;
        this.mapper = map;
    }

    @Operation(summary = "Criação de um usuário")
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRequestDTO request) throws BusinessErrorException {
        var user = mapper.map(request);
        var response = userUseCase.createUser(user);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obter um usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> createUser(@PathVariable("id") Long id) {
        var response = userUseCase.getUser(id);
        return ResponseEntity.ok(response);
    }
}
