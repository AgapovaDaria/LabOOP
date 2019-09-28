package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class LinkedListTabulatedFunctionTest {

    private MathFunction testFunc = new ThirdFunction();
    private final double[] xValues = new double[]{ 1.,3.,4.,8., 12.};
    private final double[] yValues = new double[]{8.,6.,5.,17.,16.,};

    private LinkedListTabulatedFunction ListOfArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction ListOfFunc() {
        int count = 8;
        return new LinkedListTabulatedFunction(testFunc, 15, 1, count);
    }

    private LinkedListTabulatedFunction ListElement() {
        int count = 6;
        return new LinkedListTabulatedFunction(testFunc, 8, 8, count);
    }

    @Test
    public void testAddNode() {
        LinkedListTabulatedFunction firstL = ListOfArray();
        firstL.addNode(5, 8);
        assertEquals(firstL.rightBound(), 5, 0.00001);
    }

  @Test

    public void testGetCount() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfFunc();
        assertEquals(firstList.getCount(), 5, 0.00001);
        assertEquals(secondList.getCount(), 8, 0.00001);

    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction one = ListOfArray();
        LinkedListTabulatedFunction two = ListOfFunc();
        assertEquals(one.interpolate(1.5, 0), 7.5);
        assertEquals(two.interpolate(6.5, 2), 288.5);
    }

    @Test
    public void testLeftBound() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfFunc();
        assertEquals(firstList.leftBound(), 1, 0.00001);
        assertEquals(secondList.leftBound(), 1, 0.00001);
    }

  @Test
    public void testRightBound() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfFunc();
        assertEquals(firstList.rightBound(), 12, 0.00001);
        assertEquals(secondList.rightBound(), 15, 0.00001);
    }

   @Test
    public void testGetX() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfFunc();
        assertEquals(firstList.getX(1), 3, 0.00001);
        assertEquals(secondList.getX(0), 1, 0.00001);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        firstList.setY(3, 16);
        assertEquals(firstList.getY(3), 16, 0.00001);
    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfFunc();
        assertEquals(firstList.getY(0), 8, 0.00001);
        assertEquals(secondList.getY(0), 1, 0.00001);

    }


    @Test
    public void testIndexOfX() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfFunc();
        assertEquals(firstList.indexOfX(1), 0);
        assertEquals(secondList.indexOfX(1), 0);
    }

    @Test
    public void testIndexOfY(){
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfFunc();
        assertEquals(firstList.indexOfY(6),1 );
        assertEquals(secondList.indexOfY(0),-1 );
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        assertEquals(firstList.floorIndexOfX(8.1), 3);
        LinkedListTabulatedFunction secondList = ListOfFunc();
        assertEquals(secondList.floorIndexOfX(2.16),0 );
    }
}

