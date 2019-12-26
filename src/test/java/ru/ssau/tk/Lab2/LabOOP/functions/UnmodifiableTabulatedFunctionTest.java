package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class UnmodifiableTabulatedFunctionTest {

    private AbstractTabulatedFunction linkedListTabulatedFunction;
    private AbstractTabulatedFunction arrayTabulatedFunction;
    UnmodifiableTabulatedFunction function1;
    UnmodifiableTabulatedFunction function2;
    private final double[] xValues = new double[]{1., 3., 4., 8., 12.};
    private final double[] yValues = new double[]{8., 6., 5., 17., 16.};


    @BeforeTest
    public void setUp() {
        linkedListTabulatedFunction = new LinkedListTabulatedFunction(xValues,yValues);
        arrayTabulatedFunction = new ArrayTabulatedFunction(xValues,yValues);

        function1 = new UnmodifiableTabulatedFunction(linkedListTabulatedFunction);
        function2 = new UnmodifiableTabulatedFunction(arrayTabulatedFunction);
    }

    @Test
    public void testGetCount() {
        int result = linkedListTabulatedFunction.getCount();
        assertEquals(function1.getCount(), result);
        assertEquals(function2.getCount(), arrayTabulatedFunction.getCount());
    }

    @Test
    public void testGetX() {
        assertEquals(function1.getX(0), 1.);
        assertEquals(function2.getX(0), 1.);
    }

    @Test
    public void testGetY() {
        assertEquals(function1.getY(0), 8.);
        assertEquals(function2.getY(0), 8.);
    }

    @Test
    public void testSetY() {
        assertThrows(UnsupportedOperationException.class, () -> function1.setY(1, 3.));
        assertThrows(UnsupportedOperationException.class, () -> function2.setY(2, 1.));
    }

    @Test
    public void testApply() {
        assertEquals(function1.apply(4), 5);
        assertEquals(function2.apply(3), 6);

    }
}