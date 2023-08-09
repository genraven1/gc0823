package com.github.genraven1.toolrental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IllegalDiscountException extends ResponseStatusException {
    public IllegalDiscountException(final int discount) {
        super(HttpStatus.BAD_REQUEST, String.format("The discount %s%% can only be between 0% and 100%", discount));
    }
}
