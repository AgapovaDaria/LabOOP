package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BinaryFunctionsTest {

    @Test
    public void zeroFunctionTest() {

        double x = Math.random();
        int value = 0;
        double result;
        MathFunction func = new ZeroFunction();

        result = func.apply(x);

        Assert.assertEquals(result, value);
    }

    @Test
    public void unitFunctionTest() {
        double x = Math.random();
        int value = 1;
        double result;
        MathFunction func = new UnitFunction();

        result = func.apply(x);

        Assert.assertEquals(result, value);
    }

}

