package ru.ssau.tk.Lab2.LabOOP.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.ssau.tk.Lab2.LabOOP.functions.*;
import ru.ssau.tk.Lab2.LabOOP.operations.TabulatedFunctionOperationService;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    private static final int SPACING_SIZE = 10;
    private Button buttonCreate = new Button("Create");
    private Button buttonDownload = new Button("Download");
    private Button buttonSave = new Button("Save");
    private Button buttonCreate1 = new Button("Create");
    private Button buttonDownload1 = new Button("Download");
    private Button buttonSave1 = new Button("Save");
    private TableView<PointRecord> firstTable = new TableView<>();
    private TableView<PointRecord> secondTable = new TableView<>();
    private TableView<PointRecord> thirdTable = new TableView<>();
    private Map<String, Method> nameOperationMap = new HashMap<>();


    private Stage stage = new Stage();
    private static AbstractTabulatedFunction function;


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    private @interface Operation {

        String value();
    }

    @Operation("Сложение")
    public static TabulatedFunction sum(TabulatedFunction a, TabulatedFunction b) {
        TabulatedFunctionOperationService functionOperationService = new TabulatedFunctionOperationService();
        return functionOperationService.sum(a, b);
    }

    @Operation("Вычитание")
    public static double subtract(double a, double b) {
        return a - b;
    }

    @Operation("Произведение")
    public static double product(double a, double b) {
        return a * b;
    }

    @Operation("Деление")
    public static double division(double a, double b) {
        return a / b;
    }

    @Operation("Остаток от деления")
    public static double reminder(double a, double b) {
        return a % b;
    }

    public void start(Stage stage, AbstractTabulatedFunction tabulatedFunction) {
        initUIComponents(stage); // компоновка
        configureTable();
        //initOperations();
        //initButtonListener();
        stage.show();
    }

    private void initUIComponents(Stage stage) {
        HBox mainBox = new HBox();
        HBox buttons = new HBox();
        buttons.getChildren().addAll(buttonCreate, buttonDownload, buttonSave);
        HBox buttons1 = new HBox();
        buttons1.getChildren().addAll(buttonCreate1, buttonDownload1, buttonSave1);
        VBox firstBox = new VBox();
        VBox secondBox = new VBox();
        VBox thirdBox = new VBox();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll("Сложение", "Вычитание", "Деление", "Умножение");
        ChoiceBox<String> choiceBox = new ChoiceBox<>(observableList);
        choiceBox.setValue("Сложение");
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

    private void configureTable() {

        buttonCreate.setOnMouseClicked(event -> {
            Window window = new Window();
            window.start(stage, function);

        });

    }

    /*private void initOperations() {
        Arrays.stream(Calculator.class.getMethods())
        .filter(method -> method.isAnnotationPresent(Operation.class))
        .forEach(method -> {
        Operation operation = method.getAnnotation(Operation.class);
        String operationName = operation.value();
        nameOperationMap.put(operationName, method);
        });
        nameOperationMap.keySet().forEach(operationComboBox::addItem);
        }
}
*/
//  private void initButtonListener() {
//      calcButton.addActionListener(event -> {
//          try {
//              double first = Double.parseDouble(firstTextField.getText());
//              double second = Double.parseDouble(secondTextField.getText());
//              String operationName = (String) operationComboBox.getSelectedItem();
//              Method operation = nameOperationMap.get(operationName);
//              Double result = (Double) operation.invoke(null, first, second);
//              resultTextField.setText(result.toString());
//          } catch (Exception e) {
//              OptionPane.showMessageDialog(this, e.getStackTrace());
//          }
//      });
// }*/

}
