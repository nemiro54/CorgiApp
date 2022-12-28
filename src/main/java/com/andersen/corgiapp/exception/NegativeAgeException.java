package com.andersen.corgiapp.exception;

public class NegativeAgeException extends RuntimeException {

    public NegativeAgeException(String message) {
        super(message);
    }
}
