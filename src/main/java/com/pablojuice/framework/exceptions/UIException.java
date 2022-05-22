package com.pablojuice.framework.exceptions;

import org.openqa.selenium.WebDriverException;


public class UIException extends WebDriverException {

    private static final String MESSAGE = "Something bad happened while working with web elements. ";

    public UIException() {
        super(MESSAGE);
    }

    public UIException(String message) {
        super(MESSAGE + message);
    }

    public UIException(String message, Throwable cause) {
        super(MESSAGE + message, cause);
    }

    public UIException(Throwable cause) {
        super(cause);
    }
}
