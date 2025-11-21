package client.screens;

import client.components.ElementSetup;
import client.dto.GroupResponse;
import client.models.Group;
import client.services.GroupAPI;
import client.util.SceneManager;
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

public class GroupConfigScreen {
    public static Scene getScene(Group group) {
        Label header = new Label("CONFIGURATE GROUP");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        Label errorLabel = new Label();
        errorLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        errorLabel.setStyle("-fx-text-fill: red;");

        TextField nameField = new TextField();
        ElementSetup.tfSetup(nameField, "Name");
        nameField.setText(group.getName());

        TextArea descField = new TextArea();
        descField.setPromptText("Description");
        descField.setFocusTraversable(false);
        descField.setText(group.getDescription());
        descField.setWrapText(true);
        descField.setPrefHeight(70);

        descField.setStyle("""
                -fx-font-size: 18;
                """);

        Button confButton = new Button("CONFIRM");
        confButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        ElementSetup.buttonSetup(confButton, "10", "16");
        confButton.setOnAction(a -> {
            if (nameField.getText().length() > 10) {
                errorLabel.setText("Group name can contain max. 10 characters");
            } else if (nameField.getText().isEmpty()) {
                errorLabel.setText("Group name can not be empty");
            } else if (descField.getText().length() > 45) {
                errorLabel.setText("Description can contain max. 45 characters");
            } else {
                try {
                    GroupResponse updated = GroupAPI.update(group.getGroupId(), nameField.getText(), descField.getText());
                    group.setName(updated.getName());
                    group.setDescription(updated.getDescription());
                    SceneManager.toGroup(group);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button backButton = new Button("BACK");
        backButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        ElementSetup.buttonSetup(backButton, "10", "16");
        backButton.setOnAction(a -> {
            try {
                SceneManager.toGroup(group);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Button remUserButton = new Button("REMOVE USER");
        remUserButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        ElementSetup.buttonSetup(remUserButton, "10", "16");
        remUserButton.setPrefWidth(180);
        remUserButton.setOnAction(a -> {
            SceneManager.toRmUser(group);
        });

        Button delButton = new Button("DELETE GROUP");
        delButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 18));
        delButton.setPrefWidth(180);
        delButton.setStyle("""
                -fx-background-color: red;
                -fx-text-fill: white;
                -fx-font-size:20;
                -fx-background-radius: 10;
                -fx-cursor: hand;
                """);
        delButton.setOnAction(a -> {
            try {
                GroupAPI.delete(group.getGroupId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            SceneManager.toGroupsScreen();
        });

        HBox topButtonsBox = new HBox(remUserButton, delButton);
        topButtonsBox.setAlignment(Pos.CENTER);
        topButtonsBox.setSpacing(30);

        HBox bottomButtonsBox = new HBox(backButton, confButton);
        bottomButtonsBox.setAlignment(Pos.CENTER);
        bottomButtonsBox.setSpacing(30);

        VBox fieldBox = new VBox(errorLabel, nameField, descField, topButtonsBox);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setSpacing(10);

        VBox root = new VBox(header, fieldBox, bottomButtonsBox);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);
        root.setPadding(new Insets(30, 30, 30, 30));
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 500, 500);
    }
}
