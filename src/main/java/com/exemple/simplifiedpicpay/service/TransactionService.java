package com.exemple.simplifiedpicpay.service;

import com.exemple.simplifiedpicpay.domain.dto.TransactionDTO;

public interface TransactionService {
    public void transfer(TransactionDTO request);
}
