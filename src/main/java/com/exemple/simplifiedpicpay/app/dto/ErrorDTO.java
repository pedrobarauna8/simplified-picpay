package com.exemple.simplifiedpicpay.app.dto;

public record ErrorDTO(String timestamp,
                       Integer status,
                       String error,
                       String message,
                       String path) {
}
