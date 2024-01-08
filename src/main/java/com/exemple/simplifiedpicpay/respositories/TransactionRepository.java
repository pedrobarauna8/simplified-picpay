package com.exemple.simplifiedpicpay.respositories;

import com.exemple.simplifiedpicpay.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
