package ru.ssau.tk.Lab2.LabOOP.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        File file = new File("output/serialized array functions.bin");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
