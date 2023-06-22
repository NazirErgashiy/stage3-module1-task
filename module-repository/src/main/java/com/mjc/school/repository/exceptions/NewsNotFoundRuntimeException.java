package com.mjc.school.repository.exceptions;

public class NewsNotFoundRuntimeException extends RuntimeException{
    public NewsNotFoundRuntimeException(String message){
        super("ERROR_CODE: 1001 ERROR_MESSAGE: "+message);
    }
}
