package com.camelot.userManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No data  found")
public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException() {
    }

    public NoDataFoundException(String message) {
        super(message);
    }
}
