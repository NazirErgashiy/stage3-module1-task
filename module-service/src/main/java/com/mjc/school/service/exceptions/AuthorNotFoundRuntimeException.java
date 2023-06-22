package com.mjc.school.service.exceptions;

public class AuthorNotFoundRuntimeException extends RuntimeException {
    public AuthorNotFoundRuntimeException(String message) {
        super("ERROR_CODE: 0002 ERROR_MESSAGE: " + message);
    }
}
