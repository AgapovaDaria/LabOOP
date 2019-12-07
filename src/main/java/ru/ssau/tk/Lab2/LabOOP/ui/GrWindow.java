package ru.ssau.tk.Lab2.LabOOP.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.ArrayTabulatedFunctionFactory;

public class GrWindow extends Application {
    private static final int SPACING_SIZE = 10;

    @Override
    public void start(Stage stage) {
        stage.show();
        Window window = new Window();
        window.start();
        FirstWindow firstWindow = new FirstWindow();
        firstWindow.start();
    }
}
