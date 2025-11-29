package client.screens;

import client.components.ElementSetup;
import client.models.User;
import client.services.AuthAPI;
import client.services.Session;
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

public class RegistrationScreen {
    public static Scene getScene() {
        Label header = new Label("SIGN UP");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 52));

        TextField emailField = new TextField();
        ElementSetup.tfSetup(emailField, "Email");

        TextField usernameField = new TextField();
        ElementSetup.tfSetup(usernameField, "Username");

        PwdFields pwdFields = RegistrationScreen.CreatePwdBox("Password");

        PwdFields repFields = RegistrationScreen.CreatePwdBox("Repeat password");

        Label infoLabel = new Label("Log In");
        infoLabel.setFont(Font.font("Arial", 15));
        infoLabel.setStyle("""
            -fx-text-fill: #4D7CFE;
            -fx-underline: true;
            -fx-cursor: hand;
        """);

        infoLabel.setOnMouseClicked(e -> {
            SceneManager.toLogin();
        });

        Button regButton = new Button("CREATE ACCOUNT");
        regButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        regButton.setDefaultButton(true);
        ElementSetup.buttonSetup(regButton, "10", "18");
        regButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String pwd1 = pwdFields.pwdField.isVisible()
                        ? pwdFields.pwdField.getText()
                        : pwdFields.pwdVisible.getText();

                String pwd2 = repFields.pwdField.isVisible()
                        ? repFields.pwdField.getText()
                        : repFields.pwdVisible.getText();

                if (!(emailField.getText().contains("@") && emailField.getText().contains("."))) {
                    infoLabel.setText("Email address is incorrect!");
                    infoLabel.setStyle("-fx-text-fill: red;");
                } else if (!(pwd1.equals(pwd2))) {
                    infoLabel.setText("Passwords are different!");
                    infoLabel.setStyle("-fx-text-fill: red;");
                } else if (usernameField.getText().isEmpty()) {
                    infoLabel.setText("Username can not be empty!");
                    infoLabel.setStyle("-fx-text-fill: red;");
                } else if (pwd1.isBlank()) {
                    infoLabel.setText("Password can not be empty!");
                    infoLabel.setStyle("-fx-text-fill: red;");
                } else {
                    try {
                        User u = AuthAPI.register(
                                usernameField.getText(),
                                emailField.getText(),
                                pwd1
                        );

                        Session.setUser(u);
                        SceneManager.toGroupsScreen();

                    } catch (Exception ex) {
                        infoLabel.setText("Registration failed");
                        infoLabel.setStyle("-fx-text-fill: red;");
                    }


                }
            }
        });

        VBox regBox = new VBox(emailField, usernameField, pwdFields.pwdBox, repFields.pwdBox, infoLabel);
        regBox.setSpacing(20);
        regBox.setPadding(new Insets(0, 100, 0, 100));
        regBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(header, regBox, regButton);
        root.setSpacing(40);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 500, 500);
    }

    static PwdFields CreatePwdBox(String hintText) {
        PasswordField passwordField = new PasswordField();
        passwordField.setFont(Font.font("Arial", 18));
        passwordField.setPromptText(hintText);
        passwordField.setPrefWidth(228);
        passwordField.setFocusTraversable(false);

        TextField passwordVisible = new TextField();
        passwordVisible.setFont(Font.font("Arial", 18));
        passwordVisible.setPromptText(hintText);
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

        HBox passwordBox = new HBox(passwordField, passwordVisible, passwordButton);
        passwordBox.setSpacing(10);

        PwdFields pwdFields = new PwdFields(passwordField, passwordVisible, passwordBox);

        return pwdFields;
    }
}

class PwdFields {
    PasswordField pwdField;
    TextField pwdVisible;
    HBox pwdBox;

    public PwdFields(PasswordField pwdField, TextField pwdVisible, HBox pwdBox) {
        this.pwdField = pwdField;
        this.pwdVisible = pwdVisible;
        this.pwdBox = pwdBox;
    }
}