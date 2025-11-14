package client.screens;

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
        Label header = new Label("REGISTRATION");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 52));
        header.setStyle("-fx-text-fill: #4a4944;");

        TextField nameField = new TextField();
        nameField.setFont(Font.font("Arial", 18));
        nameField.setPromptText("first name"); 
        nameField.setFocusTraversable(false);

        TextField surnameField = new TextField();
        surnameField.setFont(Font.font("Arial", 18));
        surnameField.setPromptText("second name"); 
        surnameField.setFocusTraversable(false);
        
        TextField emailField = new TextField();
        emailField.setFont(Font.font("Arial", 18));
        emailField.setPromptText("email"); 
        emailField.setFocusTraversable(false);

        TextField usernameField = new TextField();
        usernameField.setFont(Font.font("Arial", 18));
        usernameField.setPromptText("username"); 
        usernameField.setFocusTraversable(false);

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
        passwordButton.setStyle("""
            -fx-background-color: #4fa3a5;
            -fx-text-fill: white;
            -fx-font-size: 11pt;
            -fx-background-radius: 5;
        """);
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
        regButton.setStyle("""
            -fx-background-color: #4fa3a5;
            -fx-text-fill: white;
            -fx-font-size: 18pt;
            -fx-background-radius: 10;
        """);
        regButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // ADD LOGIC FOR REGISTRATION

                // if created account correctly
                // scenemanager.showMainScene
            }
        });

        HBox passwordBox = new HBox(passwordField, passwordVisible, passwordButton);
        passwordBox.setPadding(new Insets(0, 0, 0, 0));
        passwordBox.setSpacing(10);

        VBox regBox = new VBox(nameField, surnameField, emailField, usernameField, passwordBox);
        regBox.setSpacing(20);
        regBox.setPadding(new Insets(0, 100, 0, 100));
        regBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(header, regBox, regButton);
        root.setSpacing(60);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f5f5dc;");

        return new Scene(root, 500, 600);
    }
}
