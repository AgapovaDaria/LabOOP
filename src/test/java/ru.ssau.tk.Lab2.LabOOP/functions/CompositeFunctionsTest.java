package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionsTest {


    @Test
    public void compositeFunctionTest() {

        double x = 9.1;
        MathFunction sqrF = value -> Math.pow(value, 2);
        MathFunction zeroF = value -> 0;
        MathFunction invertF = value -> -value;
        MathFunction plus1F = value -> {
            return value + 1;
        };

        MathFunction resultF = sqrF.andThen(zeroF).andThen(invertF);
        MathFunction result1F = invertF.andThen(sqrF);
        MathFunction result2F = sqrF.andThen(invertF).andThen(plus1F);

        assertEquals(resultF.apply(x), 0);
        assertEquals(result1F.apply(x),  Math.pow(x, 2));
        assertEquals(result2F.apply(x), 1 - Math.pow(x, 2));
    }
}