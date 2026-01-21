package org.example.app.exceptions;

public class DeadlineInPastException extends RuntimeException {
    public DeadlineInPastException (String message) { super(message); }

}

