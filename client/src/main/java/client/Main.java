package client;

import client.screens.LoginScreen;
import client.screens.RegistrationScreen;
import client.util.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main  extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        SceneManager.init(stage);
        stage.setScene(LoginScreen.getScene());
        stage.setTitle("Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
