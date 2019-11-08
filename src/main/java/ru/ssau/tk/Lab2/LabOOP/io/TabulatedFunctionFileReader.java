package ru.ssau.tk.Lab2.LabOOP.io;

import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.*;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        File file = new File("input/function.txt");

        try(FileReader fileReader = new FileReader(file)) {

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            System.out.println(FunctionsIO.readTabulatedFunction(bufferedReader, new ArrayTabulatedFunctionFactory()).toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = null;

        try(FileReader fileReader = new FileReader(file)) {

            bufferedReader = new BufferedReader(fileReader);
            System.out.println(FunctionsIO.readTabulatedFunction(bufferedReader, new LinkedListTabulatedFunctionFactory()).toString());

        }
        catch (IOException e) {
            try{
                assert bufferedReader != null;
                bufferedReader.close();
            }
            catch (IOException et){
                et.printStackTrace();
            }

            e.printStackTrace();
        }
    }
}
