package client;

import client.screens.LoginScreen;
import client.util.SceneManager;
import javafx.application.Application;
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
