package ru.ssau.tk.Lab2.LabOOP.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.ArrayTabulatedFunctionFactory;

public class Window {
    private static final int SPACING_SIZE = 10;

    private TextField textField = new TextField();
    private Button addRowButton = new Button("Add");
    private Button newButton = new Button("Create");
    private ObservableList<PointRecord> records = FXCollections.observableArrayList();
    private TableView<PointRecord> table = new TableView<>();
    private Stage stage = new Stage();

    private OnCreateCallbackFunc callbackFunc;

    public void start(Stage parameterStage, OnCreateCallbackFunc callbackFunc) {
        this.callbackFunc = callbackFunc;
        compose(parameterStage);
        configureTable();
        addButtonListeners();
        newButtonListeners();
        stage.show();
    }

    public TableView<PointRecord> getTable(){
        return table;
    }

    public void compose(Stage parameterStage) {
        VBox mainBox = new VBox();
        mainBox.setPadding(new Insets(SPACING_SIZE));
        mainBox.setSpacing(SPACING_SIZE);
        HBox textBox = new HBox();
        Label label = new Label("Count:  ");
        HBox buttonBox = new HBox();
        HBox newButtonBox = new HBox();
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setSpacing(SPACING_SIZE);
        textBox.setAlignment(Pos.TOP_CENTER);
        newButtonBox.setAlignment(Pos.TOP_RIGHT);
        newButtonBox.setSpacing(SPACING_SIZE);
        newButtonBox.getChildren().addAll(newButton);
        textBox.getChildren().addAll(label, textField);
        buttonBox.getChildren().addAll(addRowButton);
        mainBox.getChildren().addAll(textBox, buttonBox, table, newButtonBox);
        Scene scene = new Scene(mainBox, 300, 350);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parameterStage);
        stage.setTitle("Creating a value table");
        stage.setScene(scene);
    }

    public void configureTable() {
        table.setEditable(true);
        table.setItems(records);
        TableColumn<PointRecord, Double> xColumn = new TableColumn<>("X");
        TableColumn<PointRecord, Double> yColumn = new TableColumn<>("Y");
        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        yColumn.setCellFactory(column -> new TableProcessing());
        yColumn.setOnEditCommit(event -> {
            int rowNumber = event.getTablePosition().getRow();
            if (rowNumber != -1) {
                records.get(rowNumber).setY(event.getNewValue());
            }
        });
        xColumn.setCellFactory(column -> new TableProcessing());
        xColumn.setOnEditCommit(event -> {
            int rowNumber = event.getTablePosition().getRow();
            if (rowNumber != -1) {
                records.get(rowNumber).setX(event.getNewValue());
            }
        });
        table.getColumns().add(xColumn);
        table.getColumns().add(yColumn);
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

    /**
     * show after createButton clicked
     */
    public void newButtonListeners() {
        newButton.setOnMouseClicked(event -> {
            try {
                double[] xValues = new double[records.size()];
                double[] yValues = new double[records.size()];
                for (int i = 0; i < records.size(); i++) {
                    PointRecord pointRecord = records.get(i);
                    xValues[i] = pointRecord.getX();
                    yValues[i] = pointRecord.getY();
                }
                ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
                try {
                    TabulatedFunction function = factory.create(xValues, yValues);
                    callbackFunc.apply(function);
                    stage.close();
                } catch (Exception e) {
                    ErrorWindows errorWindows = new ErrorWindows();
                    errorWindows.showAlertWithoutHeaderText(e);
                }

            } catch (NumberFormatException e) {
                ErrorWindows errorWindows = new ErrorWindows();
                errorWindows.showAlertWithoutHeaderText(e);
            }
        });
    }


    public interface OnCreateCallbackFunc{
         void apply(TabulatedFunction function);
    }
}
