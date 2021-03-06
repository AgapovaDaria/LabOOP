package ru.ssau.tk.Lab2.LabOOP.functions.factory;

import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.MathFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;


public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    @Override
    public TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count) {
        return new ArrayTabulatedFunction(source, xFrom, xTo, count);
    }

}
