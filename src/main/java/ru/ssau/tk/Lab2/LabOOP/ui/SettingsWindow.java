package ru.ssau.tk.Lab2.LabOOP.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ssau.tk.Lab2.LabOOP.functions.AbstractTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.LinkedListTabulatedFunction;

import java.util.HashMap;

public class SettingsWindow extends Stage {

    private static final int SPACING_SIZE = 10;
    AbstractTabulatedFunction function;
    private Stage stage = new Stage();
    private Button okButton = new Button("Ок");
    ObservableList<String> observableList = FXCollections.observableArrayList();
    HashMap<String, AbstractTabulatedFunction> functionHashMap;

    public void start(Stage parameterStage) {
        compose(parameterStage);
        setInitValue();
        addButtonListeners();
        stage.show();
    }

    public void compose(Stage parameterStage) {
        VBox mainBox = new VBox();
        mainBox.setPadding(new Insets(SPACING_SIZE));
        mainBox.setSpacing(SPACING_SIZE);
        HBox textBox = new HBox();
        HBox buttonBox = new HBox();
        textBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        buttonBox.setSpacing(SPACING_SIZE);
        buttonBox.getChildren().addAll(okButton);
        Label label = new Label("Generation factory:  ");
        ComboBox<String> langsComboBox = new ComboBox<>(observableList);
        langsComboBox.getSelectionModel().selectFirst();
        textBox.getChildren().addAll(label, langsComboBox);
        mainBox.getChildren().addAll(textBox, buttonBox);
        Scene scene = new Scene(mainBox, 400, 80);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parameterStage);
        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.show();
    }

    private void setInitValue() {
        functionHashMap = new HashMap<>();

        function = new ArrayTabulatedFunction();
        functionHashMap.put(function.toString(), function);
        observableList.add(function.toString());

        function = new LinkedListTabulatedFunction();
        functionHashMap.put(function.toString(), function);
        observableList.add(function.toString());

        observableList = FXCollections.observableArrayList(
                function.toString()
        );
    }

    public void addButtonListeners() {
        okButton.setOnMouseClicked(event -> {
            GrWindow.returnFun(function);
            stage.close();
        });
    }
}