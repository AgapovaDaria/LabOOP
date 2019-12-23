package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.Lab2.LabOOP.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.Lab2.LabOOP.exceptions.InterpolationException;

import java.util.Iterator;

import static org.testng.Assert.assertEquals;

public class AbstractTabulatedFunctionTest {

    private final double[] xValues = new double[]{1., 3., 2., 8., 12.};
    private final double[] yValues = new double[]{8., 24, 6., 5., 17., 16.,};
    private double[] secondXValues = new double[]{5., 8., 9.};
    private double[] secondYValues = new double[]{20., 1., 5.};

    @Test
    public void testCheckLengthIsTheSame() {
        Assert.assertThrows(DifferentLengthOfArraysException.class,
                () -> AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues));
    }

    @Test
    public void testCheckSorted() {
        Assert.assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(xValues));
    }

    @Ignore
    @Test()
    public void testToString() {
        ArrayTabulatedFunction arrayFun = new ArrayTabulatedFunction(secondXValues, secondYValues);
        assertEquals(arrayFun.toString(), "ArrayTabulatedFunction size = 3\n[5.0; 20.0]\n[8.0; 1.0]\n[9.0; 5.0]");
        LinkedListTabulatedFunction linkedListFun = new LinkedListTabulatedFunction(secondXValues, secondYValues);
        assertEquals(linkedListFun.toString(), "LinkedListTabulatedFunction size = 3\n[5.0; 20.0]\n[8.0; 1.0]\n[9.0; 5.0]");
    }

    @Test
    public void testInterpolate() {
        MockTabulatedFunction mockFunction = new MockTabulatedFunction();
        double x = 1.5;
        assertEquals(mockFunction.interpolate(x, 1),3.5);
    }

    @Test
    public void testApply() {
        MockTabulatedFunction mockFunction = new MockTabulatedFunction();

        double x = 1.5;
        double x1 = 3;
        double x2 = 0;
        double x3 = 2;

        assertEquals(mockFunction.apply(x),3.5);
        assertEquals(mockFunction.apply(x1),5);
        assertEquals(mockFunction.apply(x2),2);
        assertEquals(mockFunction.apply(x3),4);
    }

    private class MockTabulatedFunction extends AbstractTabulatedFunction {

        private static final long serialVersionUID = 8123701083808580614L;
        private final double x0 = 1;
        private final double x1 = 2;
        private final double y0 = 3;
        private final double y1 = 4;

        int count = 2;

        MockTabulatedFunction(){
            super();
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        protected int floorIndexOfX(double x) {
            return 0;
        }

        @Override
        protected double extrapolateLeft(double x) {
            return 2;
        }

        @Override
        protected double extrapolateRight(double x) {
            return 5;
        }

        @Override
        protected double interpolate(double x, int floorIndex) {
            if (x < x0 || x > x1) throw new InterpolationException("х находится вне интервала интерполирования");
            return interpolate(x, x0, x1, y0, y1);
        }

        @Override
        public double getX(int index) {
            return x0;
        }

        @Override
        public double getY(int index) {
            return y1;
        }

        @Override
        public void setY(int index, double value) {
            //y1 = value;
        }

        @Override
        public int indexOfX(double x) {
            return x==x1 ? 1: x==x0? 0 :-1;
        }

        @Override
        public int indexOfY(double y) {
            return 0;
        }

        @Override
        public double leftBound() {
            return x0;
        }

        @Override
        public double rightBound() {
            return x1;
        }

        @Override
        public Iterator<Point> iterator() {
            return null;
        }
    }

}
