package ru.ssau.tk.Lab2.LabOOP.functions;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private MathFunction testFunc = new ThirdFunction();
    private final double[] xValues = new double[]{8., 1., 12., 4., 3., 15., 6., 15.,};
    private final double[] yValues = new double[]{8., 6., 5., 17., 16., 7., 2., 25.,};

    private LinkedListTabulatedFunction Array_constructor() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

   /* private LinkedListTabulatedFunction Math_constructor () {
        return new LinkedListTabulatedFunction(testFunc, ,0 , 11);

    }*/
}
