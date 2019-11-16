package ru.ssau.tk.Lab2.LabOOP.concurrent;

import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;

public class ReadTask implements Runnable {
    private final TabulatedFunction function;

    ReadTask(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public void run() {
        for (int i = 0; i < function.getCount(); i++) {
            synchronized (function) {
                System.out.printf("After read: i = %dx = %f, y = %f\n", i, function.getX(i), function.getY(i));
            }
        }
    }
}
