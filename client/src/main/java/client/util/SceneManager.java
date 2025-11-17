package client.util;

import client.screens.*;
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

    public static void toGroupsScreen() {
        stage.setScene(GroupsScreen.getScene());
        stage.setTitle("My Groups");
        stage.show();
    }

    public static void toJoin() {
        stage.setScene(JoinScreen.getScene());
        stage.setTitle("Join Group");
        stage.show();
    }

    public static void toCreate() {
        stage.setScene(CreateScreen.getScene());
        stage.setTitle("Create Group");
        stage.show();
    }

    public static void toTaskConfig() {
        stage.setScene(TaskConfigScreen.getScene());
        stage.setTitle("Task Configuration");
        stage.show();
    }

    public static void toAddTask() {
        stage.setScene(AddTaskScreen.getScene());
        stage.setTitle("New Task");
        stage.show();
    }

    public static void toRemoveTask() {
        stage.setScene(RemoveTaskScreen.getScene());
        stage.setTitle("Remove Task");
        stage.show();
    }
}
