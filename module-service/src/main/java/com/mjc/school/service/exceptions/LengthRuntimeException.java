package com.mjc.school.service.exceptions;

public class LengthRuntimeException extends RuntimeException {
    public LengthRuntimeException(String message) {
        super("ERROR_CODE: 0001 ERROR_MESSAGE: " + message);
    }
}
