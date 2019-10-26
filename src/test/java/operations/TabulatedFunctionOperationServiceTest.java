package operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.functions.AbstractTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {

    @Test
    public void testAsPoints() {
        final double[] xValues = new double[]{1., 3., 2., 8., 12.};
        final double[] yValues = new double[]{8., 24, 6., 5., 17., 16.,};
        AbstractTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

    }
}