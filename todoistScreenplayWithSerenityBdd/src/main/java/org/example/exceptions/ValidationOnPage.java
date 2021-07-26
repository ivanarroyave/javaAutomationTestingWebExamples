package org.example.exceptions;

public class ValidationOnPage extends AssertionError {
    public static final String THE_ELEMENT_ON_PAGE_DO_NOT_EXIST = "The element on page don't exist. %s";

    public ValidationOnPage(String message, Throwable cause){
        super(message, cause);
    }
}
