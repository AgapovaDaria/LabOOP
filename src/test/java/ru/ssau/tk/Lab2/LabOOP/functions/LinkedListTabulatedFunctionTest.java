package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;


public class LinkedListTabulatedFunctionTest {

    private MathFunction testFunc = new ThirdFunction();
    private final double[] xValues = new double[]{1., 3., 4., 8., 12.};
    private final double[] yValues = new double[]{8., 6., 5., 17., 16.};

    private LinkedListTabulatedFunction listOfList() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction listOfFunc() {
        int count = 8;
        return new LinkedListTabulatedFunction(testFunc, 15, 1, count);
    }

    private LinkedListTabulatedFunction listElement() {
        int count = 6;
        return new LinkedListTabulatedFunction(testFunc, 8, 8, count);
    }

    @Test
    public void testAddNode() {
        LinkedListTabulatedFunction firstL = listOfList();
        firstL.addNode(5, 8);
        assertEquals(firstL.rightBound(), 5, 0.00001);
        assertEquals(xValues.length, 5, 0.00001);
        assertEquals(firstL.getCount(), 6, 0.00001);
    }

    @Test

    public void testGetCount() {
        LinkedListTabulatedFunction firstList = listOfList();
        LinkedListTabulatedFunction secondList = listOfFunc();
        assertEquals(firstList.getCount(), 5, 0.00001);
        assertEquals(secondList.getCount(), 8, 0.00001);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction one = listOfList();
        LinkedListTabulatedFunction two = listOfFunc();
        assertEquals(one.interpolate(1.5, 0), 7.5);
        assertEquals(two.interpolate(6.5, 2), 288.5);
    }

    @Test
    public void testLeftBound() {
        LinkedListTabulatedFunction firstList = listOfList();
        LinkedListTabulatedFunction secondList = listOfFunc();
        assertEquals(firstList.leftBound(), 1, 0.00001);
        assertEquals(secondList.leftBound(), 1, 0.00001);
    }

    @Test
    public void testRightBound() {
        LinkedListTabulatedFunction firstList = listOfList();
        LinkedListTabulatedFunction secondList = listOfFunc();
        assertEquals(firstList.rightBound(), 12, 0.00001);
        assertEquals(secondList.rightBound(), 15, 0.00001);
    }

    @Test
    public void testGetX() {
        LinkedListTabulatedFunction firstList = listOfList();
        LinkedListTabulatedFunction secondList = listOfFunc();
        assertEquals(firstList.getX(1), 3, 0.00001);
        assertEquals(secondList.getX(0), 1, 0.00001);
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            firstList.getX(-5);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            secondList.getX(100);
        });
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction firstList = listOfList();
        LinkedListTabulatedFunction twoList = listOfList();
        firstList.setY(4, 16);
        assertEquals(firstList.getY(4), 16, 0.00001);
        twoList.setY(0, 8);
        assertEquals(firstList.getY(0), 8, 0.00001);
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            firstList.setY(-5, 500);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            twoList.setY(100, 100);
        });
    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction firstList = listOfList();
        LinkedListTabulatedFunction secondList = listOfFunc();
        assertEquals(firstList.getY(0), 8, 0.00001);
        assertEquals(secondList.getY(0), 1, 0.00001);
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            firstList.getY(-100);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            secondList.getY(60);
        });
    }


    @Test
    public void testIndexOfX() {
        LinkedListTabulatedFunction firstList = listOfList();
        LinkedListTabulatedFunction secondList = listOfFunc();
        assertEquals(firstList.indexOfX(1), 0);
        assertEquals(secondList.indexOfX(1), 0);
        assertEquals(secondList.indexOfX(12), -1);
    }

    @Test
    public void testIndexOfY() {
        LinkedListTabulatedFunction firstList = listOfList();
        LinkedListTabulatedFunction secondList = listOfFunc();
        assertEquals(firstList.indexOfY(6), 1);
        assertEquals(secondList.indexOfY(0), -1);
        assertEquals(firstList.indexOfY(8), 0);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction firstList = listOfList();
        assertEquals(firstList.floorIndexOfX(8.1), 3);
        LinkedListTabulatedFunction secondList = listOfFunc();
        assertEquals(secondList.floorIndexOfX(2.16), 0);
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            firstList.floorIndexOfX(0);
        });
    }

    @Test
    public void testExtrapolateLeft() {
        LinkedListTabulatedFunction firstList = listOfList();
        LinkedListTabulatedFunction secondList = listOfFunc();
        assertEquals(firstList.extrapolateLeft(0), 9);
        assertEquals(secondList.extrapolateLeft(0), -12);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction firstList = listOfList();
        LinkedListTabulatedFunction secondList = listOfFunc();
        assertEquals(firstList.extrapolateRight(12.5), 15.875);
        assertEquals(secondList.extrapolateRight(16), 3964);
    }

    @Test
    public void testIterator() {
        LinkedListTabulatedFunction firstArray = listOfList();
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
    public void testApply() {
        LinkedListTabulatedFunction firstList = listOfList();
        assertEquals(firstList.apply(0), 9, 0.0001);
        assertEquals(firstList.apply(8), 17, 0.0001);
        assertEquals(firstList.apply(6.5), firstList.interpolate(6.5, 2), 0.0001);
    }
}

