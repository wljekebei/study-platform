package client.screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class LoginScreen {
    public static Scene getScene() {
        Label header = new Label("LOGIN");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 52));
        header.setStyle("-fx-text-fill: #4a4944;");

        TextField usernameField = new TextField();
        usernameField.setFont(Font.font("Arial", 18));
        usernameField.setPromptText("username"); // hint text
        usernameField.setFocusTraversable(false);

        TextField passwordField = new TextField();
        passwordField.setFont(Font.font("Arial", 18));
        passwordField.setPromptText("password"); // hint text
        passwordField.setFocusTraversable(false);

        Button loginButton = new Button("LOGIN");
        loginButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        loginButton.setDefaultButton(true);
        loginButton.setStyle("""
            -fx-background-color: #4fa3a5;
            -fx-text-fill: white;
            -fx-font-size: 18pt;
            -fx-background-radius: 10;
        """);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // ADD LOGIC FOR LOGGING IN

                // if logged in correctly
                // scenemanager.showMainScene
            }
        });

        VBox logBox = new VBox(usernameField, passwordField);
        logBox.setSpacing(20);
        logBox.setPadding(new Insets(0, 70, 0, 70));
        logBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(header, logBox, loginButton);
        root.setSpacing(60);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f5f5dc;");

        return new Scene(root, 400, 400);
    }
}
