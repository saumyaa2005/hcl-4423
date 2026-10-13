package com.hcl.library.model;

/**
 * Interface representing items that can be borrowed and returned.
 */
public interface Borrowable {
    boolean isAvailable();
    void borrow();
    void giveBack();
}
