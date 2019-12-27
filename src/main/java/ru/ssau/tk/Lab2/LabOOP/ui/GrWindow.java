package ru.ssau.tk.Lab2.LabOOP.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.TabulatedFunctionFactory;


public class GrWindow extends Application {
    private static final int SPACING_SIZE = 10;
    private static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

    @Override
    public void start(Stage stage) {
        compose(stage);
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }

    public void compose(Stage stage) {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu(" File ");
        Menu setMenu = new Menu(" Settings ");
        MenuItem tableItem = new MenuItem(" Table ");
        MenuItem openFileItem = new MenuItem(" Functions ");
        MenuItem openCalculator = new MenuItem(" Calculator ");
        MenuItem exitItem = new MenuItem(" Exit ");
        MenuItem setItem = new MenuItem(" Factory settings ");
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        setItem.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        fileMenu.getItems().addAll(tableItem, openFileItem, openCalculator, exitItem);
        setMenu.getItems().addAll(setItem);
        menuBar.getMenus().addAll(fileMenu, setMenu);
        setItem.setOnAction(event -> {
            SettingsWindow settingsWindow = new SettingsWindow();
            settingsWindow.start(stage);
        });
        exitItem.setOnAction(actionEvent -> Platform.exit());
        tableItem.setOnAction(event -> {
            Window window = new Window();
            window.start(stage, function -> {
                returnFun(factory);
            });
        });
        openFileItem.setOnAction(event -> {
            FirstWindow firstWindow = new FirstWindow();
            firstWindow.start(stage, factory);
        });
        openCalculator.setOnAction(event -> {
            Calculator calculator = Calculator.getInstance();
            calculator.start(stage, factory);
        });
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        Scene scene = new Scene(root, 500, 350);
        menuBar.setStyle("-fx-background-color: #4682B4;");
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #E6E6FA, #778899);");
        stage.setTitle("The program of laboratory work");
        stage.setScene(scene);
    }

    public static void returnFun(TabulatedFunctionFactory factory2) {
        factory = factory2;
    }
}
