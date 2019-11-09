package ru.ssau.tk.Lab2.LabOOP.functions;

import java.util.Iterator;

public class StrictTabulatedFunction implements TabulatedFunction {

    private TabulatedFunction functionTab;

    public StrictTabulatedFunction (TabulatedFunction functionTab) {
        this.functionTab = functionTab;
    }

    @Override
    public int getCount() {
        return  functionTab.getCount();
    }

    @Override
    public double getX(int index) {
        return functionTab.getX(index);
    }

    @Override
    public double getY(int index) {
        return functionTab.getY(index);
    }

    @Override
    public void setY(int index, double value) {
        functionTab.setY(index,value);
    }

    @Override
    public int indexOfX(double x) {
        return functionTab.indexOfX(x);
    }

    @Override
    public int indexOfY(double y) {
        return functionTab.indexOfY(y);
    }

    @Override
    public double leftBound() {
        return functionTab.leftBound();
    }

    @Override
    public double rightBound() {
        return functionTab.rightBound();
    }


    @Override
    public Iterator<Point> iterator() {
        return functionTab.iterator();
    }

    @Override
    public double apply(double x) {
        if (functionTab.indexOfX(x) == -1) {
            throw new UnsupportedOperationException();
        } else {
            return functionTab.apply(x);
        }
    }
}
