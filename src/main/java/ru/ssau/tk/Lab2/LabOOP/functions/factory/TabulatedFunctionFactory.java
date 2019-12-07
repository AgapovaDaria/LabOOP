package ru.ssau.tk.Lab2.LabOOP.functions.factory;


import ru.ssau.tk.Lab2.LabOOP.functions.MathFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.StrictTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
    TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count);
    default TabulatedFunction createStrict(double[] xValues, double[] yValues) {
        return new StrictTabulatedFunction(create(xValues, yValues));
    }
    default TabulatedFunction createStrict(MathFunction source, double xFrom, double xTo, int count) {
        return new StrictTabulatedFunction(create(source, xFrom,xTo,count));
    }
}
