package ru.ssau.tk.Lab2.LabOOP.functions.factory;


import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
