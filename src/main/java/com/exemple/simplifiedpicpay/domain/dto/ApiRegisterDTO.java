package com.exemple.simplifiedpicpay.domain.dto;

import com.exemple.simplifiedpicpay.domain.apiUser.ApiUserRole;

public record ApiRegisterDTO(String login, String password, ApiUserRole role) {
}
