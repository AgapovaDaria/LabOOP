package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.Lab2.LabOOP.exceptions.DifferentLengthOfArraysException;

import java.util.Iterator;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {

    private final double[] xValues = new double[]{1., 3., 2., 8., 12.};
    private final double[] yValues = new double[]{8., 24, 6., 5., 17., 16.,};

    private  AbstractTabulatedFunction function = new AbstractTabulatedFunction() {
        @Override
        protected int floorIndexOfX(double x) {
            return 0;
        }

        @Override
        protected double extrapolateLeft(double x) {
            return 0;
        }

        @Override
        protected double extrapolateRight(double x) {
            return 0;
        }

        @Override
        protected double interpolate(double x, int floorIndex) {
            return 0;
        }

        @Override
        public double getX(int index) {
            return 0;
        }

        @Override
        public double getY(int index) {
            return 0;
        }

        @Override
        public void setY(int index, double value) {

        }

        @Override
        public int indexOfX(double x) {
            return 0;
        }

        @Override
        public int indexOfY(double y) {
            return 0;
        }

        @Override
        public double leftBound() {
            return 0;
        }

        @Override
        public double rightBound() {
            return 0;
        }

        @Override
        public Iterator<Point> iterator() {
            return null;
        }
    };

    @Test
    public void testCheckLengthIsTheSame() {
        Assert.assertThrows(DifferentLengthOfArraysException.class,
                () -> function.checkLengthIsTheSame(xValues,yValues));
    }

    @Test
    public void testCheckSorted() {
        Assert.assertThrows(ArrayIsNotSortedException.class, () -> function.checkSorted(xValues));
    }


}
