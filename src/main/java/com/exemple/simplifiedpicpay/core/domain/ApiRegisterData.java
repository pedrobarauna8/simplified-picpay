package com.exemple.simplifiedpicpay.core.domain;

import com.exemple.simplifiedpicpay.core.domain.apiUser.ApiUserRole;

public record ApiRegisterData(String login,
                              String password,
                              ApiUserRole role) {
}
