package com.andersen.corgiapp.exception;

public class NoConnectionException extends RuntimeException {

    public NoConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
