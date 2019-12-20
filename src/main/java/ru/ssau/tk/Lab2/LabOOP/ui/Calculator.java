package ru.ssau.tk.Lab2.LabOOP.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.ssau.tk.Lab2.LabOOP.functions.AbstractTabulatedFunction;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    private static Calculator instance = new Calculator();

    private enum CurrentFunction {
        FirstFunction,
        SecondFunction,
        ThirdFunction,
    }

    private static final int SPACING_SIZE = 10;
    private Button buttonFirstCreate = new Button("Create");
    private Button buttonFirstDownload = new Button("Download");
    private Button buttonFirstSave = new Button("Save");
    private Button buttonSecondCreate = new Button("Create");
    private Button buttonSecondDownload = new Button("Download");
    private Button buttonSecondSave = new Button("Save");
    private TableView<PointRecord> firstTable = new TableView<>();
    private TableView<PointRecord> secondTable = new TableView<>();
    private TableView<PointRecord> thirdTable = new TableView<>();

    private ObservableList<PointRecord> firstRecords = FXCollections.observableArrayList();
    private ObservableList<PointRecord> secondRecords = FXCollections.observableArrayList();
    private ObservableList<PointRecord> thirdRecords = FXCollections.observableArrayList();

    private ChoiceBox<String> choiceBox;
    private Map<String, Method> nameOperationMap = new HashMap<>();

    private Stage stage = new Stage();

    private AbstractTabulatedFunction firstFunction;
    private AbstractTabulatedFunction secondFunction;
    private AbstractTabulatedFunction thirdFunction;
    private Calculator() {
    }

    public static Calculator getInstance() {
        return instance;
    }

    public void start(Stage stage, AbstractTabulatedFunction tabulatedFunction) {
        initUIComponents(stage);
        configureTable();
        stage.show();
    }

    private void initUIComponents(Stage stage) {
        HBox mainBox = new HBox();
        HBox buttons = new HBox();
        buttons.getChildren().addAll(buttonFirstCreate, buttonFirstDownload, buttonFirstSave);
        HBox buttons1 = new HBox();
        buttons1.getChildren().addAll(buttonSecondCreate, buttonSecondDownload, buttonSecondSave);
        VBox firstBox = new VBox();
        VBox secondBox = new VBox();
        VBox thirdBox = new VBox();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll("Сложение", "Вычитание", "Деление", "Умножение");
        choiceBox = new ChoiceBox<>(observableList);
        choiceBox.setValue("Сложение");

        choiceBox.setOnAction( event -> {
            if( firstFunction != null && secondFunction != null){
                StartOperation();
            }
        });

        secondBox.setAlignment(Pos.TOP_CENTER);
        buttons.setSpacing(SPACING_SIZE);
        firstBox.setAlignment(Pos.TOP_LEFT);
        thirdBox.setAlignment(Pos.TOP_RIGHT);
        buttons1.setSpacing(SPACING_SIZE);
        firstBox.getChildren().addAll(buttons, firstTable);
        secondBox.getChildren().addAll(buttons1, secondTable);
        thirdTable.setId("Result");
        thirdBox.getChildren().addAll(choiceBox, thirdTable);
        mainBox.getChildren().addAll(firstBox, secondBox, thirdBox);
        Scene scene = new Scene(mainBox, 700, 400);
        stage.setTitle("Calculator");
        stage.setScene(scene);
    }

    private void InitTable(TableView<PointRecord> tableView, ObservableList<PointRecord> pointRecords,
                           AbstractTabulatedFunction function, boolean editX, boolean editY) {
        tableView.getColumns().clear();
        tableView.setItems(pointRecords);
        tableView.setEditable(true);

        TableColumn<PointRecord, Double> xColumn = new TableColumn<>("X");
        TableColumn<PointRecord, Double> yColumn = new TableColumn<>("Y");

        yColumn.setEditable(editY);
        xColumn.setEditable(editX);

        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        yColumn.setCellFactory(column -> new TableProcessing());
        xColumn.setCellFactory(column -> new TableProcessing());

        yColumn.setOnEditCommit(event -> {
            int rowNumber = event.getTablePosition().getRow();
            if (rowNumber != -1) {
                double value = event.getNewValue();
                pointRecords.get(rowNumber).setY(value);
                function.setY(rowNumber, value);
                System.out.println(rowNumber);
            }
        });


        tableView.getColumns().add(xColumn);
        tableView.getColumns().add(yColumn);

    }

    private void configureTable() {

        buttonFirstCreate.setOnMouseClicked(event -> {
            Window window = new Window();
            window.start(stage, func -> {
                returnFun((AbstractTabulatedFunction) func, firstRecords, CurrentFunction.FirstFunction);
                InitTable(firstTable, firstRecords, firstFunction, false, true);
            });
        });

        buttonSecondCreate.setOnMouseClicked(event -> {
            Window window = new Window();
            window.start(stage, func -> {
                returnFun((AbstractTabulatedFunction) func, secondRecords, CurrentFunction.SecondFunction);
                InitTable(secondTable, secondRecords, secondFunction, false, true);
                if (firstRecords.size() > 1 && firstRecords.size() != secondRecords.size()) {
                    ErrorWindows errorWindows = new ErrorWindows();
                    errorWindows.showAlertWithoutHeaderText(new Exception("Функции должны иметь одинаковые длины"));
                    return;
                }
                StartOperation();
            });
        });
    }

    public void returnFun(AbstractTabulatedFunction function, ObservableList<PointRecord> records, CurrentFunction currentFunction) {
        switch (currentFunction) {
            case FirstFunction:
                firstFunction = function;
                break;
            case SecondFunction:
                secondFunction = function;
                break;
            case ThirdFunction:
               // thirdFunction = function;
                break;
            default:
                break;
        }

        records.clear();
        for (int i = 0; i < function.getCount(); i++) {
            PointRecord point = new PointRecord(function.getX(i), function.getY(i));
            records.add(point);
        }

    }

    private void StartOperation() {
        if (firstFunction != null && (firstFunction.getCount() <= 1 || firstFunction.getCount() != secondFunction.getCount()))
            return;
        AbstractTabulatedFunction function; // use   TabulatedFunctionOperationService  service;
        switch (choiceBox.getValue()) {
            case "Сложение":
                break;
            case "Вычитание":
                break;
            case "Произведение":
                break;
            case "Деление":
                break;
            case "Остаток от деления":
                break;
            default:
                break;
        }

        for (int i = 0; i < secondFunction.getCount(); i++) {

        }
        //test
        function = secondFunction;

        //после получения новой 3 функции делаем так
        thirdFunction = function;
        // задаем таблицу
        InitTable(thirdTable, thirdRecords, thirdFunction, false, false);
        // добавляем значения
        returnFun(thirdFunction, thirdRecords, CurrentFunction.ThirdFunction);
    }

}
