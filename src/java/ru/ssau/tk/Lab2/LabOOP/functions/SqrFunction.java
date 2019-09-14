package ru.ssau.tk.Lab2.LabOOP.functions;

public class SqrFunction implements MathFunction{

    public SqrFunction(){

    }

    @Override
    public double apply(double x) {
        return Math.pow(x,2);
    }
}
