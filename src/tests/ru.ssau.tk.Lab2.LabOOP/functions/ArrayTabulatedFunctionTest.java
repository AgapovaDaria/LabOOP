package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {


    private int count;

    private double xFrom = 1.5;
    private double xTo = 9;

    private double[] masX = new double[]{1.5, 5.25, 9};
    private double[] masY;

    private MathFunction source = (x) -> x * x;
    private AbstractTabulatedFunction function_one;
    private AbstractTabulatedFunction function_two;


    @BeforeClass
    public void startUp() {
        count = masX.length;
        masY = new double[count];

        for (int i = 0; i < masX.length; i++) {
            masY[i] = source.apply(masX[i]);
        }

        function_one = new ArrayTabulatedFunction(masX, masY);
        function_two = new ArrayTabulatedFunction(source, xFrom, xTo, count);

    }

    @Test(dependsOnMethods = {"testGetX", "testGetY"})
    public void testValues() {
        for (int i = 0; i < count; i++) {
            Assert.assertEquals(function_one.getX(i), function_two.getX(i));
            Assert.assertEquals(function_one.getY(i), function_two.getY(i));
        }
    }

    @Test
    public void testExtrapolateLeft() {
        //
    }

    @Test
    public void testExtrapolateRight() {
        //
    }

    @Test
    public void testInterpolate() {
        //
    }

    //Зависимый тест
    @Test(dependsOnMethods = {"testExtrapolateLeft", "testExtrapolateRight", "testInterpolate", "testGetY", "testIndexOfX"})
    public void testApply() {
        double leftValue = masX[0] - 1;
        double rightValue = masX[count - 1] + 1;
        double value = masX[count - 1];
        double middleValue = (masX[0] + masX[1]) / 2;

        Assert.assertEquals(function_two.extrapolateLeft(leftValue), function_two.apply(leftValue));
        Assert.assertEquals(function_two.extrapolateRight(rightValue), function_two.apply(rightValue));
        Assert.assertEquals(function_two.getY(count - 1), function_two.apply(value));
        Assert.assertEquals(function_one.interpolate(middleValue, function_one.floorIndexOfX(middleValue)), function_one.apply(middleValue));
    }

    @Test
    public void testGetX() {
        Assert.assertEquals(masX[count - 1], function_one.getX(count - 1));
        Assert.assertEquals(masX[count - 1], function_two.getX(count - 1));
    }

    @Test
    public void testGetY() {
        Assert.assertEquals(masY[count - 1], function_one.getY(count - 1));
        Assert.assertEquals(masY[count - 1], function_two.getY(count - 1));
    }

    @Test
    public void testIndexOfX() {
        Assert.assertEquals(-1, function_one.indexOfX(masX[0] - 1));
        Assert.assertEquals(-1, function_one.indexOfX(masX[count - 1] + 1));
        Assert.assertEquals(1, function_one.indexOfX(masX[1]));
        Assert.assertEquals(count - 1, function_one.indexOfX(masX[count - 1]));
    }

    @Test
    public void testIndexOfY() {
        Assert.assertEquals(-1, function_one.indexOfY(masY[0] - 1));
        Assert.assertEquals(-1, function_one.indexOfY(masY[count - 1] + 1));
        Assert.assertEquals(1, function_one.indexOfY(masY[1]));
        Assert.assertEquals(count - 1, function_one.indexOfY(masY[count - 1]));
    }

    @Test
    public void testLeftBound() {
        Assert.assertEquals(masX[0], function_one.leftBound());
        Assert.assertEquals(masX[0], function_two.leftBound());
    }

    @Test
    public void testRightBound() {
        Assert.assertEquals(masX[count - 1], function_one.rightBound());
        Assert.assertEquals(masX[count - 1], function_two.rightBound());
    }
}