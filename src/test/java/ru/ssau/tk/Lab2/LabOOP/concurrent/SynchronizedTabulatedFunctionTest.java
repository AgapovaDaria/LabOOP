package ru.ssau.tk.Lab2.LabOOP.concurrent;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import ru.ssau.tk.Lab2.LabOOP.functions.*;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SynchronizedTabulatedFunctionTest {

    private MathFunction testFunc = new ThirdFunction();
    private final double[] xValues = new double[]{1., 3., 4., 8., 12.};
    private final double[] yValues = new double[]{8., 6., 5., 17., 16.};

    private SynchronizedTabulatedFunction arrayConsistingOfTwoArrays() {
        TabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        return new SynchronizedTabulatedFunction(function);
    }

    private SynchronizedTabulatedFunction arrayOfFunctions() {
        int count = 8;
        TabulatedFunction function = new ArrayTabulatedFunction(testFunc, 15, 1, count);
        return new SynchronizedTabulatedFunction(function);
    }

    @Test
    public void testIterator() {
        SynchronizedTabulatedFunction firstArray = arrayOfFunctions();
        Iterator<Point> iterator = firstArray.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            System.out.println(point.x);
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

    @Test
    public void testGetCount() {
        SynchronizedTabulatedFunction firstArray = arrayConsistingOfTwoArrays();
        SynchronizedTabulatedFunction secondArray = arrayOfFunctions();
        assertEquals(firstArray.getCount(), 5, 0.00001);
        assertEquals(secondArray.getCount(), 8, 0.00001);
    }

    @Test
    public void testGetX() {
        SynchronizedTabulatedFunction firstArray = arrayConsistingOfTwoArrays();
        SynchronizedTabulatedFunction secondArray = arrayOfFunctions();
        assertEquals(firstArray.getX(1), 3, 0.00001);
        assertEquals(secondArray.getX(0), 1, 0.00001);
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            firstArray.getX(-5);
        });
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            secondArray.getX(100);
        });
    }

    @Test
    public void testGetY() {
        SynchronizedTabulatedFunction firstArray = arrayConsistingOfTwoArrays();
        SynchronizedTabulatedFunction secondArray = arrayOfFunctions();
        assertEquals(firstArray.getY(0), 8, 0.00001);
        assertEquals(secondArray.getY(0), 1, 0.00001);
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            firstArray.getY(-100);
        });
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            secondArray.getY(60);
        });
    }

    @Test
    public void testSetY() {
        SynchronizedTabulatedFunction firstArray = arrayConsistingOfTwoArrays();
        SynchronizedTabulatedFunction secondArray = arrayOfFunctions();
        firstArray.setY(4, 16);
        assertEquals(firstArray.getY(4), 16, 0.00001);
        secondArray.setY(0, 8);
        assertEquals(firstArray.getY(0), 8, 0.00001);
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            firstArray.setY(-5, 500);
        });
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            secondArray.setY(100, 100);
        });
    }

    @Test
    public void testIndexOfX() {
        SynchronizedTabulatedFunction firstArray = arrayConsistingOfTwoArrays();
        SynchronizedTabulatedFunction secondArray = arrayOfFunctions();
        assertEquals(firstArray.indexOfX(1), 0);
        assertEquals(secondArray.indexOfX(1), 0);
        assertEquals(secondArray.indexOfX(12), -1);
    }

    @Test
    public void testIndexOfY() {
        SynchronizedTabulatedFunction firstArray = arrayConsistingOfTwoArrays();
        SynchronizedTabulatedFunction secondArray = arrayOfFunctions();
        assertEquals(firstArray.indexOfY(6), 1);
        assertEquals(secondArray.indexOfY(0), -1);
        assertEquals(firstArray.indexOfY(8), 0);
    }

    @Test
    public void testLeftBound() {
        SynchronizedTabulatedFunction firstArray = arrayConsistingOfTwoArrays();
        SynchronizedTabulatedFunction secondArray = arrayOfFunctions();
        Assert.assertEquals(firstArray.leftBound(), 1., 0.00001);
        Assert.assertEquals(secondArray.leftBound(), 1, 0.00001);
    }

    @Test
    public void testRightBound() {
        SynchronizedTabulatedFunction firstArray = arrayConsistingOfTwoArrays();
        SynchronizedTabulatedFunction secondArray = arrayOfFunctions();
        Assert.assertEquals(firstArray.rightBound(), 12., 0.00001);
        Assert.assertEquals(secondArray.rightBound(), 15, 0.00001);
    }

    @Test
    public void testApply() {
        SynchronizedTabulatedFunction firstArray = arrayConsistingOfTwoArrays();
        assertEquals(firstArray.apply(0.), 9., 0.00001);
        assertEquals(firstArray.apply(10.), 16.5, 0.00001);
    }

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void testDoSynchronously() {
        SynchronizedTabulatedFunction fun = arrayOfFunctions();

        SynchronizedTabulatedFunction.Operation<Void> oneOperation = synchronizedTabulatedFunction -> {
            synchronizedTabulatedFunction.setY(0, 9);
            return null;
        };

        SynchronizedTabulatedFunction.Operation<Integer> twoOperation = SynchronizedTabulatedFunction::getCount;

        assertNull(fun.doSynchronously(oneOperation));
        assertEquals((int)(fun.doSynchronously(twoOperation)), 8);
    }
}