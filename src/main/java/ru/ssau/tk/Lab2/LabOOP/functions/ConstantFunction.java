package ru.ssau.tk.Lab2.LabOOP.functions;

public class ConstantFunction implements MathFunction {

    private  final double  value;

    public ConstantFunction(int value){
        this.value = value;
    }

    @Override
    public double apply(double x) {
        return value;
    }

    public double getValue() {
        return value;
    }
}
