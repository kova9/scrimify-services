package com.scrimify.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ScrimifyException extends RuntimeException {
    private final HttpStatus status;

    private ScrimifyException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public static ScrimifyException badRequest(String message) {
        return new ScrimifyException(message, HttpStatus.BAD_REQUEST);
    }

    public static ScrimifyException notFound(String message) {
        return new ScrimifyException(message, HttpStatus.NOT_FOUND);
    }

    public static ScrimifyException internalServerError(String message) {
        return new ScrimifyException(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Add more methods as needed for other HTTP status codes
    public static ScrimifyException unauthorized(String message) {
        return new ScrimifyException(message, HttpStatus.UNAUTHORIZED);
    }

    public static ScrimifyException conflict(String message) {
        return new ScrimifyException(message, HttpStatus.CONFLICT);
    }
}
