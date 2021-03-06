package ru.ssau.tk.Lab2.LabOOP.io;

import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.SqrFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lab2.LabOOP.operations.TabulatedDifferentialOperator;

import java.io.*;
import static ru.ssau.tk.Lab2.LabOOP.io.FunctionsIO.deserialize;
import static ru.ssau.tk.Lab2.LabOOP.io.FunctionsIO.serialize;

public class ArrayTabulatedFunctionSerialization{
    public static void main(String[] args){
        File file = new File("output/serialized_array_functions.bin");
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(new SqrFunction(), 3, 10, 8);
        TabulatedDifferentialOperator differential = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction firstDifferential = differential.derive(function);
        TabulatedFunction secondDifferential = differential.derive(firstDifferential);
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            serialize(bufferedOutputStream, function);
            serialize(bufferedOutputStream, firstDifferential);
            serialize(bufferedOutputStream, secondDifferential);

            System.out.println(firstDifferential.getCount());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            System.out.println(deserialize(bufferedInputStream).toString() + "\n"
                    + deserialize(bufferedInputStream).toString() + "\n" + deserialize(bufferedInputStream).toString());
        } catch (IOException | ClassNotFoundException ic) {
            ic.printStackTrace();
        }
    }
}