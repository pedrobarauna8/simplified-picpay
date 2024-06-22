package com.exemple.simplifiedpicpay.core.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record ApiRegisterRequestDTO(@NotBlank String login,
                                    @NotBlank String password,
                                    @NotBlank String role) {
}
