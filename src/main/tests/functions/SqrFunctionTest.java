package functions;

import org.testng.Assert;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @org.testng.annotations.Test
    public void testApply() {
        // arrange
        Double x = 12.5;
        MathFunction func = new SqrFunction();
        double result;

        //act
        result = func.apply(x);

        //assert
        Assert.assertEquals(result, Math.pow(x, 2));
    }
}