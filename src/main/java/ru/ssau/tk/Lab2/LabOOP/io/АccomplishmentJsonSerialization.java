package ru.ssau.tk.Lab2.LabOOP.io;

import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;

import java.io.*;

public class АccomplishmentJsonSerialization {

    public static void main(String[] args) {
        double[] xValues = {5., 6., 7., 8.};
        double[] yValues = {14., 10., 16., 9.};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        try (BufferedWriter outputStream = new BufferedWriter(new FileWriter("output/array_function.json"))) {
            FunctionsIO.serializeJson(outputStream, function);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader inputStream = new BufferedReader(new FileReader("output/array_function.json"))) {
            System.out.println(FunctionsIO.deserializeJson(inputStream).toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
