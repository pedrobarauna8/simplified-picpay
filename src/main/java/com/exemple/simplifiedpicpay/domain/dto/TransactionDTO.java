package com.exemple.simplifiedpicpay.domain.dto;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value,
                             Long payer,
                             Long payee) {
}
