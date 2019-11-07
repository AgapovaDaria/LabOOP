package operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.functions.AbstractTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.Point;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {

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
    }

    @Test
    public void testGetFactory() {
    }


    @Test
    public void testSum() {
    }

    @Test
    public void testSubtract() {
    }

    @Test
    public void testMultiplication() {
    }

    @Test
    public void testDivision() {
    }
}