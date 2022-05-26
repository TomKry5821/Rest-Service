package com.krypczyk.restservice.Exception;

public class EmptyParameterException extends RuntimeException {
    public EmptyParameterException(String message) {
        super(message);
    }
}
