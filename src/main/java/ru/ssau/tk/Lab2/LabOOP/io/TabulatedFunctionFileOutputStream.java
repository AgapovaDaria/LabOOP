package ru.ssau.tk.Lab2.LabOOP.io;

import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.LinkedListTabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {

    public static void main(String[] args) {
        double[] xValues = {5., 6., 7., 8.};
        double[] yValues = {14., 10., 16., 9.};
        ArrayTabulatedFunction arrayFun = new ArrayTabulatedFunction(xValues, yValues);
        try {
            BufferedOutputStream outStreamArray = new BufferedOutputStream(new FileOutputStream(new File("output/array function.bin")));
            FunctionsIO.writeTabulatedFunction(outStreamArray, arrayFun);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedListTabulatedFunction linkedListFun = new LinkedListTabulatedFunction(xValues, yValues);
        try {
            BufferedOutputStream outStreamLinkedList = new BufferedOutputStream(new FileOutputStream(new File("output/linked list function.bin")));
            FunctionsIO.writeTabulatedFunction(outStreamLinkedList, linkedListFun);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
