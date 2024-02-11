package com.exemple.simplifiedpicpay.service;

import com.exemple.simplifiedpicpay.domain.ApiUser;

public interface TokenService {

    public String generateToken(ApiUser apiUser);
    public String validateToken(String token);
}
