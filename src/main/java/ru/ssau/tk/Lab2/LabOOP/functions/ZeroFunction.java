package ru.ssau.tk.Lab2.LabOOP.functions;

import ru.ssau.tk.Lab2.LabOOP.functions.ConstantFunction;

public class ZeroFunction extends ConstantFunction{
    public ZeroFunction() {
        super(0);
    }

    @Override
    public String toString() {
        return "Нулевая функция";
    }
}
