package com.exemple.simplifiedpicpay.core.domain.apiUser;

public enum ApiUserRole {

    ADMIN("admin"),
    USER("user");

    private final String role;

    ApiUserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
