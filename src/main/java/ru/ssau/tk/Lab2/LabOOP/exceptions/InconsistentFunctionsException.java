package ru.ssau.tk.Lab2.LabOOP.exceptions;

import java.io.Serializable;

public class InconsistentFunctionsException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 3444812072879950716L;

    public InconsistentFunctionsException() {

    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
