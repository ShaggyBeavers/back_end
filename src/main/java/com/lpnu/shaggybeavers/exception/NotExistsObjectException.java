package com.lpnu.shaggybeavers.exception;

public class NotExistsObjectException extends RuntimeException{

    private static final String NOT_EXISTS_OBJECT = "Object doesn't exist ";

    public NotExistsObjectException () {
        super(NOT_EXISTS_OBJECT);
    }

    public NotExistsObjectException (String message) {
        super(message.isEmpty() ? NOT_EXISTS_OBJECT : message);
    }
}