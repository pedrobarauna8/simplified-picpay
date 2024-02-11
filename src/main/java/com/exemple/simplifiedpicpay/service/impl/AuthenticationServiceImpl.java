package com.exemple.simplifiedpicpay.service.impl;

import com.exemple.simplifiedpicpay.service.AuthenticationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.auth-url}")
    private String url;

    private static final String AUTORIZADO = "Autorizado";

    public Boolean isAuthenticatedUser() {

        var response = restTemplate.getForEntity(url, String.class);

        var body = new JSONObject(response.getBody());

        if (response.getStatusCode().is2xxSuccessful() && (body.get("message").equals(AUTORIZADO))) {
            return TRUE;
        }
        return FALSE;
    }
}
