package ru.ssau.tk.Lab2.LabOOP.functions;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {

    protected int count;

    public AbstractTabulatedFunction(int length) {
        count = length;
    }

    protected int floorIndexOfX(double x) {
        int index = indexOfX(x);
        if (index != -1) return index;

        if (rightBound() < x) return count;

        for (int i = 0; i < getCount(); i++) {
            double value = getX(i);
            if (value < x) {
                index = i;
            }
        }
        if (index == -1) return 0;

        return index;
    }

    abstract protected double extrapolateLeft(double x);

    abstract protected double extrapolateRight(double x);

    abstract protected double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX));
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) return extrapolateLeft(x);
        if (x > rightBound()) return extrapolateRight(x);

        int i = indexOfX(x);
        if(i != -1) return getY(i);

        return  interpolate(x, floorIndexOfX(x));
    }

    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
    }

}
