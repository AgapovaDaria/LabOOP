package operations;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.MathFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.SqrFunction;

import static org.testng.Assert.*;

public class LeftSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {

        double step = 0.0001;
        SteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(step);
        MathFunction function = new SqrFunction();

        assertEquals(differentialOperator.derive(function).apply(2), 3.9999, 0.00001);

    }
}