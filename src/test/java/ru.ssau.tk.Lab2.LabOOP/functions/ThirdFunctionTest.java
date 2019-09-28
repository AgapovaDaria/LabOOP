package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ThirdFunctionTest {

    @Test
    public void testApply() {
        MathFunction x = new ThirdFunction();//создаем экземпляр
        double actual = x.apply(3);
        double expected = 27;// ожидаемое значение
        assertEquals(expected, actual, 0.00001);
    }
}