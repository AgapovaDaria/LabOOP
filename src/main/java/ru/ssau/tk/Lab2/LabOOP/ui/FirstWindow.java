package ru.ssau.tk.Lab2.LabOOP.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ssau.tk.Lab2.LabOOP.functions.*;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.TabulatedFunctionFactory;

import java.util.*;


public class FirstWindow {

    private static final int SPACING_SIZE = 10;

    private TextField textField1 = new TextField();
    private TextField textField2 = new TextField();
    private TextField textField3 = new TextField();

    private Button newButton = new Button("Create");
    private ObservableList<String> observableList;
    private HashMap<String, MathFunction> functionHashMap;
    private TabulatedFunction selectedFunction;
    private TabulatedFunctionFactory factory;

    private Stage stage = new Stage();

    public void start(Stage parameterStage, TabulatedFunctionFactory factory) {
        this.factory = factory;
        setInitValue();
        compose(parameterStage);
        newButtonListeners();
        stage.show();
    }

    public void compose(Stage parameterStage) {
        VBox mainBox = new VBox();
        mainBox.setPadding(new Insets(SPACING_SIZE));
        mainBox.setSpacing(SPACING_SIZE);
        HBox textBox = new HBox();
        Label label = new Label("Function:  ");
        Label label1 = new Label("Count:  ");
        Label label2 = new Label("xFrom:  ");
        Label label3 = new Label("xTo:  ");

        HBox newButtonBox = new HBox();
        ChoiceBox<String> choiceBox = new ChoiceBox<>(observableList);
        textBox.setAlignment(Pos.TOP_CENTER);
        newButtonBox.setAlignment(Pos.TOP_RIGHT);
        newButtonBox.setSpacing(SPACING_SIZE);
        newButtonBox.getChildren().addAll(newButton);
        textBox.getChildren().addAll(label, choiceBox);
        mainBox.getChildren().addAll(textBox, label1, textField1, label2, textField2, label3, textField3, newButtonBox);
        Scene scene = new Scene(mainBox, 500, 280);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parameterStage);
        stage.setTitle("My window");
        stage.setScene(scene);
    }


    public void newButtonListeners() {
        newButton.setOnMouseClicked(event -> {
            try {
                int count = Integer.parseInt(textField1.getText());
                double xFrom = Double.parseDouble(textField2.getText());
                double xTo = Double.parseDouble(textField3.getText());

                selectedFunction = factory.create(
                        functionHashMap.getOrDefault(observableList, new ZeroFunction()), xFrom, xTo, count);

            } catch (NumberFormatException e) {
                ErrorWindows errorWindows = new ErrorWindows();
                errorWindows.showAlertWithoutHeaderText(e);
            } finally {
                stage.close();
            }
        });
    }

    private void setInitValue() {
        functionHashMap = new HashMap<>();

        MathFunction function = new ZeroFunction();
        functionHashMap.put(function.toString(), function);

        observableList = FXCollections.observableArrayList(
                function.toString()
        );

        function = new SqrFunction();
        functionHashMap.put(function.toString(), function);
        observableList.add(function.toString());

        function = new UnitFunction();
        functionHashMap.put(function.toString(), function);
        observableList.add(function.toString());

        function = new ThirdFunction();
        functionHashMap.put(function.toString(), function);
        observableList.add(function.toString());

        function = new TangFunction();
        functionHashMap.put(function.toString(), function);
        observableList.add(function.toString());

        observableList = observableList.sorted();
    }

}
