package ru.ssau.tk.Lab2.LabOOP.io;


import operations.TabulatedDifferentialOperator;
import ru.ssau.tk.Lab2.LabOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.ThirdFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {

    public static void main(String[] args) {
        LinkedListTabulatedFunction linkedListFun = new LinkedListTabulatedFunction(new ThirdFunction(), 1, 15, 20);
        TabulatedDifferentialOperator tabulateddifferentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction deriveFir = tabulateddifferentialOperator.derive(linkedListFun);
        TabulatedFunction deriveSec = tabulateddifferentialOperator.derive(deriveFir);
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("output/serialized linked list functions.bin"))) {
            FunctionsIO.serialize(outputStream, linkedListFun);
            FunctionsIO.serialize(outputStream, deriveFir);
            FunctionsIO.serialize(outputStream, deriveSec);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("output/serialized linked list functions.bin"))) {
            System.out.println(FunctionsIO.deserialize(inputStream).toString());
            System.out.println(FunctionsIO.deserialize(inputStream).toString());
            System.out.println(FunctionsIO.deserialize(inputStream).toString());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
