package client;

import client.screens.LoginScreen;
import client.screens.RegistrationScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main  extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(RegistrationScreen.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
