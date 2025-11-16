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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CreateScreen {
    public static Scene getScene() {
        Label header = new Label("CREATE GROUP");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        TextField nameField = new TextField();
        ElementSetup.tfSetup(nameField, "Group Name");

        TextArea description = new TextArea();
        description.setPrefWidth(300);
        description.setPrefHeight(150);
        description.setWrapText(true);
        description.setPromptText("Group Description");
        description.setFont(Font.font("Arial", 18));
        description.setFocusTraversable(false);

        Button createButton = new Button("CREATE");
        createButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        createButton.setDefaultButton(false);
        ElementSetup.buttonSetup(createButton, "10", "18");
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // creating logic
                // SceneManager.toGroup

                // REMOVE
                SceneManager.toGroupsScreen();
                // REMOVE ^
            }
        });

        Button backButton = new Button("BACK");
        backButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        backButton.setDefaultButton(false);
        ElementSetup.buttonSetup(backButton, "10", "12");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toGroupsScreen();
            }
        });

        VBox nameDesc = new VBox(nameField, description);
        nameDesc.setAlignment(Pos.CENTER);
        nameDesc.setSpacing(20);

        HBox buttonsBox = new HBox(backButton, createButton);
        buttonsBox.setAlignment(Pos.CENTER_LEFT);
        buttonsBox.setPadding(new Insets(0, 0, 0, 10));
        buttonsBox.setSpacing(50);

        VBox root = new VBox(header, nameDesc, buttonsBox);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);
        root.setPadding(new Insets(30, 30, 30, 30));
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 400, 450);
    }
}
