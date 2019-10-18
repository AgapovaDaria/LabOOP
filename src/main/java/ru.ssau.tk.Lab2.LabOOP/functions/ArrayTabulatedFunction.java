package ru.ssau.tk.Lab2.LabOOP.functions;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private double[] xValues;
    private double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {

        if (xValues.length < 2) {
            throw new IllegalArgumentException("Массив меньше данной длины");
        }
        /* Если длины разные, то общая длина = min
        if (xValues.length != yValues.length) count = Math.min(xValues.length, yValues.length);*/

        count = xValues.length;

        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {

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

        for (int i =0; i<count; i++) {
            xValues[i] = x;
            yValues[i] = source.apply(x);
            x += len;
        }

    }


    @Override
    protected int floorIndexOfX(double x)  {
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
        return interpolate(x, getX(floorIndex), getX(floorIndex + 1), getY(floorIndex), getY(floorIndex + 1));
    }

    @Override
    public double getX(int index) throws ArrayIndexOutOfBoundsException  {
        if (isCorrectIndex(index)) {
            return xValues[index];
        } else {
            return 0;
        }
    }

    @Override
    public double getY(int index) throws ArrayIndexOutOfBoundsException  {
        if (isCorrectIndex(index)) {
            return yValues[index];
        } else {
            return 0;
        }
    }

    @Override
    public void setY(int index, double value) throws ArrayIndexOutOfBoundsException {
        if (isCorrectIndex(index)) {
            yValues[index] = value;
        }
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

    private boolean isCorrectIndex(int index) {
        return index >= 0 && index < count;
    }
}
