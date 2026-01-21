package org.example.app.exceptions;

public class TaskWithoutProjectException extends RuntimeException {
    public TaskWithoutProjectException (String message) { super(message); }

}

