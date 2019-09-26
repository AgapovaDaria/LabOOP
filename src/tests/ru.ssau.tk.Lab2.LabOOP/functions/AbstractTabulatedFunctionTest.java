package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {

    private  static final int LENGTH = 10;

    public AbstractTabulatedFunction tabulatedFunction = new AbstractTabulatedFunction(LENGTH) {
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
    };

    @Test
    public void testFloorIndexOfX() {
    }

    @Test
    public void testInterpolate() {
    }

    @Test
    public void testApply() {
    }
}