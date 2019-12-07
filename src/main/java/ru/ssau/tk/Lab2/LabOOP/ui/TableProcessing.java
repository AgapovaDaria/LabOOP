package ru.ssau.tk.Lab2.LabOOP.ui;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

public class TableProcessing extends TextFieldTableCell<PointRecord, Double> {
    public TableProcessing(){
        super(new DoubleStringConverter() {
            @Override
            public Double fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (NumberFormatException e) {
                    ErrorWindows errorWindows = new ErrorWindows();
                    errorWindows.showAlertWithoutHeaderText(e);
                }
                return 0.;
            }

            @Override
            public String toString(Double value) {
                try {
                    return super.toString(value);
                } catch (NumberFormatException e) {
                    ErrorWindows errorWindows = new ErrorWindows();
                    errorWindows.showAlertWithoutHeaderText(e);
                }
                return "";
            }
        });
    }
}
