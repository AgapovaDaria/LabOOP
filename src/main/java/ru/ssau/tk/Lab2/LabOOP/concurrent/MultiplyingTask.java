package ru.ssau.tk.Lab2.LabOOP.concurrent;

import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {

    private final TabulatedFunction function;
    public boolean isDone = false;

    MultiplyingTask(TabulatedFunction tabulatedFunction) {
        function = tabulatedFunction;
    }

    @Override
    public void run() {
        for (int i = 0; i < function.getCount(); i++) {
            synchronized (function) {
                function.setY(i, 2 * function.getY(i));
            }
        }
        isDone = true;
        System.out.println("Поток" + Thread.currentThread().getName() + " закончил выполнение задачи");
    }
}