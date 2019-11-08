package ru.ssau.tk.Lab2.LabOOP.io;

import operations.TabulatedDifferentialOperator;
import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.SqrFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.ArrayTabulatedFunctionFactory;
import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        File file = new File("output/serialized_array_functions.bin");
        TabulatedFunction function = new ArrayTabulatedFunction(new SqrFunction(), 3, 10, 50);
        TabulatedDifferentialOperator differential = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction firstDifferential = differential.derive(function);
        TabulatedFunction secondDifferential = differential.derive(firstDifferential);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            FunctionsIO.serialize(bufferedOutputStream, function);
            FunctionsIO.serialize(bufferedOutputStream, firstDifferential);
            FunctionsIO.serialize(bufferedOutputStream, secondDifferential);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream inputStream = new FileInputStream(file);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
                            System.out.println(FunctionsIO.deserialize(bufferedInputStream).toString() + "\n"
                    + FunctionsIO.deserialize(bufferedInputStream).toString() + "\n" + FunctionsIO.deserialize(bufferedInputStream).toString());
        } catch (IOException | ClassNotFoundException ic) {
            ic.printStackTrace();
        }
    }
}