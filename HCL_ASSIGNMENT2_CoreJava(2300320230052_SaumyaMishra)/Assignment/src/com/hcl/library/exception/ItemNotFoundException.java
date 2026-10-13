package com.hcl.library.exception;

public class ItemNotFoundException extends LibraryException {
    private static final long serialVersionUID = 1L;
    public ItemNotFoundException(String message) {
        super(message);
    }
}
