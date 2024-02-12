package com.exemple.simplifiedpicpay.handler;

import com.exemple.simplifiedpicpay.domain.dto.ErrorDTO;
import com.exemple.simplifiedpicpay.exception.BusinessErrorException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ExceptionControllerHandler {

    @ExceptionHandler(BusinessErrorException.class)
    public ResponseEntity<ErrorDTO> businessExceptionHandler(BusinessErrorException e, WebRequest request) {
        var error = new ErrorDTO(
                new Timestamp(System.currentTimeMillis()).toString(),
                BAD_REQUEST.value(),
                BAD_REQUEST.toString(),
                e.getMessage(),
                request.getContextPath());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDTO> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e, RequestFacade request) {
        var error = new ErrorDTO(
                new Timestamp(System.currentTimeMillis()).toString(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                request.getContextPath());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDTO> noSuchElementException(NoSuchElementException e, RequestFacade request) {
        var error = new ErrorDTO(
                new Timestamp(System.currentTimeMillis()).toString(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                "User not found",
                request.getContextPath());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> entityNotFoundExceptionHandler() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> genericExceptionHandler(Exception e, RequestFacade request) {
        var error = new ErrorDTO(
                new Timestamp(System.currentTimeMillis()).toString(),
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.toString(),
                e.getMessage(),
                request.getContextPath());
        return ResponseEntity.internalServerError().body(error);
    }
}
