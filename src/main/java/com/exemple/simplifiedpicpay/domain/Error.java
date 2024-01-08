package com.exemple.simplifiedpicpay.domain;

public record Error(String timestamp, Integer status, String error, String message, String path) {
}
