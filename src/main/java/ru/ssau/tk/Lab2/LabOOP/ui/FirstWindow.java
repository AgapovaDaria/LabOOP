package ru.ssau.tk.Lab2.LabOOP.ui;

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
import ru.ssau.tk.Lab2.LabOOP.functions.*;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.ArrayTabulatedFunctionFactory;

public class FirstWindow {
    private static final int SPACING_SIZE = 10;

    private TextField textField = new TextField();
    private TextField textField1 = new TextField();
    private TextField textField2 = new TextField();
    private TextField textField3 = new TextField();
    private Button addRowButton = new Button("Save");
    private Button newButton = new Button("Create");
    private ObservableList<PointRecord> records = FXCollections.observableArrayList();
    ObservableList<MathFunction> fun = FXCollections.observableArrayList(new SqrFunction(), new UnitFunction(), new ThirdFunction(),
            new TangFunction(), new ZeroFunction());
    private TableView<PointRecord> table = new TableView<>();
    private Stage stage = new Stage();

    public void start() {
        compose();
        addButtonListeners();
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
        ChoiceBox<MathFunction> choiceBox = new ChoiceBox<MathFunction>(fun);
        textBox.setAlignment(Pos.TOP_CENTER);
        newButtonBox.setAlignment(Pos.TOP_RIGHT);
        newButtonBox.setSpacing(SPACING_SIZE);
        newButtonBox.getChildren().addAll(newButton);
        textBox.getChildren().addAll(label, choiceBox);
        mainBox.getChildren().addAll(textBox, label1, textField1, label2, textField2, label3, textField3, newButtonBox);
        Scene scene = new Scene(mainBox, 500, 350);
        stage.setTitle("My window");
        stage.setScene(scene);
    }

    public void addButtonListeners() {
        addRowButton.setOnMouseClicked(event -> {
            try {
                int count = Integer.parseInt(textField.getText());
                for (int i = 0; i < count; i++) {
                    records.add(new PointRecord(0, 0));
                }
            } catch (NumberFormatException e) {
                ErrorWindows errorWindows = new ErrorWindows();
                errorWindows.showAlertWithoutHeaderText(e);
            }
        });
    }

    public void newButtonListeners() {
        newButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               /*try {
                    int count = Integer.parseInt(textField1.getText());
                    double xFrom = Double.parseDouble(textField2.getText());

                }*/
                ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
                try {
                    //TabulatedFunction function = factory.create();
                } catch (Exception e) {
                    ErrorWindows errorWindows = new ErrorWindows();
                    errorWindows.showAlertWithoutHeaderText(e);
                }
                stage.close();
            } /*catch( NumberFormatException e) {
                ErrorWindows errorWindows = new ErrorWindows();
                errorWindows.showAlertWithoutHeaderText(e);
            }*/
        });
    }
}
