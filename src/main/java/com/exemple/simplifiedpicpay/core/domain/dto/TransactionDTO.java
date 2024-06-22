package com.exemple.simplifiedpicpay.core.domain.dto;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value,
                             Long payer,
                             Long payee) {
}
