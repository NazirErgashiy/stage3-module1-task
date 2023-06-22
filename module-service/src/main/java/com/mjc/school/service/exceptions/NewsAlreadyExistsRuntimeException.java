package com.mjc.school.service.exceptions;

public class NewsAlreadyExistsRuntimeException extends RuntimeException {
    public NewsAlreadyExistsRuntimeException(String message) {
        super("ERROR_CODE: 0003 ERROR_MESSAGE: " + message);
    }
}
