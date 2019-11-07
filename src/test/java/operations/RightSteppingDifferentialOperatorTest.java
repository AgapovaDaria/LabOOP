package operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Lab2.LabOOP.functions.MathFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.SqrFunction;

import static org.testng.Assert.*;

public class RightSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        double step = 0.0001;
        SteppingDifferentialOperator differentialOperator = new RightSteppingDifferentialOperator(step);
        MathFunction function = new SqrFunction();

        assertEquals(differentialOperator.derive(function).apply(2), 4.0001, 0.00001);

    }
}