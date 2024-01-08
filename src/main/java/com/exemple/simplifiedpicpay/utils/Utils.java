package com.exemple.simplifiedpicpay.utils;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import com.exemple.simplifiedpicpay.domain.user.UserType;
import com.exemple.simplifiedpicpay.exception.BusinessErrorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    public static void validateDocument(String document, UserType userType) throws BusinessErrorException {

        var cpfValidator = new CPFValidator();
        var cnpjValidator = new CNPJValidator();

        try {
            if (userType.equals(UserType.PF)) {
                cpfValidator.assertValid(document);
            } else {
                cnpjValidator.assertValid(document);
            }
        } catch (Exception e) {
            throw new BusinessErrorException("Invalid document");
        }
    }

    public static void validateEmail(String email) throws BusinessErrorException {
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new BusinessErrorException("Invalid email");
        }
    }
}
