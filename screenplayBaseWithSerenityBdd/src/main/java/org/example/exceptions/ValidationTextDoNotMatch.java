package org.example.exceptions;

public class ValidationTextDoNotMatch extends AssertionError {
    public static final String VALIDATION_DO_NOT_MATCH = "The validation don't match. %s";

    public ValidationTextDoNotMatch(String message, Throwable cause){
        super(message, cause);
    }
}
