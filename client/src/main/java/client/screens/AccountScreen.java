package client.screens;

import client.components.ElementSetup;
import client.models.User;
import client.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AccountScreen {
    public static Scene getScene(User user) {
        Label header = new Label("EDIT DATA");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 38));

        Label errorLabel = new Label();
        errorLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 18));
        errorLabel.setStyle("-fx-text-fill: red;");

        TextField usernameField = new TextField();
        ElementSetup.tfSetup(usernameField, "Change Username");
        usernameField.setText(user.getName());
        usernameField.setFocusTraversable(false);

        TextField emailField = new TextField();
        ElementSetup.tfSetup(usernameField, "Change Email");
        emailField.setText(user.getEmail());
        emailField.setFocusTraversable(false);

        usernameField.setMaxWidth(350);
        emailField.setMaxWidth(350);
        usernameField.setFont(Font.font("Arial", FontWeight.MEDIUM, 18));
        emailField.setFont(Font.font("Arial", FontWeight.MEDIUM, 18));

        VBox tfBox = new VBox(usernameField, emailField, errorLabel);
        tfBox.setAlignment(Pos.CENTER);
        tfBox.setSpacing(10);

        Button saveButton = new Button("SAVE");
        saveButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        saveButton.setDefaultButton(true);
        ElementSetup.buttonSetup(saveButton, "10", "18");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // ADD LOGIC FOR SAVING
                if (usernameField.getText().isEmpty()) {
                    errorLabel.setText("Username can not be empty!");
                } else if (!(emailField.getText().contains("@") && emailField.getText().contains("."))) {
                    errorLabel.setText("Email address is incorrect!");
                } else {
                    user.setName(usernameField.getText());
                    user.setEmail(emailField.getText());
                    SceneManager.toGroupsScreen();
                }
            }
        });

        Button statsButton = new Button("STATS");
        statsButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        statsButton.setDefaultButton(true);
        ElementSetup.buttonSetup(statsButton, "10", "18");
        statsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toUserStats();
            }
        });

        HBox buttonBox = new HBox(statsButton, saveButton);
        buttonBox.setSpacing(40);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(header, tfBox, buttonBox);
        root.setSpacing(20);
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 400, 350);
    }
}
