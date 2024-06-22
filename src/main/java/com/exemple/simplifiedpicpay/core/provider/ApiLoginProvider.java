package com.exemple.simplifiedpicpay.core.provider;

import com.exemple.simplifiedpicpay.core.domain.ApiLoginData;

public interface ApiLoginProvider {
    String getToken(ApiLoginData apiLoginData);
    String validateToken(String token);
}
