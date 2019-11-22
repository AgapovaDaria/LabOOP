package ru.ssau.tk.Lab2.LabOOP.functions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class StrictTabulatedFunctionTest {
    private final double[] xValues = new double[]{1., 3., 4., 8., 12.};
    private final double[] yValues = new double[]{8., 6., 5., 17., 16.};

    @Test
    public void check() {
        TabulatedFunction array = new StrictTabulatedFunction(new ArrayTabulatedFunction(xValues, yValues));
        assertEquals(array.getCount(), 5);
        assertEquals(array.getX(1), 3.);
        assertEquals(array.getY(0), 8.);
        array.setY(0, 4.);
        assertEquals(array.indexOfX(1), 0);
        assertEquals(array.indexOfY(6.), 1);
        assertEquals(array.leftBound(), 1.);
        assertEquals(array.rightBound(), 12.);
        assertEquals(array.iterator().next().x, 1);
        assertEquals(array.apply(3), 6);
        assertThrows(UnsupportedOperationException.class, () -> array.apply(2.5));
        TabulatedFunction list = new StrictTabulatedFunction(new LinkedListTabulatedFunction(xValues, yValues));
        assertEquals(list.getCount(), 5);
        assertEquals(list.getX(4), 12.);
        assertEquals(list.getY(4), 16.);
        list.setY(4, 6.);
        assertEquals(list.indexOfX(4.), 2);
        assertEquals(list.indexOfY(17), 3);
        assertEquals(list.leftBound(), 1.);
        assertEquals(list.rightBound(), 12.);
        assertEquals(list.iterator().next().x, 1);
        assertEquals(list.apply(4), 5);
        assertThrows(UnsupportedOperationException.class, () -> list.apply(3.5));
    }
}