package com.exemple.simplifiedpicpay.service;

import com.exemple.simplifiedpicpay.domain.TransactionDTO;
import com.exemple.simplifiedpicpay.domain.transaction.Transaction;
import com.exemple.simplifiedpicpay.domain.user.UserType;
import com.exemple.simplifiedpicpay.exception.BusinessErrorException;
import com.exemple.simplifiedpicpay.respositories.TransactionRepository;
import com.exemple.simplifiedpicpay.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.exemple.simplifiedpicpay.service.AuthService.isAuthenticatedUser;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void transfer(TransactionDTO request) {
        var payee = userRepository.findById(request.payee()).orElseThrow(); //TODO LANCAR MINHA EXCECAO
        var payer = userRepository.findById(request.payer()).orElseThrow();

        if (payer.getUserType().equals(UserType.PJ)) throw new BusinessErrorException("PF user only receive transfers");

        if (payer.getBalance().subtract(request.value()).compareTo(BigDecimal.ZERO) < 0) throw new BusinessErrorException("User has no balance");

        if (!isAuthenticatedUser()) throw new BusinessErrorException("Transaction not authorized");

        var transaction = new Transaction(request.value(), payer, payee);

        payer.setBalance(payer.getBalance().subtract(transaction.getAmount()));
        payee.setBalance(payee.getBalance().add(transaction.getAmount()));

        transactionRepository.save(transaction);
        userRepository.save(payer);
        userRepository.save(payee);

    }
}
