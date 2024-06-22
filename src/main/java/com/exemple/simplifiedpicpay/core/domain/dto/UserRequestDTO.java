package com.exemple.simplifiedpicpay.core.domain.dto;

import com.exemple.simplifiedpicpay.core.domain.user.UserType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record UserRequestDTO(String name,
                             String document,
                             @Valid @Pattern(regexp = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") String email,
                             String password,
                             BigDecimal balance,
                             UserType userType) {
}
