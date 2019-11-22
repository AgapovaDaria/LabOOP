package ru.ssau.tk.Lab2.LabOOP.concurrent;

import ru.ssau.tk.Lab2.LabOOP.functions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class MultiplyingTaskExecutor {

    public static void main(String[] args) throws InterruptedException {

        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);
        List<Thread> threadList = new ArrayList<>();
        List<MultiplyingTask> tasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            MultiplyingTask task = new MultiplyingTask(linkedListFunction);
            threadList.add(new Thread(task));
            tasks.add(task);
        }

        for (Thread t : threadList) {
            t.start();
        }

        while (!tasks.isEmpty()){
           tasks.removeIf((task) -> task.isDone);
        }

        System.out.println(linkedListFunction);
    }
}
