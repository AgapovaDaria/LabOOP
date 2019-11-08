package ru.ssau.tk.Lab2.LabOOP.io;

import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.SqrFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.ThirdFunction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        File file1 = new File("output/array function.txt");
        File file2 = new File("output/linked list function.txt");
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 11);
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new ThirdFunction(), 0, 10,11);

        try(FileWriter fileWriter1 = new FileWriter(file1);FileWriter fileWriter2 = new FileWriter(file2)) {

            BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
            BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
            FunctionsIO.writeTabulatedFunction(fileWriter1, arrayTabulatedFunction);
            FunctionsIO.writeTabulatedFunction(fileWriter2, linkedListTabulatedFunction);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
