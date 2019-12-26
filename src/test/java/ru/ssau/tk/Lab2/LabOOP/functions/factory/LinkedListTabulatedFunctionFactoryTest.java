package ru.ssau.tk.Lab2.LabOOP.functions.factory;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.StrictTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.UnmodifiableTabulatedFunction;


public class LinkedListTabulatedFunctionFactoryTest {
    private final double[] xValues = new double[]{1., 3., 4., 8., 12.};
    private final double[] yValues = new double[]{8., 6., 5., 17., 16.};
    private TabulatedFunction function = (new LinkedListTabulatedFunctionFactory().createUnmodifiable(xValues, yValues));

    @Test
    public void testCreate() {
        TabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction func = linkedListTabulatedFunctionFactory.create(xValues, yValues);
        assertTrue(func instanceof LinkedListTabulatedFunction);
    }

    @Test
    public void testCreateStrict() {
        TabulatedFunction list = (new LinkedListTabulatedFunctionFactory().createStrict(xValues, yValues));
        assertTrue(list instanceof StrictTabulatedFunction);
        assertEquals(list.getCount(), 5);
        assertEquals(list.getX(1), 3.);
        assertEquals(list.getY(0), 8.);
        list.setY(0, 4.);
        assertEquals(list.indexOfX(1), 0);
        assertEquals(list.indexOfY(6.), 1);
        assertEquals(list.leftBound(), 1.);
        assertEquals(list.rightBound(), 12.);
        assertEquals(list.iterator().next().x, 1);
        assertEquals(list.apply(3), 6);
        assertThrows(UnsupportedOperationException.class, () -> list.apply(2.5));
    }

    @Test
    public void testCreateUnmodifiable() {
        assertTrue(function instanceof UnmodifiableTabulatedFunction);
    }

    @Test
    public void testGetCount() {
        assertEquals(function.getCount(),5);
    }

    @Test
    public void testGetX() {
        assertEquals(function.getX(3), 8.);
    }

    @Test
    public void testGetY() {
        assertEquals(function.getY(1), 6.);
    }

    @Test
    public void testSetY() {
        assertThrows(UnsupportedOperationException.class, () -> function.setY(2, 1.));
    }

    @Test
    public void testApply() {
        assertEquals(function.apply(3), 6);
    }

    @Test
    public void testLeftBound() {
        assertEquals(function.leftBound(), 1.);
    }

    @Test
    public void testRightBound() {
        assertEquals(function.rightBound(), 12.);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(function.indexOfX(1.), 0);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(function.indexOfY(16.), 4);
    }

    @Test
    public void testIterator() {
        assertEquals(function.iterator().next().x, 1);
    }
}