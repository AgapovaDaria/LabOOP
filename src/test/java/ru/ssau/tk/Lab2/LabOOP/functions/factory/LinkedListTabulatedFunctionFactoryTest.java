package ru.ssau.tk.Lab2.LabOOP.functions.factory;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.functions.LinkedListTabulatedFunction;
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
}