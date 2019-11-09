package ru.ssau.tk.Lab2.LabOOP.exceptions;

import java.io.Serializable;

public class ArrayIsNotSortedException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -745273276687932824L;

    public ArrayIsNotSortedException() {

    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
