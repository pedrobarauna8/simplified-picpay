package com.exemple.simplifiedpicpay.core;

import com.exemple.simplifiedpicpay.core.domain.transaction.Transaction;
import org.springframework.security.core.userdetails.UserDetails;

public interface TransactionRepository {
    UserDetails findByLogin(String login);
    Transaction save(Transaction transaction);
}
