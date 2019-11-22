package ru.ssau.tk.Lab2.LabOOP.functions.factory;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.StrictTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;


public class LinkedListTabulatedFunctionFactoryTest {
    private final double[] xValues = new double[]{1., 3., 4., 8., 12.};
    private final double[] yValues = new double[]{8., 6., 5., 17., 16.};

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
}