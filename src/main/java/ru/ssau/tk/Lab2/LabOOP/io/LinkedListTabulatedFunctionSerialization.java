package ru.ssau.tk.Lab2.LabOOP.io;

import ru.ssau.tk.Lab2.LabOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.ThirdFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.Lab2.LabOOP.operations.TabulatedDifferentialOperator;

import java.io.*;
import static ru.ssau.tk.Lab2.LabOOP.io.FunctionsIO.deserialize;
import static ru.ssau.tk.Lab2.LabOOP.io.FunctionsIO.serialize;

public class LinkedListTabulatedFunctionSerialization {

    public static void main(String[] args) {
        TabulatedFunction linkedListFun = new LinkedListTabulatedFunction(new ThirdFunction(), 1, 15, 20);
        TabulatedDifferentialOperator tabulateddifferentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction deriveFir = tabulateddifferentialOperator.derive(linkedListFun);
        TabulatedFunction deriveSec = tabulateddifferentialOperator.derive(deriveFir);
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("output/serialized linked list functions.bin"))) {
            serialize(outputStream,  linkedListFun);
            serialize(outputStream, deriveFir);
            serialize(outputStream, deriveSec);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("output/serialized linked list functions.bin"))) {
            System.out.println(deserialize(inputStream).toString());
            System.out.println(deserialize(inputStream).toString());
            System.out.println(deserialize(inputStream).toString());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

