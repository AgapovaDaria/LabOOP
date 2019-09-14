package ru.ssau.tk.Lab2.LabOOP.functions;

public class TangFunction implements MathFunction{

    @Override
    public double apply(double x) {
        return Math.tan(x);
    }
}

