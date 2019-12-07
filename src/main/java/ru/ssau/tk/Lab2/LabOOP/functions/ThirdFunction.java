package ru.ssau.tk.Lab2.LabOOP.functions;

public class ThirdFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return java.lang.Math.pow(x,3);
    }

    @Override
    public String toString() {
        return "Кубическая функция";
    }
}
