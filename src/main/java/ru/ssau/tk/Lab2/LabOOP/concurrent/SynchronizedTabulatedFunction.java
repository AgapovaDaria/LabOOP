package ru.ssau.tk.Lab2.LabOOP.concurrent;

import com.sun.istack.internal.NotNull;
import ru.ssau.tk.Lab2.LabOOP.functions.Point;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.operations.TabulatedFunctionOperationService;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SynchronizedTabulatedFunction implements TabulatedFunction {

    private final TabulatedFunction function;

    public SynchronizedTabulatedFunction(TabulatedFunction function) {
        this.function = function;
    }

    @NotNull
    @Override
    public Iterator<Point> iterator() {
        synchronized (function) {
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
    }

    @Override
    public double apply(double x){
        synchronized (function){
            return function.apply(x);
        }
    }

    @Override
    public int getCount() {
        synchronized (function) {
            return function.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (function) {
            return function.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (function) {
            return function.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (function) {
            function.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x){
        synchronized (function){
            return function.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y){
        synchronized (function){
            return function.indexOfY(y);
        }
    }

    @Override
    public double leftBound(){
        synchronized (function){
            return function.leftBound();
        }
    }

    @Override
    public double rightBound(){
        synchronized (function){
            return function.rightBound();
        }
    }

    public interface Operation<T>{
        T apply(SynchronizedTabulatedFunction synchronizedTabulatedFunction);
    }


    public <T> T  doSynchronously(Operation<? extends T> operation){
        T tmp;
        synchronized (this) {
          tmp =  operation.apply(this);
        }
        return tmp;
    }
}
