package com.github.genraven1.toolrental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ToolNotFoundException extends ResponseStatusException {
    public ToolNotFoundException(final String code) {
        super(HttpStatus.NOT_FOUND, "There is not a tool with the code " + code + " in the system.");
    }
}
