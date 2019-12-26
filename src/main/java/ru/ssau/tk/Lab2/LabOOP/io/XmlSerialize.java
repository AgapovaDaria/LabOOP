package ru.ssau.tk.Lab2.LabOOP.io;

import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.SqrFunction;
import ru.ssau.tk.Lab2.LabOOP.io.FunctionsIO.*;

import java.io.*;

public class XmlSerialize {

    public static void main(String[] args){
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(new SqrFunction(), 1, 11, 11);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output/function.xml"))){
            FunctionsIO.serializeXml(bufferedWriter,function);
        }catch(IOException e){
            e.printStackTrace();
        }
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("output/function.xml"))) {
            System.out.println(FunctionsIO.deserializeXml(bufferedReader));
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
