package ru.ssau.tk.Lab2.LabOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    @Test
    public void testApply() {
        MathFunction x = new IdentityFunction();//создаем экземпляр
        double actual = x.apply(2.5);
        double expected = 2.5;// ожидаемое значение
        assertEquals(expected, actual, 0.00001);
    }
}