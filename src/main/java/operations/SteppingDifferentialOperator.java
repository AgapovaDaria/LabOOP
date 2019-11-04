package operations;

import ru.ssau.tk.Lab2.LabOOP.functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {

    protected double step;

    public SteppingDifferentialOperator(double step) {

        if (step <= 0 || Double.isNaN(step) || step == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException(" ");
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
