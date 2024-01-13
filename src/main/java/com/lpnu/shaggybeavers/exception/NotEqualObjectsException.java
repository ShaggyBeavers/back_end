package com.lpnu.shaggybeavers.exception;

public class NotEqualObjectsException extends RuntimeException {

    private static final String NOT_EQUAL_OBJECTS = "Objects aren't equal";

    public NotEqualObjectsException () {
        super(NOT_EQUAL_OBJECTS);
    }

    public NotEqualObjectsException (String message) {
        super(message.isEmpty() ? NOT_EQUAL_OBJECTS : message);
    }

}
