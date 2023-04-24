package com.eteration.simplebanking.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class InsufficientBalanceException extends Exception {

    private final HttpStatus httpStatus;

    public InsufficientBalanceException(HttpStatus httpStatus,String errorMessage) {
        super(errorMessage);
        this.httpStatus = httpStatus;
    }
}
