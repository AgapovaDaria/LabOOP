package ru.ssau.tk.Lab2.LabOOP.ui;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Text;

public class GraphicWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Button button1 = new Button("Ok");
    Button button2 = new Button("Cansel");

    @Override
    public void start(Stage stage) {

        Text text = new Text("Я усталь!");
        text.setLayoutY(150);    // установка положения надписи по оси Y
        text.setLayoutX(160);   // установка положения надписи по оси X
        Button button1 = new Button("Ok");
        Button button2 = new Button("Cansel");

        Group group = new Group(text);

        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("Function parameters");
        stage.setWidth(400);
        stage.setHeight(350);
        stage.show();


    }
}

