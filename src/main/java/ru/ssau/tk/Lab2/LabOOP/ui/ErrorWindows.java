package ru.ssau.tk.Lab2.LabOOP.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorWindows {

    public void showAlertWithoutHeaderText(Exception e) {
        String message = generateMessageForException(e);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error message");
        alert.setHeaderText(message);
        VBox dialogPaneContent = new VBox();
        Label label = new Label("Stack Trace:");
        String stackTrace = this.getStackTrace(e);
        TextArea textArea = new TextArea();
        textArea.setText(stackTrace);
        dialogPaneContent.getChildren().addAll(label, textArea);
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }

    private String generateMessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "A number is expected, not a string.";
        }
        return "Unknown error.";
    }

    private String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String s = sw.toString();
        return s;
    }
}

