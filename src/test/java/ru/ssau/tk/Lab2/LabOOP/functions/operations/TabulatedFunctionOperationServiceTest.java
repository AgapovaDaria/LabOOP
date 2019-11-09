package ru.ssau.tk.Lab2.LabOOP.functions.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.functions.AbstractTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.Point;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.Lab2.LabOOP.operations.TabulatedFunctionOperationService;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {

    double[] firstXValues = new double[]{1., 2., 3., 4.};
    double[] firstYValues = new double[]{7., 2., 15., 6.};
    double[] secondXValues = new double[]{1., 7., 8., 9.};
    double[] secondYValues = new double[]{12., 1., 6., 15.};


    @Test
    public void testAsPoints() {
        final double[] xValues = new double[]{1., 2., 3., 8., 12.};
        final double[] yValues = new double[]{5., 6., 8., 17., 24.};
        AbstractTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        int i = 0;
        for (Point point : points) {
            assertEquals(point.x, function.getX(i));
            assertEquals(point.y, function.getY(i++));
        }
    }

    @Test
    public void testSetFactory() {
        TabulatedFunctionOperationService tabulatedOperationService = new TabulatedFunctionOperationService();
        tabulatedOperationService.setFactory(new ArrayTabulatedFunctionFactory());
        assertTrue(tabulatedOperationService.getFactory() instanceof ArrayTabulatedFunctionFactory);
        tabulatedOperationService.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(tabulatedOperationService.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testGetFactory() {
        TabulatedFunctionOperationService tabulatedOperationService = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
        assertTrue(tabulatedOperationService.getFactory() instanceof ArrayTabulatedFunctionFactory);
        tabulatedOperationService = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        assertTrue(tabulatedOperationService.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }


    @Test
    public void testSum() {
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService tabulatedArray = new TabulatedFunctionOperationService(arrayFactory);
        TabulatedFunctionOperationService tabulatedList = new TabulatedFunctionOperationService(linkedListFactory);
        TabulatedFunction u = arrayFactory.create(firstXValues,firstYValues);
        TabulatedFunction v = linkedListFactory.create(firstXValues,secondYValues);
        TabulatedFunction sumArray = tabulatedArray.sum(u,v);
        TabulatedFunction sumList = tabulatedList.sum(u,v);
        int i = 0;
        for(Point point : sumArray){
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, firstYValues[i] + secondYValues[i++]);
        }
        i= 0;
        for(Point point : sumList){
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, firstYValues[i] + secondYValues[i++]);
        }
    }

    @Test
    public void testSubtract() {
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService tabulatedArray = new TabulatedFunctionOperationService(arrayFactory);
        TabulatedFunctionOperationService tabulatedList = new TabulatedFunctionOperationService(linkedListFactory);
        TabulatedFunction u = arrayFactory.create(firstXValues,firstYValues);
        TabulatedFunction v = linkedListFactory.create(firstXValues,secondYValues);
        TabulatedFunction subtractArray = tabulatedArray.subtract(u,v);
        TabulatedFunction subtractList = tabulatedList.subtract(u,v);
        int i = 0;
        for(Point point : subtractArray){
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, firstYValues[i] - secondYValues[i++]);
        }
        i= 0;
        for(Point point : subtractList){
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, firstYValues[i] - secondYValues[i++]);
        }
    }

    @Test
    public void testMultiplication() {
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService functionOperationServiceArray = new TabulatedFunctionOperationService(arrayFactory);
        TabulatedFunction a = arrayFactory.create(firstXValues, firstYValues);
        TabulatedFunction b = arrayFactory.create(firstXValues, secondYValues);
        TabulatedFunction resultMultiplicationArray = functionOperationServiceArray.multiplication(a, b);
        int i = 0;
        for (Point point : resultMultiplicationArray) {
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, firstYValues[i] * secondYValues[i++]);
        }
        TabulatedFunctionOperationService functionOperationServiceLinkedList = new TabulatedFunctionOperationService(linkedListFactory);
        TabulatedFunction aSecond = linkedListFactory.create(firstXValues, firstYValues);
        TabulatedFunction bSecond = linkedListFactory.create(firstXValues, secondYValues);
        TabulatedFunction resultMultiplicationLinkedList = functionOperationServiceLinkedList.multiplication(aSecond, bSecond);
        i = 0;
        for (Point point : resultMultiplicationLinkedList) {
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, firstYValues[i] * secondYValues[i++]);
        }
    }

    @Test
    public void testDivision() {
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService functionOperationServiceArray = new TabulatedFunctionOperationService(arrayFactory);
        TabulatedFunction a = arrayFactory.create(firstXValues, firstYValues);
        TabulatedFunction b = arrayFactory.create(firstXValues, secondYValues);
        TabulatedFunction resultDivisionArray = functionOperationServiceArray.division(a, b);
        int i = 0;
        for (Point point : resultDivisionArray) {
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, firstYValues[i] / secondYValues[i++]);
        }
        TabulatedFunctionOperationService functionOperationServiceLinkedList = new TabulatedFunctionOperationService(linkedListFactory);
        TabulatedFunction aSecond = linkedListFactory.create(firstXValues, firstYValues);
        TabulatedFunction bSecond = linkedListFactory.create(firstXValues, secondYValues);
        TabulatedFunction resultDivisionLinkedList = functionOperationServiceLinkedList.division(aSecond, bSecond);
        i = 0;
        for (Point point : resultDivisionLinkedList) {
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, firstYValues[i] / secondYValues[i++]);
        }
    }
}