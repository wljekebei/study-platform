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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

        TextField descField = new TextField();
        ElementSetup.tfSetup(descField, "Description");
        descField.setText(task.getDescription());

        TextField deadlineField = new TextField(); // max 10 chars
        ElementSetup.tfSetup(deadlineField, "Deadline");
        deadlineField.setText(task.getDeadline());

        TextField progressField = new TextField(); // max 10 chars
        ElementSetup.tfSetup(progressField, "Progress");
        progressField.setText(task.getStatus());

        deadlineField.setMaxWidth(160);
        progressField.setMaxWidth(160);

        Button confButton = new Button("CONFIRM");
        confButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        confButton.setDefaultButton(true);
        ElementSetup.buttonSetup(confButton, "10", "16");
        confButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                task.setTitle(titleField.getText());
                task.setDescription(descField.getText());
                task.setDeadline(deadlineField.getText());
                task.setStatus(progressField.getText());
                SceneManager.toGroup(group);
            }
        });

        Button backButton = new Button("BACK");
        backButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        backButton.setDefaultButton(false);
        ElementSetup.buttonSetup(backButton, "10", "16");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toGroup(group);
            }
        });

        HBox buttonsBox = new HBox(backButton, confButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(50);

        VBox fieldBox = new VBox(titleField, descField, deadlineField, progressField);
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
