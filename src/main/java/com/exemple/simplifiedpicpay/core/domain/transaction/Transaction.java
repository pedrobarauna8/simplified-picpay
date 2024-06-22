package com.exemple.simplifiedpicpay.core.domain.transaction;

import com.exemple.simplifiedpicpay.core.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
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
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    private LocalDateTime timeStamps;

    public Transaction(BigDecimal value, UserEntity payer, UserEntity payee) {
        this.amount = value;
        this.sender = payer;
        this.receiver = payee;
        this.timeStamps = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
    }
}
