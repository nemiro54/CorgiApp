package com.andersen.corgiapp.exception;

public class RequiredFieldIsEmptyException extends RuntimeException {

    public RequiredFieldIsEmptyException(String message) {
        super(message);
    }
}
