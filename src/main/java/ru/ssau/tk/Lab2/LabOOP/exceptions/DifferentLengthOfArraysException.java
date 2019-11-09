package ru.ssau.tk.Lab2.LabOOP.exceptions;

import java.io.Serializable;

public class DifferentLengthOfArraysException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -9075022722708006101L;

    public DifferentLengthOfArraysException() {

    }

    public DifferentLengthOfArraysException(String message) {
        super(message);
    }
}
