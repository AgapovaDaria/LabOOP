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
import javafx.stage.Stage;
import ru.ssau.tk.Lab2.LabOOP.functions.*;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.ArrayTabulatedFunctionFactory;

import java.util.*;


public class FirstWindow {
    private static final int SPACING_SIZE = 10;

    private TextField textField1 = new TextField();
    private TextField textField2 = new TextField();
    private TextField textField3 = new TextField();
    private Button newButton = new Button("Create");
    ObservableList<String> fun = FXCollections.observableArrayList(new SqrFunction().toString(), new UnitFunction().toString(),
            new ThirdFunction().toString(),
            new TangFunction().toString(), new ZeroFunction().toString());

    private Stage stage = new Stage();

    public void start() {
        compose();
        newButtonListeners();
        stage.show();
    }

    public void compose() {
        VBox mainBox = new VBox();
        mainBox.setPadding(new Insets(SPACING_SIZE));
        mainBox.setSpacing(SPACING_SIZE);
        HBox textBox = new HBox();
        Label label = new Label("Function:  ");
        Label label1 = new Label("Count:  ");
        Label label2 = new Label("xFrom:  ");
        Label label3 = new Label("xTo:  ");

        HBox newButtonBox = new HBox();
        ChoiceBox<String> choiceBox = new ChoiceBox<>(fun);
        textBox.setAlignment(Pos.TOP_CENTER);
        newButtonBox.setAlignment(Pos.TOP_RIGHT);
        newButtonBox.setSpacing(SPACING_SIZE);
        newButtonBox.getChildren().addAll(newButton);
        textBox.getChildren().addAll(label, choiceBox);
        mainBox.getChildren().addAll(textBox, label1, textField1, label2, textField2, label3, textField3, newButtonBox);
        Scene scene = new Scene(mainBox, 500, 280);
        stage.setTitle("My window");
        stage.setScene(scene);
    }

    public void newButtonListeners() {
        newButton.setOnMouseClicked(event -> {
            try {
                int count = Integer.parseInt(textField1.getText());
                double xFrom = Double.parseDouble(textField2.getText());
                double xTo = Double.parseDouble(textField3.getText());
                ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
                SortedMap<String, MathFunction> name = new TreeMap<>();
               // Collections.sort(name,Comparator<Map.Entry>);
                name.put("Квадратичная функция", new SqrFunction());
                name.put("Единичная функция", new UnitFunction());
                name.put("Кубическая функция", new ThirdFunction());
                name.put("Тангенциальная функция", new TangFunction());
                name.put("Нулевая функция", new ZeroFunction());
                TabulatedFunction function = factory.create(name.get(fun), xFrom, xTo, count);

                stage.close();
            } catch (NumberFormatException e) {
                ErrorWindows errorWindows = new ErrorWindows();
                errorWindows.showAlertWithoutHeaderText(e);
            }
        });
    }

    /*@Override
    public String toString() {
        Map< String,MathFunction> name = new HashMap<>();
        name.put("Квадратичная функция",new SqrFunction());
        name.put("Единичная функция",new UnitFunction());
        name.put("Кубическая функция",new ThirdFunction());
        name.put("Тангенциальная функция",new TangFunction());
        name.put("Нулевая функция",new ZeroFunction());
        return "FirstWindow{" +
                "fun=" + fun +
                '}';
    }

     */
}
