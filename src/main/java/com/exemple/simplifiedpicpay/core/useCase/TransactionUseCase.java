package com.exemple.simplifiedpicpay.core.useCase;

import com.exemple.simplifiedpicpay.core.TransactionRepository;
import com.exemple.simplifiedpicpay.core.UserRepository;
import com.exemple.simplifiedpicpay.core.domain.TransactionData;
import com.exemple.simplifiedpicpay.core.domain.transaction.Transaction;
import com.exemple.simplifiedpicpay.core.domain.user.UserType;
import com.exemple.simplifiedpicpay.core.exception.BusinessErrorException;
import com.exemple.simplifiedpicpay.core.provider.AuthenticationProvider;
import com.exemple.simplifiedpicpay.core.provider.NotificationProvider;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Component
public class TransactionUseCase {

    UserRepository userRepository;
    AuthenticationProvider authenticationProvider;
    TransactionRepository transactionRepository;
    NotificationProvider notificationProvider;

    public TransactionUseCase(UserRepository userRepository,
                              AuthenticationProvider authenticationProvider,
                              TransactionRepository transactionRepository,
                              NotificationProvider notificationProvider) {
        this.userRepository = userRepository;
        this.authenticationProvider = authenticationProvider;
        this.transactionRepository = transactionRepository;
        this.notificationProvider = notificationProvider;
    }

    @Transactional
    public void transfer(TransactionData transactionData) {

        var payee = userRepository.findById(transactionData.payee()).orElseThrow();
        var payer = userRepository.findById(transactionData.payer()).orElseThrow();

        if (payer.getUserType().equals(UserType.PJ)) throw new BusinessErrorException("PF user only receive transfers");

        if (payer.getBalance().subtract(transactionData.value()).compareTo(BigDecimal.ZERO) < 0)
            throw new BusinessErrorException("User has no balance");

        if (!authenticationProvider.isAuthenticatedUser())
            throw new BusinessErrorException("Transaction not authorized");

        var transaction = new Transaction(transactionData.value(), payer, payee);

        payer.setBalance(payer.getBalance().subtract(transaction.getAmount()));
        payee.setBalance(payee.getBalance().add(transaction.getAmount()));

        transactionRepository.save(transaction);
        userRepository.save(payer);
        userRepository.save(payee);

        notificationProvider.sendNotification();

    }
}
