package ru.ssau.tk.Lab2.LabOOP.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {
    public ArrayIsNotSortedException() {

    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
