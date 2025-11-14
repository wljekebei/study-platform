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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RegistrationScreen {
    public static Scene getScene() {
        Label header = new Label("SIGN UP");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 52));

        TextField nameField = new TextField();
        ElementSetup.tfSetup(nameField, "first name");

        TextField surnameField = new TextField();
        ElementSetup.tfSetup(surnameField, "second name");
        
        TextField emailField = new TextField();
        ElementSetup.tfSetup(emailField, "email");

        TextField usernameField = new TextField();
        ElementSetup.tfSetup(usernameField, "username");

        PasswordField passwordField = new PasswordField();
        passwordField.setFont(Font.font("Arial", 18));
        passwordField.setPromptText("password");
        passwordField.setPrefWidth(228);
        passwordField.setFocusTraversable(false);

        TextField passwordVisible = new TextField();
        passwordVisible.setFont(Font.font("Arial", 18));
        passwordVisible.setPromptText("password");
        passwordVisible.setPrefWidth(228);
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


        Button regButton = new Button("CREATE ACCOUNT");
        regButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        regButton.setDefaultButton(true);
        ElementSetup.buttonSetup(regButton, "10", "18");
        regButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // ADD LOGIC FOR REGISTRATION

                // if created account correctly
                // scenemanager.showMainScene
            }
        });

        HBox passwordBox = new HBox(passwordField, passwordVisible, passwordButton);
        passwordBox.setSpacing(10);

        VBox regBox = new VBox(nameField, surnameField, emailField, usernameField, passwordBox);
        regBox.setSpacing(20);
        regBox.setPadding(new Insets(0, 100, 0, 100));
        regBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(header, regBox, regButton);
        root.setSpacing(60);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 500, 600);
    }
}