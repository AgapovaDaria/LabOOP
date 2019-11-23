package ru.ssau.tk.Lab2.LabOOP.operations;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.functions.Point;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperatorTest {
    TabulatedFunctionFactory arrayFun = new ArrayTabulatedFunctionFactory();
    TabulatedFunctionFactory linkedList = new LinkedListTabulatedFunctionFactory();
    private double[] xValues = {5., 10., 15.};
    private double[] yValues = {6., 8., 10.};
    TabulatedFunction a = arrayFun.create(xValues, yValues);
    TabulatedFunction l = linkedList.create(xValues, yValues);

    @Test
    public void testDerive() {
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(linkedList);
        TabulatedFunction deriveOne = differentialOperator.derive(l);
        for (Point point : deriveOne) {
            assertEquals(point.y, 0.4);
        }
        TabulatedDifferentialOperator differentialOperatorOne = new TabulatedDifferentialOperator(arrayFun);
        TabulatedFunction deriveTwo = differentialOperatorOne.derive(a);
        for (Point point : deriveTwo) {
            assertEquals(point.y, 0.4);
        }
    }

    @Test
    public void testDeriveSynchronously() {
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(arrayFun);
        TabulatedFunction derive = differentialOperator.deriveSynchronously(a);
        for (Point point : derive) {
            assertEquals(point.y, 0.4);
        }
    }
}