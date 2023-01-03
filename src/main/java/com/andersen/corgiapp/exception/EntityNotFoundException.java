package com.andersen.corgiapp.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(long id, String className) {
        super(String.format("%s id: %s not found", className, id));
    }
}