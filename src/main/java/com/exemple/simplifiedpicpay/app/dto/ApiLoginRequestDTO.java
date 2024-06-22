package com.exemple.simplifiedpicpay.app.dto;

import jakarta.validation.constraints.NotBlank;

public record ApiLoginRequestDTO(@NotBlank String login,
                                 @NotBlank String password) {
}
