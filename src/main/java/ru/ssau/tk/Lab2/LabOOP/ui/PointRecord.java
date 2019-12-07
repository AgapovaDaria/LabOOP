package ru.ssau.tk.Lab2.LabOOP.ui;

import javafx.scene.control.TextField;

public class PointRecord {
    private double x;
    private double y;


    public PointRecord(double x, double y) {

        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }


    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

