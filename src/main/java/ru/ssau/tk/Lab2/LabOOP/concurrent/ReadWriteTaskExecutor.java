package ru.ssau.tk.Lab2.LabOOP.concurrent;

import ru.ssau.tk.Lab2.LabOOP.functions.ConstantFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;

public class ReadWriteTaskExecutor {

    public static void main(String[] args) {
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);
        Thread readTask = new Thread(new ReadTask(linkedListFunction));
        Thread writeTask = new Thread(new WriteTask(linkedListFunction,0.5));
        readTask.start();
        writeTask.start();
    }
}
