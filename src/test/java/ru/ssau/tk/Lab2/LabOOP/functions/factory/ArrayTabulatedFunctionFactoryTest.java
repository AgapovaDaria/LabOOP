package ru.ssau.tk.Lab2.LabOOP.functions.factory;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.StrictTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactoryTest {
    private final double[] xValues = new double[]{1., 3., 4., 8., 12.};
    private final double[] yValues = new double[]{8., 6., 5., 17., 16.};

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
}