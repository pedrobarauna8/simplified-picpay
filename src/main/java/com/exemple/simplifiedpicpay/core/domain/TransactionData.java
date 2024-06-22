package com.exemple.simplifiedpicpay.core.domain;

import java.math.BigDecimal;

public record TransactionData(BigDecimal value,
                              Long payer,
                              Long payee) {
}
