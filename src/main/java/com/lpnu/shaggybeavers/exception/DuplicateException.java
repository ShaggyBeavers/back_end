package com.lpnu.shaggybeavers.exception;

public class DuplicateException extends RuntimeException {

    private static final String DUPLICATE_EXCEPTION = "Duplicates aren't allowed";

    public DuplicateException() {
        super(DUPLICATE_EXCEPTION);
    }

    public DuplicateException(String message) {
        super(message.isEmpty() ? DUPLICATE_EXCEPTION : message);
    }

}
