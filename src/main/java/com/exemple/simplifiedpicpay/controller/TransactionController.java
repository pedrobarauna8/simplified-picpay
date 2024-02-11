package com.exemple.simplifiedpicpay.controller;

import com.exemple.simplifiedpicpay.domain.dto.TransactionDTO;
import com.exemple.simplifiedpicpay.service.AuthenticationService;
import com.exemple.simplifiedpicpay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<Void> transaction(@RequestBody TransactionDTO request) {
        transactionService.transfer(request);
        return ResponseEntity.ok().build();
    }
}