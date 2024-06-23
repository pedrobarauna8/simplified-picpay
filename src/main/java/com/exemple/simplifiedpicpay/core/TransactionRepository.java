package com.exemple.simplifiedpicpay.core;

import com.exemple.simplifiedpicpay.core.domain.transaction.Transaction;

public interface TransactionRepository {
    //UserDetails findByLogin(String login);
    Transaction save(Transaction transaction);
}
