package com.lpnu.shaggybeavers.exception;

public class NotExistsUserException extends NotExistsObjectException{
    public NotExistsUserException() {
        super("User doesn't exist");
    }
}
