package com.exemple.simplifiedpicpay.controller;

import com.exemple.simplifiedpicpay.domain.dto.TransactionDTO;
import com.exemple.simplifiedpicpay.service.AuthenticationService;
import com.exemple.simplifiedpicpay.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Transaction Controller")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Transferência entre dois usuários")
    @PostMapping
    public ResponseEntity<Void> transaction(@RequestBody @Valid TransactionDTO request) {
        transactionService.transfer(request);
        return ResponseEntity.ok().build();
    }
}