package client.util;

import client.screens.LoginScreen;
import client.screens.RegistrationScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static Stage stage; // static - exists once in all program

    public static void init(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void toRegistration() {
        stage.setScene(RegistrationScreen.getScene());
        stage.setTitle("Sign up");
        stage.show();
    }

    public static void toLogin() {
        stage.setScene(LoginScreen.getScene());
        stage.setTitle("Login");
        stage.show();
    }
}
