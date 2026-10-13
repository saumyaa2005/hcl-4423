package com.hcl.library.exception;

public class ItemNotAvailableException extends LibraryException {
    private static final long serialVersionUID = 1L;
    public ItemNotAvailableException(String message) {
        super(message);
    }
}
