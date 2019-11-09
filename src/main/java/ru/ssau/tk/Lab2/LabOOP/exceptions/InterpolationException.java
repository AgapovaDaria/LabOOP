package ru.ssau.tk.Lab2.LabOOP.exceptions;

import java.io.Serializable;

public class InterpolationException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 4260786385203168349L;

    public InterpolationException() {

    }

    public InterpolationException(String message) {
        super(message);
    }
}
