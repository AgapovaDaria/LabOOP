package ru.ssau.tk.Lab2.LabOOP.ui;

import javafx.scene.control.TextField;

public class IndexStringRecord {
    private TextField x;
    private TextField y;


    public IndexStringRecord(TextField x, TextField y) {

        this.x = x;
        this.y = y;
    }

    public TextField getX() {
        return x;
    }

    public void setX(TextField x) {
        this.x = x;
    }


    public TextField getY() {
        return y;
    }

    public void setY(TextField y) {
        this.y = y;
    }
}

