package com.exemple.simplifiedpicpay.domain.transaction;

import com.exemple.simplifiedpicpay.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "transactions")
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private LocalDateTime timeStamps;

    public Transaction(BigDecimal value, User payer, User payee) {
        this.amount = value;
        this.sender = payer;
        this.receiver = payee;
        this.timeStamps = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
    }
}
