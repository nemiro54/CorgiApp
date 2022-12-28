package com.andersen.corgiapp.exception;

public class ModelNotFoundException extends RuntimeException {

    private final String message;

    public ModelNotFoundException(long id, String className) {
        this.message = String.format("%s id: %s not found", className, id);
    }

    public String getMessage() {
        return message;
    }
}