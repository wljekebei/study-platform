package client.screens;

import client.components.ElementSetup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class LoginScreen {
    public static Scene getScene() {
        Label header = new Label("LOGIN");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 52));

        TextField usernameField = new TextField();
        ElementSetup.tfSetup(usernameField, "email / username");

        PasswordField passwordField = new PasswordField();
        passwordField.setFont(Font.font("Arial", 18));
        passwordField.setPromptText("password");
        passwordField.setPrefWidth(188);
        passwordField.setFocusTraversable(false);

        TextField passwordVisible = new TextField();
        passwordVisible.setFont(Font.font("Arial", 18));
        passwordVisible.setPromptText("password");
        passwordVisible.setPrefWidth(188);
        passwordVisible.setFocusTraversable(false);
        passwordVisible.setManaged(false);
        passwordVisible.setVisible(false);

        Button passwordButton = new Button("SHOW");
        passwordButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        passwordButton.setPrefWidth(72);
        passwordButton.setDefaultButton(true);
        ElementSetup.buttonSetup(passwordButton, "5", "11");
        passwordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (passwordVisible.isVisible()) {
                    passwordField.setText(passwordVisible.getText());
                    passwordVisible.setManaged(false);
                    passwordVisible.setVisible(false);
                    passwordField.setManaged(true);
                    passwordField.setVisible(true);

                    passwordButton.setText("SHOW");
                } else {
                    passwordVisible.setText(passwordField.getText());
                    passwordField.setManaged(false);
                    passwordField.setVisible(false);
                    passwordVisible.setManaged(true);
                    passwordVisible.setVisible(true);

                    passwordButton.setText("HIDE");
                }
            }
        });

        Button loginButton = new Button("LOGIN");
        loginButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        loginButton.setDefaultButton(true);
        ElementSetup.buttonSetup(loginButton, "5", "18");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // ADD LOGIC FOR LOGGING IN

                // if logged in correctly
                // scenemanager.showMainScene
            }
        });

        HBox passwordBox = new HBox(passwordField, passwordVisible, passwordButton);
        passwordBox.setSpacing(10);

        VBox logBox = new VBox(usernameField, passwordBox);
        logBox.setSpacing(20);
        logBox.setPadding(new Insets(0, 70, 0, 70));
        logBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(header, logBox, loginButton);
        root.setSpacing(60);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 400, 400);
    }
}
