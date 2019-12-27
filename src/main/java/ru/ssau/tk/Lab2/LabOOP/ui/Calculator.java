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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.Lab2.LabOOP.operations.TabulatedFunctionOperationService;

import java.io.*;

import static ru.ssau.tk.Lab2.LabOOP.io.FunctionsIO.deserialize;
import static ru.ssau.tk.Lab2.LabOOP.io.FunctionsIO.serialize;

public class Calculator {

    private static Calculator instance = new Calculator();

    private enum CurrentFunction {
        FIRST_FUNCTION,
        SECOND_FUNCTION,
        THIRD_FUNCTION,
    }

    private final String stringSum = "Сложение";
    private final String stringSub = "Вычитание";
    private final String stringDivision = "Деление";
    private final String stringMultiplication = "Умножение";
    private static final int SPACING_SIZE = 10;
    private Button buttonFirstCreate = new Button("Create");
    private Button buttonFirstDownload = new Button("Download");
    private Button buttonFirstSave = new Button("Save");
    private Button buttonSecondCreate = new Button("Create");
    private Button buttonSecondDownload = new Button("Download");
    private Button buttonSecondSave = new Button("Save");
    private Button buttonCalc = new Button("Calculate");
    private Button buttonThirdSave = new Button("Save");
    private TableView<PointRecord> firstTable = new TableView<>();
    private TableView<PointRecord> secondTable = new TableView<>();
    private TableView<PointRecord> thirdTable = new TableView<>();
    private ObservableList<PointRecord> firstRecords = FXCollections.observableArrayList();
    private ObservableList<PointRecord> secondRecords = FXCollections.observableArrayList();
    private ObservableList<PointRecord> thirdRecords = FXCollections.observableArrayList();
    private ChoiceBox<String> choiceBox;
    private Stage stage = new Stage();
    private TabulatedFunction firstFunction;
    private TabulatedFunction secondFunction;
    private TabulatedFunction thirdFunction;
    private TabulatedFunctionFactory factory;
    private final FileChooser fileChooser = new FileChooser();

    private Calculator() {
    }

    public static Calculator getInstance() {
        return instance;
    }

    public void start(Stage stage, TabulatedFunctionFactory factory) {
        this.factory = factory;
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
        observableList.addAll(stringSum, stringSub, stringDivision, stringMultiplication);
        choiceBox = new ChoiceBox<>(observableList);
        choiceBox.setValue(stringSum);
        HBox buttons2 = new HBox();
        buttons2.getChildren().addAll(buttonCalc, choiceBox, buttonThirdSave);
        buttonCalc.setOnAction(event -> {
            if (firstFunction != null && secondFunction != null) {
                startOperation();
            } else {
                ErrorWindows errorWindows = new ErrorWindows();
                errorWindows.showAlertWithoutHeaderText(new Exception("Functions are null"));
            }
        });
        buttonFirstSave.setOnAction(event -> {
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                    serialize(bufferedOutputStream, firstFunction);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonFirstDownload.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
                    TabulatedFunction readFunction = deserialize(bufferedInputStream);
                    firstFunction = readFunction;
                    returnFun(firstFunction, firstRecords, CurrentFunction.FIRST_FUNCTION);
                    initTable(firstTable, firstRecords, firstFunction, false, true);

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonSecondSave.setOnAction(event -> {
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                    serialize(bufferedOutputStream, secondFunction);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonSecondDownload.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
                    TabulatedFunction readFunction = deserialize(bufferedInputStream);
                    secondFunction = readFunction;
                    returnFun(secondFunction, secondRecords, CurrentFunction.SECOND_FUNCTION);
                    initTable(secondTable, secondRecords, secondFunction, false, true);

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonThirdSave.setOnAction(event -> {
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                    serialize(bufferedOutputStream, thirdFunction);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        secondBox.setAlignment(Pos.TOP_CENTER);
        buttons.setSpacing(SPACING_SIZE);
        buttons1.setSpacing(SPACING_SIZE);
        firstBox.setAlignment(Pos.TOP_LEFT);
        thirdBox.setAlignment(Pos.TOP_RIGHT);
        buttons2.setSpacing(SPACING_SIZE);
        firstBox.getChildren().addAll(buttons, firstTable);
        secondBox.getChildren().addAll(buttons1, secondTable);
        thirdTable.setId("Result");
        thirdBox.getChildren().addAll(buttons2, thirdTable);
        mainBox.getChildren().addAll(firstBox, secondBox, thirdBox);
        Scene scene = new Scene(mainBox, 700, 400);
        stage.setTitle("Calculator");
        stage.setScene(scene);
    }

    private void initTable(TableView<PointRecord> tableView, ObservableList<PointRecord> pointRecords,
                           TabulatedFunction function, boolean editX, boolean editY) {
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
                //System.out.println(rowNumber);
            }
        });
        tableView.getColumns().add(xColumn);
        tableView.getColumns().add(yColumn);
    }

    private void configureTable() {

        buttonFirstCreate.setOnMouseClicked(event -> {
            Window window = new Window();
            window.start(stage, func -> {
                returnFun(func, firstRecords, CurrentFunction.FIRST_FUNCTION);
                initTable(firstTable, firstRecords, firstFunction, false, true);
            });
        });

        buttonSecondCreate.setOnMouseClicked(event -> {
            Window window = new Window();
            window.start(stage, func -> {
                returnFun(func, secondRecords, CurrentFunction.SECOND_FUNCTION);
                initTable(secondTable, secondRecords, secondFunction, false, true);
            });
        });
    }

    public void returnFun(TabulatedFunction function, ObservableList<PointRecord> records, CurrentFunction currentFunction) {
        switch (currentFunction) {
            case FIRST_FUNCTION:
                firstFunction = function;
                break;
            case SECOND_FUNCTION:
                secondFunction = function;
                break;
            case THIRD_FUNCTION:
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

    private void startOperation() {
        if (firstFunction.getCount() != secondFunction.getCount()) {
            ErrorWindows errorWindows = new ErrorWindows();
            errorWindows.showAlertWithoutHeaderText(new Exception("Functions must have the same length"));
        }
        TabulatedFunction function = null;
        try {
            TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService(factory);
            switch (choiceBox.getValue()) {
                case stringSum:
                    function = operationService.sum(firstFunction, secondFunction);
                    break;
                case stringSub:
                    function = operationService.subtract(firstFunction, secondFunction);
                    break;
                case stringMultiplication:
                    function = operationService.multiplication(firstFunction, secondFunction);
                    break;
                case stringDivision:
                    function = operationService.division(firstFunction, secondFunction);
                    break;
                default:
                    break;
            }
            for (int i = 0; i < secondFunction.getCount(); i++) {

                thirdFunction = function;
                // задаем таблицу
                initTable(thirdTable, thirdRecords, thirdFunction, false, false);
                // добавляем значения
                returnFun(thirdFunction, thirdRecords, CurrentFunction.THIRD_FUNCTION);
            }
        } catch (Exception e) {
            ErrorWindows errorWindows = new ErrorWindows();
            errorWindows.showAlertWithoutHeaderText(e);
        }
    }
}
