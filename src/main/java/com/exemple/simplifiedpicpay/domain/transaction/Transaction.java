package com.exemple.simplifiedpicpay.domain.transaction;

import com.exemple.simplifiedpicpay.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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

    public Transaction(BigDecimal value, User payer, User payee){
        this.amount = value;
        this.sender = payer;
        this.receiver = payee;
        this.timeStamps = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
    }
}
