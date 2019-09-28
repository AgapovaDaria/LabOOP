package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TangFunctionTest {

    @Test
    public void testApply() {
        //arrange
        double x = 13.3;
        MathFunction func = new TangFunction();
        double result;
        double trueResult;
        //art
        result = func.apply(x);
        trueResult = Math.tan(x);
        //assert
        Assert.assertEquals(result,trueResult);
    }
}