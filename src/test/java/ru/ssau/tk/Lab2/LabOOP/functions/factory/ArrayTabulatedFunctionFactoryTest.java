package ru.ssau.tk.Lab2.LabOOP.functions.factory;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.StrictTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.UnmodifiableTabulatedFunction;

public class ArrayTabulatedFunctionFactoryTest {
    private final double[] xValues = new double[]{1., 3., 4., 8., 12.};
    private final double[] yValues = new double[]{8., 6., 5., 17., 16.};
    private TabulatedFunction function = (new ArrayTabulatedFunctionFactory().createUnmodifiable(xValues, yValues));

    @Test
    public void testCreate() {
        TabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction func = arrayTabulatedFunctionFactory.create(xValues, yValues);
        assertTrue(func instanceof ArrayTabulatedFunction); //Оператор instanceof нужен, чтобы проверить, был ли объект,
        // на который ссылается , создан на основе какого-либо класса.
    }

    @Test
    public void testCreateStrict() {
        TabulatedFunction array = (new ArrayTabulatedFunctionFactory().createStrict(xValues, yValues));
        assertTrue(array instanceof StrictTabulatedFunction);
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