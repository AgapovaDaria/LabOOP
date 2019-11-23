package ru.ssau.tk.Lab2.LabOOP.operations;

import ru.ssau.tk.Lab2.LabOOP.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.Point;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.TabulatedFunctionFactory;

import java.io.Serializable;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] arrPoints = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[arrPoints.length];
        double[] yValues = new double[arrPoints.length];
        for (int k = 0; k < xValues.length - 1; k++) {
            xValues[k] = arrPoints[k].x;
            yValues[k] = (arrPoints[k + 1].y - arrPoints[k].y) / (arrPoints[k + 1].x - arrPoints[k].x);
        }
        xValues[xValues.length - 1] = arrPoints[xValues.length - 1].x;
        yValues[yValues.length - 1] = yValues[yValues.length - 2];
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction deriveSynchronously(TabulatedFunction function) {
        if (!(function instanceof SynchronizedTabulatedFunction)) {
            SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);
            return synchronizedTabulatedFunction.doSynchronously(this::derive);
        }
        return ((SynchronizedTabulatedFunction) function).doSynchronously(this::derive);
    }
}