package com.exemple.simplifiedpicpay.service;

import com.exemple.simplifiedpicpay.domain.apiUser.ApiUser;

public interface TokenService {

    public String generateToken(ApiUser apiUser);
    public String validateToken(String token);
}
