package ru.ssau.tk.Lab2.LabOOP.functions;

public interface TabulatedFunction extends MathFunction {
    int getCount(); // метод получения количества табулированных значений

    // метод, получающий значение аргумента x по номеру индекса
    double getX(int index);

    // метод, получающий значение у по номеру индекса
    double getY(int index);

    // метод, задающий значение у по номеру индекса
    void setY(int index, double value);

    //метод,возвращающий индекс аргумента х
    int indexOfX(double x);

    //метод, возвращающий индекс первого вхождения значения у
    int indexOfY(double y);

    //метод, возвращающий самый левый х
    double leftBound();

    //метод,возвращающий самый правый х
    double rightBound();

}
