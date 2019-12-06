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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GrWindow extends Application {
    private static final int SPACING_SIZE = 10;

    TextField textField = new TextField();
    Button addRowButton = new Button("Save");
    ObservableList<IndexStringRecord> records = FXCollections.observableArrayList();
    TableView<IndexStringRecord> table = new TableView<>();


    @Override
    public void start(Stage stage) {
        compose(stage);
        configureTable();
        addButtonListeners();
        stage.show();
    }

    public void compose(Stage stage) {
        VBox mainBox = new VBox();
        mainBox.setPadding(new Insets(SPACING_SIZE));
        mainBox.setSpacing(SPACING_SIZE);
        HBox textBox = new HBox();
        Label label = new Label("Count:  ");
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setSpacing(SPACING_SIZE);
        textBox.setAlignment(Pos.TOP_CENTER);
        textBox.getChildren().addAll(label, textField);
        buttonBox.getChildren().addAll(addRowButton);
        mainBox.getChildren().addAll(textBox,buttonBox, table);
        Scene scene = new Scene(mainBox, 400, 200);
        stage.setTitle("My window");
        stage.setScene(scene);
    }

    public void configureTable() {
        table.setEditable(true);
        table.setItems(records);
        TableColumn<IndexStringRecord, Double>  xColumn = new TableColumn<>("X");
        TableColumn<IndexStringRecord, Double>  yColumn = new TableColumn<>("Y");
        xColumn.setCellValueFactory(new PropertyValueFactory<>("xColumn"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("yComumn"));

//        yColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        yColumn.setOnEditCommit((EventHandler<TableColumn.CellEditEvent<IndexStringRecord, String>>) event -> {
//            int rowNumber = event.getTablePosition().getRow();
//            if (rowNumber != -1) {
//                records.get(rowNumber).setValue(event.getNewValue());
//            }
//        });
        table.getColumns().

                add(xColumn);
        table.getColumns().

                add(yColumn);
    }

    public void addButtonListeners() {
        addRowButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    int count = Integer.parseInt(textField.getText());
                    for (int i=0; i<count; i++) {
                        records.add(new IndexStringRecord(0, 0));
                    }
                } catch (NumberFormatException e) {
                    ErrorWindows errorWindows = new  ErrorWindows();
                    errorWindows.showAlertWithoutHeaderText(e);
                }
            }
        });
    }
}
