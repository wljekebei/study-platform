package client;

import client.screens.GroupScreen;
import client.screens.LoginScreen;
import client.screens.RegistrationScreen;
import client.util.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;

public class Main  extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        SceneManager.init(stage);
//        stage.setScene(LoginScreen.getScene());
//        stage.setTitle("Login");
//        stage.show();
        Scene scene = GroupScreen.getScene(
                "ACSS",
                124637,
                "Description",
                List.of(
                        new GroupScreen.UserEntry("Kolay", "owner"),
                        new GroupScreen.UserEntry("Vasyl", "admin"),
                        new GroupScreen.UserEntry("Random", "member"),
                        new GroupScreen.UserEntry("Kokot", "admin"),
                        new GroupScreen.UserEntry("Rostik", "member"),
                        new GroupScreen.UserEntry("Tarasik", "member")
                ),
                List.of(
                        new GroupScreen.TaskEntry("Create DB",  "DEscription apsodkpa osaoksdpao", "2025-06-13", "In progress"),
                        new GroupScreen.TaskEntry("Hello World sasj", "DEscription apsodkpao","2027-12-01", "Done"),
                        new GroupScreen.TaskEntry("Vasyl Hrytsiuk pici", "DEscription apsodkpaoskdpaoksdpao","2025-12-21", "Open"),
                        new GroupScreen.TaskEntry("JAAAJ", "DEscription apsodkpaoskao","2025-01-06", "Done"),
                        new GroupScreen.TaskEntry("I love my mom", "DEscription apsodk aoksdpao vfgsdfs","2025-02-12", "In progress")
                )
        );
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
