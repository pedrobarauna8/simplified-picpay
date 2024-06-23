package com.exemple.simplifiedpicpay.infra.service.impl;

import com.exemple.simplifiedpicpay.core.provider.AuthenticationProvider;
import lombok.experimental.FieldDefaults;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Service
public class AuthenticationProviderImpl implements AuthenticationProvider {

    RestTemplate restTemplate;
    String url;

    public AuthenticationProviderImpl(RestTemplate restTemplate,
                                      @Value("${url.auth-url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public Boolean isAuthenticatedUser() {

        var response = restTemplate.getForEntity(url, String.class);

        var body = new JSONObject(response.getBody());

        var data = new JSONObject(body.get("data").toString());

        if (response.getStatusCode().is2xxSuccessful() && (data.get("authorization").equals(TRUE))) {
            return TRUE;
        }
        return FALSE;
    }
}
