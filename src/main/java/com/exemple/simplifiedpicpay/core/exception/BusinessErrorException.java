package com.exemple.simplifiedpicpay.core.exception;

import java.io.Serial;

public class BusinessErrorException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -9066731662911028019L;

    public BusinessErrorException(String msg) {
        super(msg);
    }

    public BusinessErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BusinessErrorException() {
    }
}
