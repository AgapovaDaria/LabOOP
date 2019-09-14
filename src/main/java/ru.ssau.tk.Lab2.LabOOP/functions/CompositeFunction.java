package ru.ssau.tk.Lab2.LabOOP.functions;

public class CompositeFunction implements ru.ssau.tk.Lab2.LabOOP.functions.MathFunction {
    private ru.ssau.tk.Lab2.LabOOP.functions.MathFunction firstFunction;
    private ru.ssau.tk.Lab2.LabOOP.functions.MathFunction secondFunction;

    public CompositeFunction(ru.ssau.tk.Lab2.LabOOP.functions.MathFunction firstFunction, ru.ssau.tk.Lab2.LabOOP.functions.MathFunction secondFunction) {
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }


    @Override
    public double apply(double x) {
        return secondFunction.apply(firstFunction.apply(x));
    }

}
