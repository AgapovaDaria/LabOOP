package ru.ssau.tk.Lab2.LabOOP.ui;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.transform.*;
import javafx.scene.effect.*;
import javafx.scene.paint.*;

public class Example extends Application {

    double angle = 0.0;
    double scaleFactor = 0.4;
    double blurVal = 1.0;

    // Create initial effects and transforms.
    Reflection reflection = new Reflection();
    BoxBlur blur = new BoxBlur(1.0, 1.0, 1);
    Rotate rotate = new Rotate();
    Scale scale = new Scale(scaleFactor, scaleFactor);

    // Create push buttons.
    Button btnRotate = new Button("Rotate");
    Button btnBlur = new Button("Blur off");
    Button btnScale = new Button("Scale");

    Label reflect = new Label("Reflection Adds Visual Sparkle");

    public static void main(String[] args) {

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    // Override the start() method.
    public void start(Stage myStage) {

        // Give the stage a title.
        myStage.setTitle("Effects and Transforms Demo");

        // Use a FlowPane for the root node. In this case,
        // vertical and horizontal gaps of 20 are used.
        FlowPane rootNode = new FlowPane(20, 20);

        // Center the controls in the scene.
        rootNode.setAlignment(Pos.CENTER);

        // Create a scene.
        Scene myScene = new Scene(rootNode, 300, 120);

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Add rotation to the transform list for the Rotate button.
        btnRotate.getTransforms().add(rotate);

        // Add scaling to the transform list for the Scale button.
        btnScale.getTransforms().add(scale);

        // Set the reflection effect on the reflection label.
        reflection.setTopOpacity(0.7);
        reflection.setBottomOpacity(0.3);
        reflect.setEffect(reflection);

        // Handle the action events for the Rotate button.
        btnRotate.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Each time button is pressed, it is rotated 30 degrees
                // around its center.
                angle += 15.0;

                rotate.setAngle(angle);
                rotate.setPivotX(btnRotate.getWidth()/2);
                rotate.setPivotY(btnRotate.getHeight()/2);
            }
        });

        // Handle the action events for the Scale button.
        btnScale.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Each time button is pressed, the button's scale is changed.
                scaleFactor += 0.1;
                if(scaleFactor > 2.0) scaleFactor = 0.4;

                scale.setX(scaleFactor);
                scale.setY(scaleFactor);

            }
        });

        // Handle the action events for the Blur button.
        btnBlur.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Each time button is pressed, its blur status is changed.
                if(blurVal == 10.0) {
                    blurVal = 1.0;
                    btnBlur.setEffect(null);
                    btnBlur.setText("Blur off");
                } else {
                    blurVal++;
                    btnBlur.setEffect(blur);
                    btnBlur.setText("Blur on");
                }
                blur.setWidth(blurVal);
                blur.setHeight(blurVal);
            }
        });

        // Add the label and buttons to the scene graph.
        rootNode.getChildren().addAll(btnRotate, btnScale, btnBlur, reflect);

        // Show the stage and its scene.
        myStage.show();
    }
}

