package com.exemple.simplifiedpicpay.app.controller;

import com.exemple.simplifiedpicpay.app.mapper.TransactionMapper;
import com.exemple.simplifiedpicpay.core.domain.dto.TransactionDTO;
import com.exemple.simplifiedpicpay.core.useCase.TransactionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Tag(name = "Transaction Controller")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    TransactionMapper transactionMapper;
    TransactionUseCase transactionUseCase;

    public TransactionController(TransactionMapper transactionMapper, TransactionUseCase transactionUseCase) {
        this.transactionMapper = transactionMapper;
        this.transactionUseCase = transactionUseCase;
    }

    @Operation(summary = "Transferência entre dois usuários")
    @PostMapping
    public ResponseEntity<Void> transaction(@RequestBody @Valid TransactionDTO request) {
        var transactionData = transactionMapper.map(request);
        transactionUseCase.transfer(transactionData);
        return ResponseEntity.ok().build();
    }
}