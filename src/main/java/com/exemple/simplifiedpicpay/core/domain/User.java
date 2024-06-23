package com.exemple.simplifiedpicpay.core.domain;

import com.exemple.simplifiedpicpay.core.domain.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class User {

    private String name;
    private Document document;
    private String email;
    private String password;
    private BigDecimal balance;
    private UserType userType;

    public String getDocument() {
        return document.getNumber();
    }
}
