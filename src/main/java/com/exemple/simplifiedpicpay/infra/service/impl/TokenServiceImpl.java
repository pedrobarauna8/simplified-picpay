package com.exemple.simplifiedpicpay.infra.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.exemple.simplifiedpicpay.core.domain.ApiLoginData;
import com.exemple.simplifiedpicpay.core.domain.apiUser.ApiUserEntity;
import com.exemple.simplifiedpicpay.core.provider.ApiLoginProvider;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Service
public class TokenServiceImpl implements ApiLoginProvider {

    String secret;
    AuthenticationManager authenticationManager;

    public TokenServiceImpl(@Value("${api.security.token.secret}") String secret,
                            AuthenticationManager authenticationManager) {
        this.secret = secret;
        this.authenticationManager = authenticationManager;
    }

    public String getToken(ApiLoginData apiLoginData) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(apiLoginData.login(), apiLoginData.password());
        var apiUser = (ApiUserEntity) authenticationManager.authenticate(usernamePassword).getPrincipal();
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("simplifield-picpay")
                    .withSubject(apiUser.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("simplifield-picpay")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
