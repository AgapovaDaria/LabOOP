package ru.ssau.tk.Lab2.LabOOP.functions.factory;

import ru.ssau.tk.Lab2.LabOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}
