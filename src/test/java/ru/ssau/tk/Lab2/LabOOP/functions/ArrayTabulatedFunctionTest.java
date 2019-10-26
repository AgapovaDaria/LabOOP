package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ArrayTabulatedFunctionTest {


    private int count;

    private final static double xFrom = 1.5;
    private final static double xTo = 9;

    private double[] masX = new double[]{1.5, 5.25, 9};
    private double[] masY;

    private MathFunction source = (x) -> x * x;
    private AbstractTabulatedFunction functionOne;
    private AbstractTabulatedFunction functionTwo;

    @Test
    public void testIllegalArgumentExceptionInArrayMathFunction() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ArrayTabulatedFunction(source, 0, 0, 1);
        });
    }
    @BeforeClass
    public void startUp() {
        count = masX.length;
        masY = new double[count];

        for (int i = 0; i < masX.length; i++) {
            masY[i] = source.apply(masX[i]);
        }

        functionOne = new ArrayTabulatedFunction(masX, masY);
        functionTwo = new ArrayTabulatedFunction(source, xFrom, xTo, count);

    }

    @Test(dependsOnMethods = {"testGetX", "testGetY"})
    public void testValues() {
        for (int i = 0; i < count; i++) {
            Assert.assertEquals(functionOne.getX(i), functionTwo.getX(i));
            Assert.assertEquals(functionOne.getY(i), functionTwo.getY(i));
        }
    }

    @Test(dependsOnMethods = {"testGetY", "testGetX"})
    public void testExtrapolateLeft() {

        AbstractTabulatedFunction fun2 = functionTwo;
        double xNum = fun2.getX(1) - 0.25; // ==1.0
        int index = fun2.floorIndexOfX(xNum);

        double trueResult = fun2.getY(0) + ((fun2.getY(1) - (fun2.getY(0))) /
                ((fun2.getX(1) - (fun2.getX(0))))) * (xNum - fun2.getX(0));
        double result = fun2.extrapolateLeft(xNum);

        Assert.assertEquals(trueResult, result, 0.00001);
    }

    @Test(dependsOnMethods = {"testGetY", "testGetX"})
    public void testExtrapolateRight() {
        AbstractTabulatedFunction fun1 = functionTwo;
        double xNum = fun1.getX(count - 1) + 1.; // ==
        int index = fun1.floorIndexOfX(xNum);

        double trueResult = fun1.getY(count - 2) + ((fun1.getY(count - 1) - (fun1.getY(count - 2))) /
                ((fun1.getX(count - 1) - (fun1.getX(count - 2))))) * (xNum - (fun1.getX(count - 2)));
        double result = fun1.extrapolateRight(xNum);

        Assert.assertEquals(trueResult, result, 0.00001);
    }

    @Test(dependsOnMethods = {"testGetY", "testGetX"})
    public void testInterpolate() {
        AbstractTabulatedFunction fun = functionTwo;
        double xNum = (fun.getX(2) + functionOne.getX(1)) / 2; // ==1.875
        int index = fun.floorIndexOfX(xNum);

        double trueResult = fun.getY(1) + ((fun.getY(2) - (fun.getY(1))) /
                ((fun.getX(2) - (fun.getX(1))))) * (xNum - (fun.getX(1)));
        double result = fun.interpolate(xNum, index);

        Assert.assertEquals(trueResult, result, 0.00001);
    }

    //Зависимый тест
    @Test(dependsOnMethods = {"testExtrapolateLeft", "testExtrapolateRight", "testInterpolate", "testGetY", "testIndexOfX"})
    public void testApply() {
        double leftValue = masX[0] - 1;
        double rightValue = masX[count - 1] + 1;
        double value = masX[count - 1];
        double middleValue = (masX[0] + masX[1]) / 2;

        Assert.assertEquals(functionTwo.extrapolateLeft(leftValue), functionTwo.apply(leftValue));
        Assert.assertEquals(functionTwo.extrapolateRight(rightValue), functionTwo.apply(rightValue));
        Assert.assertEquals(functionTwo.getY(count - 1), functionTwo.apply(value));
        Assert.assertEquals(functionOne.interpolate(middleValue, functionOne.floorIndexOfX(middleValue)), functionOne.apply(middleValue));
    }

    @Test
    public void testGetX() {
        Assert.assertEquals(masX[count - 1], functionOne.getX(count - 1));
        Assert.assertEquals(masX[count - 1], functionTwo.getX(count - 1));
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            functionOne.getX(-6);
        });
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            functionTwo.getX(30);
        });
    }

    @Test
    public void testGetY() {
        Assert.assertEquals(masY[count - 1], functionOne.getY(count - 1));
        Assert.assertEquals(masY[count - 1], functionTwo.getY(count - 1));
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            functionOne.getY(-4);
        });
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            functionTwo.getY(25);
        });
    }

    @Test
    public void testSetY(){
        functionOne.setY(2,81);
        Assert.assertEquals(81, functionOne.getY(2), 0.0001);
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            functionOne.setY(-12,50);
        });
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            functionTwo.setY(100,20);
        });
    }

    @Test
    public void testIndexOfX() {
        Assert.assertEquals(-1, functionOne.indexOfX(masX[0] - 1));
        Assert.assertEquals(-1, functionOne.indexOfX(masX[count - 1] + 1));
        Assert.assertEquals(1, functionOne.indexOfX(masX[1]));
        Assert.assertEquals(count - 1, functionOne.indexOfX(masX[count - 1]));

    }

    @Test
    public void testIndexOfY() {
        Assert.assertEquals(-1, functionOne.indexOfY(masY[0] - 1));
        Assert.assertEquals(-1, functionOne.indexOfY(masY[count - 1] + 1));
        Assert.assertEquals(1, functionOne.indexOfY(masY[1]));
        Assert.assertEquals(count - 1, functionOne.indexOfY(masY[count - 1]));
    }

    @Test
    public void testLeftBound() {
        Assert.assertEquals(masX[0], functionOne.leftBound());
        Assert.assertEquals(masX[0], functionTwo.leftBound());
    }

    @Test
    public void testRightBound() {
        Assert.assertEquals(masX[count - 1], functionOne.rightBound());
        Assert.assertEquals(masX[count - 1], functionTwo.rightBound());
    }

    @Test
    public void testIterator() {
        AbstractTabulatedFunction firstArray = new ArrayTabulatedFunction(masX,masY);
        Iterator<Point> iterator = firstArray.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(point.x, firstArray.getX(i), 0.0001);
            assertEquals(point.y, firstArray.getY(i++), 0.0001);
        }
        i = 0;
        for (Point point : firstArray) {
            assertEquals(point.x, firstArray.getX(i), 0.0001);
            assertEquals(point.y, firstArray.getY(i++), 0.0001);
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}