package ru.ssau.tk.Lab2.LabOOP.operations;

import ru.ssau.tk.Lab2.LabOOP.functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {

    protected double step;

    SteppingDifferentialOperator(double step) {

        if (step <= 0 || Double.isNaN(step) || step == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException("шаг отрицательный, либо равен нулю, положительной бесконечности или NaN");
        }
        this.step = step;
    }


    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }
}
