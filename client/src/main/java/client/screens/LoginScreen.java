package client.screens;

import client.components.ElementSetup;
import client.models.User;
import client.services.AuthAPI;
import client.services.Session;
import client.util.MockDB;
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
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class LoginScreen {
    public static Scene getScene() {
        Label header = new Label("SIGN IN");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 52));

        Label errorLabel = new Label();
        errorLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 18));
        errorLabel.setStyle("-fx-text-fill: red;");

        TextField emailField = new TextField();
        ElementSetup.tfSetup(emailField, "Email");
        emailField.setFocusTraversable(true);


        PasswordField passwordField = new PasswordField();
        passwordField.setFont(Font.font("Arial", 18));
        passwordField.setPromptText("Password");
        passwordField.setPrefWidth(188);
        passwordField.setFocusTraversable(true);

        TextField passwordVisible = new TextField();
        passwordVisible.setFont(Font.font("Arial", 18));
        passwordVisible.setPromptText("Password");
        passwordVisible.setPrefWidth(188);
        passwordVisible.setFocusTraversable(false);
        passwordVisible.setManaged(false);
        passwordVisible.setVisible(false);

        Button passwordButton = new Button("SHOW");
        passwordButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        passwordButton.setPrefWidth(72);
        passwordButton.setDefaultButton(false);
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

        Button loginButton = new Button("LOG IN");
        loginButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        loginButton.setDefaultButton(true);
        ElementSetup.buttonSetup(loginButton, "10", "18");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String pwd = passwordField.isVisible()
                        ? passwordField.getText()
                        : passwordVisible.getText();

                if (emailField.getText().isBlank() || pwd.isBlank()) {
                    errorLabel.setText("Email and password can not be empty!");
                }

                try {
                    User u = AuthAPI.login(
                            emailField.getText(),
                            pwd
                    );

                    Session.setUser(u);
                    SceneManager.toGroupsScreen();

                } catch (Exception ex) {
                    errorLabel.setText("Login failed");
                    throw new RuntimeException(ex);
                }
            }
        });

        Label signUpLink = new Label("Don't have an account?  Sign up");
        signUpLink.setFont(Font.font("Arial", 15));
        signUpLink.setStyle("""
            -fx-text-fill: #4D7CFE;
            -fx-underline: true;
            -fx-cursor: hand;
        """);

        signUpLink.setOnMouseClicked(e -> {
            SceneManager.toRegistration();
        });


        HBox passwordBox = new HBox(passwordField, passwordVisible, passwordButton);
        passwordBox.setSpacing(10);

        VBox logBox = new VBox(emailField, passwordBox, signUpLink);
        logBox.setSpacing(20);
        logBox.setPadding(new Insets(0, 70, 0, 70));
        logBox.setAlignment(Pos.CENTER);

        VBox upBox = new VBox(header, errorLabel, logBox);
        upBox.setSpacing(10);
        upBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(upBox, loginButton);
        root.setSpacing(60);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 400, 400);
    }

//    public static User findUserByEmail(String email) {
//        for (User u : MockDB.getUsers()) {
//            if (u.getEmail().equals(email)) {
//                return u;
//            }
//        }
//        return null;
//    }

}
