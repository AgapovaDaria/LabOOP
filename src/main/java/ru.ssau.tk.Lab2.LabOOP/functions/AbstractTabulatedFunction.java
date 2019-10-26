package ru.ssau.tk.Lab2.LabOOP.functions;
import ru.ssau.tk.Lab2.LabOOP.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.Lab2.LabOOP.exceptions.DifferentLengthOfArraysException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {

    protected int count;

    AbstractTabulatedFunction() {
    }
    protected abstract int floorIndexOfX(double x);

    abstract protected double extrapolateLeft(double x);

    abstract protected double extrapolateRight(double x);

    abstract protected double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX));
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }

        int i = indexOfX(x);
        if (i != -1) {
            return getY(i);
        }

        return interpolate(x, floorIndexOfX(x));
    }

    public int getCount() {
        return count;
    }


    static void checkLengthIsTheSame(double[] xValues, double[] yValues) throws DifferentLengthOfArraysException{

        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Массивы разной длины");
        }

    }

    static void checkSorted(double[] xValues ) throws ArrayIsNotSortedException {
        for(int i = 0; i < xValues.length-1; i++){
            if(xValues[i+1] <= xValues[i] ){
                throw new ArrayIsNotSortedException("Массив не отсортирован");
            }

        }

    }
}