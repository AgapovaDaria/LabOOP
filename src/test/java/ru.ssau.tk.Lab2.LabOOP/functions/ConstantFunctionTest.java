package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConstantFunctionTest {

    @Test
    public void testApply() {
        //arrange
        double result;
        double x = 10.3;
        double value = 9;
        MathFunction func = new ConstantFunction(x);


        //act
        result = func.apply(value);

        //assert
        Assert.assertEquals(result, x);
    }
}