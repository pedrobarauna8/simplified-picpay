package com.exemple.simplifiedpicpay.core.domain;

import com.exemple.simplifiedpicpay.core.exception.BusinessErrorException;
import lombok.Getter;

import java.util.regex.Pattern;

public class Document {
    private final static String VALID_DOCUMENT_PATTERN = "([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}/?[0-9]{4}-?[0-9]{2})|([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-?[0-9]{2})";

    @Getter
    private String number;

    public Document(String number) {

        if (!Pattern.matches(VALID_DOCUMENT_PATTERN, number)){
            throw new BusinessErrorException("Invalid document");
        }
        this.number = number;
    }

}
