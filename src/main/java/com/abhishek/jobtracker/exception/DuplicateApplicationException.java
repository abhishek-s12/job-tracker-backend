package com.abhishek.jobtracker.exception;

public class DuplicateApplicationException
        extends RuntimeException {

    public DuplicateApplicationException(
            String message) {

        super(message);
    }
}