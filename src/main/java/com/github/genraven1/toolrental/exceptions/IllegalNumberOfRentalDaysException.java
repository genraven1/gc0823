package com.github.genraven1.toolrental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IllegalNumberOfRentalDaysException extends ResponseStatusException {
    public IllegalNumberOfRentalDaysException() {
        super(HttpStatus.BAD_REQUEST, "Tools must be rented for longer than 1 (one) day.");
    }
}
