package client.screens;

import client.components.ElementSetup;
import client.models.Group;
import client.models.Task;
import client.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TaskConfigScreen {
    public static Scene getScene(Task task, Group group) {
        Label header = new Label("CONFIGURATE TASK");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        TextField titleField = new TextField();
        ElementSetup.tfSetup(titleField, "Title");
        titleField.setText(task.getTitle());

        TextArea descField = new TextArea();
        descField.setStyle("""
                -fx-font-size: 18;
                """);
        descField.setPromptText("Description");
        descField.setText(task.getDescription());
        descField.setWrapText(true);
        descField.setPrefHeight(70);

        DatePicker deadlinePicker = new DatePicker();
        deadlinePicker.setPromptText("Deadline");
        deadlinePicker.setFocusTraversable(false);
        deadlinePicker.setStyle("-fx-font-size: 18");

        try {
            deadlinePicker.setValue(java.time.LocalDate.parse(task.getDeadline()));
        } catch (Exception ignored) {}


        ComboBox<String> statusChoice = new ComboBox<>();
        statusChoice.getItems().add("Open");
        statusChoice.getItems().add("In progress");
        statusChoice.getItems().add("Done");
        statusChoice.setValue(task.getStatus());
        statusChoice.setPrefWidth(250);
        statusChoice.setStyle("""
                -fx-background-color: #4D7CFE;
                -fx-text-fill: white;
                -fx-font-size: 18;
                -fx-background-radius: 8;
                """);

        statusChoice.setMaxWidth(160);
        deadlinePicker.setMaxWidth(160);

        Button confButton = new Button("CONFIRM");
        confButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        confButton.setDefaultButton(true);
        ElementSetup.buttonSetup(confButton, "10", "16");
        confButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                task.setTitle(titleField.getText());
                task.setDescription(descField.getText());
                if (deadlinePicker.getValue() != null) {
                    task.setDeadline(deadlinePicker.getValue().toString());
                }
                task.setStatus(statusChoice.getValue());
                try {
                    SceneManager.toGroup(group);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button backButton = new Button("BACK");
        backButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        backButton.setDefaultButton(false);
        ElementSetup.buttonSetup(backButton, "10", "16");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    SceneManager.toGroup(group);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        HBox buttonsBox = new HBox(backButton, confButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(50);

        VBox fieldBox = new VBox(titleField, descField, deadlinePicker, statusChoice);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setSpacing(10);

        VBox root = new VBox(header, fieldBox, buttonsBox);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);
        root.setPadding(new Insets(30, 30, 30, 30));
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 400, 500);
    }
}
