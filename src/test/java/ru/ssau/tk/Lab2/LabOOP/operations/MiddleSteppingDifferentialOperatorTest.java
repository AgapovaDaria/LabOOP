package ru.ssau.tk.Lab2.LabOOP.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.functions.MathFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.SqrFunction;

import static org.testng.Assert.assertEquals;

public class MiddleSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {

        double step = 0.0001;
        SteppingDifferentialOperator differentialOperator = new MiddleSteppingDifferentialOperator(step);
        MathFunction function = new SqrFunction();

        assertEquals(differentialOperator.derive(function).apply(2), 4E-8, 0.00001);

    }
}