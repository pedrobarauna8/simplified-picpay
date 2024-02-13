package com.exemple.simplifiedpicpay.controller;

import com.exemple.simplifiedpicpay.domain.dto.UserDTO;
import com.exemple.simplifiedpicpay.domain.user.User;
import com.exemple.simplifiedpicpay.exception.BusinessErrorException;
import com.exemple.simplifiedpicpay.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Controller")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Criação de um usuário")
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO request) throws BusinessErrorException {
        var response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obter um usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> createUser(@PathVariable("id") Long id) {
        var response = userService.getUser(id);
        return ResponseEntity.ok(response);
    }
}
