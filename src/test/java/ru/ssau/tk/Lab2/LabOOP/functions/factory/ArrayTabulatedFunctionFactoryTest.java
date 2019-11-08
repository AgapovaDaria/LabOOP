package ru.ssau.tk.Lab2.LabOOP.functions.factory;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
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
}