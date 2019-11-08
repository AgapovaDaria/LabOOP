package ru.ssau.tk.Lab2.LabOOP.io;

import ru.ssau.tk.Lab2.LabOOP.functions.Point;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;

public final class FunctionsIO {

    private FunctionsIO() {
        throw new UnsupportedOperationException(" ");
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {

    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeInt(function.getCount());
        for (Point newPoint : function) {
            out.writeDouble(newPoint.x);
            out.writeDouble(newPoint.y);
        }
        out.flush();
    }
}
