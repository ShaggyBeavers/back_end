package com.lpnu.shaggybeavers.exception;

public class FileException extends RuntimeException {

    private static final String FILE_EXCEPTION = "File operation failed";

    public FileException() {
        super(FILE_EXCEPTION);
    }

    public FileException(String message) {
        super(message.isEmpty() ? FILE_EXCEPTION : message);
    }

}
