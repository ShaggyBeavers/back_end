package com.lpnu.shaggybeavers.exception;

public class TokenException extends RuntimeException {

    private static final String TOKEN_EXCEPTION = "Token is invalid";

    public TokenException () {
        super(TOKEN_EXCEPTION);
    }

    public TokenException (String message) {
        super(message.isEmpty() ? TOKEN_EXCEPTION : message);
    }

}
