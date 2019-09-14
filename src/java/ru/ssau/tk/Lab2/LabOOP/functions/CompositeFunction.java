package ru.ssau.tk.Lab2.LabOOP.functions;

public class CompositeFunction implements MathFunction {
    private MathFunction firstFunction;
    private MathFunction secondFunction;

    public CompositeFunction(MathFunction firstFunction, MathFunction secondFunction) {
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }


    public double apply(double x) {
        return secondFunction.apply(firstFunction.apply(x));
    }

}
