package com.exemple.simplifiedpicpay.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class AuthService {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final String AUTORIZADO = "Autorizado";

    public static Boolean isAuthenticatedUser(){
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", String.class);
        var body = new JSONObject(response.getBody());
        if (response.getStatusCode().is2xxSuccessful() && (body.get("message").equals(AUTORIZADO))) return TRUE;
        return FALSE;
    }

}
