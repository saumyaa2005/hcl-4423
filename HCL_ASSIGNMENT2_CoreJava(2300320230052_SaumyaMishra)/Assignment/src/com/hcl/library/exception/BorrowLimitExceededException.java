package com.hcl.library.exception;

public class BorrowLimitExceededException extends LibraryException {
    private static final long serialVersionUID = 1L;
    public BorrowLimitExceededException(String message) {
        super(message);
    }
}
