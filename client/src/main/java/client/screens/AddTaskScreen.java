package client.screens;

import client.components.ElementSetup;
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

public class AddTaskScreen {
    public static Scene getScene() {
        Label header = new Label("NEW TASK");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 40));

        TextField titleField = new TextField();
        ElementSetup.tfSetup(titleField, "Title");

        TextField descField = new TextField();
        ElementSetup.tfSetup(descField, "Description");

        TextField deadlineField = new TextField(); // max 10 chars
        ElementSetup.tfSetup(deadlineField, "Deadline");
        deadlineField.setMaxWidth(160);

        Button confButton = new Button("CREATE TASK");
        confButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        confButton.setDefaultButton(true);
        ElementSetup.buttonSetup(confButton, "10", "16");
        confButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // back to group, saving info logic
                // SceneManager.toGroup

                // REMOVE
                SceneManager.toGroupsScreen();
                // REMOVE ^
            }
        });

        Button backButton = new Button("BACK");
        backButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        backButton.setDefaultButton(false);
        ElementSetup.buttonSetup(backButton, "10", "16");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // SM.toGroup()
                SceneManager.toGroupsScreen();
            }
        });

        HBox buttonsBox = new HBox(backButton, confButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(50);

        VBox fieldBox = new VBox(titleField, descField, deadlineField);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setSpacing(15);

        VBox root = new VBox(header, fieldBox, buttonsBox);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);
        root.setPadding(new Insets(30, 30, 30, 30));
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 400, 450);
    }
}
