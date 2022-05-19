package com.pablojuice.framework.exceptions;

import org.openqa.selenium.WebDriverException;


public class ElementException extends WebDriverException {

    private static final String MESSAGE = "Something bad happened while working with web elements. ";

    public ElementException() {
        super(MESSAGE);
    }

    public ElementException(String message) {
        super(MESSAGE + message);
    }

    public ElementException(String message, Throwable cause) {
        super(MESSAGE + message, cause);
    }

    public ElementException(Throwable cause) {
        super(cause);
    }
}
