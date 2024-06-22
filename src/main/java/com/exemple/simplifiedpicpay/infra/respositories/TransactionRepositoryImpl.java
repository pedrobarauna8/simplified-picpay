package com.exemple.simplifiedpicpay.infra.respositories;

import com.exemple.simplifiedpicpay.core.TransactionRepository;
import com.exemple.simplifiedpicpay.core.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepositoryImpl extends JpaRepository<Transaction, Long>, TransactionRepository {
}
