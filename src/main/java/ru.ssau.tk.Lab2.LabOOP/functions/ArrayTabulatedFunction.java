package ru.ssau.tk.Lab2.LabOOP.functions;

import ru.ssau.tk.Lab2.LabOOP.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.Lab2.LabOOP.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.Lab2.LabOOP.exceptions.InterpolationException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private double[] xValues;
    private double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) throws IllegalArgumentException,
            DifferentLengthOfArraysException, ArrayIsNotSortedException {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Массив меньше данной длины");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) throws IllegalArgumentException {
        this.count = count;
        xValues = new double[count];
        yValues = new double[count];
        if (count < 2) {
            throw new IllegalArgumentException("Шаг меньше минимальной длины");
        }
        if (xFrom > xTo) {
            double tmp = xFrom;
            xFrom = xTo;
            xTo = tmp;
        }
        double len = (xTo - xFrom) / (count - 1);
        double x = xFrom;
        for (int i = 0; i < count; i++) {
            xValues[i] = x;
            yValues[i] = source.apply(x);
            x += len;
        }
    }


    @Override
    protected int floorIndexOfX(double x) throws IllegalArgumentException {
        int i;
        if (x < xValues[0]) {
            throw new IllegalArgumentException("Аргумент x меньше минимального в табличной функции");
        }
        for (i = 0; i < count; i++) {
            if (xValues[i] > x) {
                return i - 1;
            }
        }
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {

        return interpolate(x, getX(0), getX(1), getY(0), getY(1));
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, getX(count - 2), getX(count - 1), getY(count - 2), getY(count - 1));
    }

    @Override
    //Без проверок на корректность данных. Запускается в методе apply()
    protected double interpolate(double x, int floorIndex) {
        int i = indexOfX(x);
        if (floorIndex >= i && i >= (floorIndex + 1)) {
            throw new InterpolationException("х находится вне интервала интерполирования");
        }
        return interpolate(x, getX(floorIndex), getX(floorIndex + 1), getY(floorIndex), getY(floorIndex + 1));
    }

    @Override
    public double getX(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Неверный индекс");
        }
        return xValues[index];
    }

    @Override
    public double getY(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Неверный индекс");
        }
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Неверный индекс");
        }
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        if (x < leftBound() || x > rightBound()) {
            return -1;
        }
        double val;
        for (int i = 0; i < count; i++) {
            val = xValues[i];
            if (val == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return count >= 1 ? xValues[0] : 0;
    }

    @Override
    public double rightBound() {
        return count > 0 ? xValues[count - 1] : 0;
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int i=0;
            public boolean hasNext() {
                return (i != count);
            }
            public Point next() {
                if (i == count) {
                    throw new NoSuchElementException();
                }
              return new Point(xValues[i], yValues[i++]);
            }
        };
    }
}