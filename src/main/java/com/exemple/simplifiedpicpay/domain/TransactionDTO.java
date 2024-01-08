package com.exemple.simplifiedpicpay.domain;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long payer, Long payee) {
}
