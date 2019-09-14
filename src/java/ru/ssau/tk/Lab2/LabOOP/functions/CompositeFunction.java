package ru.ssau.tk.Lab2.LabOOP.functions;

public class CompositeFunction implements MathFunction {

    private MathFunction oldFunction;
    private MathFunction newFunction;

    public CompositeFunction(MathFunction oldFunction, MathFunction function) {
        this.oldFunction = oldFunction;
        this.newFunction = function;
    }

    @Override
    public double apply(double x) {
        return oldFunction.apply(newFunction.apply(x));
    }
}
