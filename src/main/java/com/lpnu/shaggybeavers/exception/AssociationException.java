package com.lpnu.shaggybeavers.exception;

public class AssociationException extends RuntimeException {

    private static final String ASSOCIATION_EXCEPTION = "Entity is associated with other entity";

    public AssociationException() {
        super(ASSOCIATION_EXCEPTION);
    }

    public AssociationException(String message) {
        super(message.isEmpty() ? ASSOCIATION_EXCEPTION : message);
    }

}
