package ru.ssau.tk.Lab2.LabOOP.concurrent;

import ru.ssau.tk.Lab2.LabOOP.functions.Point;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.operations.TabulatedFunctionOperationService;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction function;
    final Object mutex;

    SynchronizedTabulatedFunction(TabulatedFunction function) {
        this.function = function;
        this.mutex = this;
    }

    @Override
    public synchronized Iterator<Point> iterator() {
        Point[] copy = TabulatedFunctionOperationService.asPoints(function);
        return new Iterator<Point>() {
            int i = 0;
            public boolean hasNext() {
                return i != copy.length;
            }
            public Point next() {
                if (i == copy.length) {
                    throw new NoSuchElementException();
                }
                return new Point(copy[i].x, copy[i++].y);
            }
        };
    }

    @Override
    public double apply(double x){
        synchronized (mutex){
            return function.apply(x);
        }
    }

    @Override
    public int getCount() {
        synchronized (mutex) {
            return function.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (mutex) {
            return function.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (mutex) {
            return function.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (mutex) {
            function.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x){
        synchronized (mutex){
            return function.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y){
        synchronized (mutex){
            return function.indexOfY(y);
        }
    }

    @Override
    public double leftBound(){
        synchronized (mutex){
            return function.leftBound();
        }
    }

    @Override
    public double rightBound(){
        synchronized (mutex){
            return function.rightBound();
        }
    }
}
