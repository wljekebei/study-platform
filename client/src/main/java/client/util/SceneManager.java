package client.util;

import client.models.Group;
import client.models.Task;
import client.models.User;
import client.screens.*;
import javafx.stage.Stage;

import java.util.List;

public class SceneManager {
    private static Stage stage; // static - exists once in all program

    public static void init(Stage primaryStage) {
        stage = primaryStage;
    }

    public static Stage getStage() {
        return stage;
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
        try {
            stage.setScene(GroupsScreen.getScene());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public static void toTaskConfig(Task task, Group group) {
        stage.setScene(TaskConfigScreen.getScene(task, group));
        stage.setTitle("Task Configuration");
        stage.show();
    }

    public static void toAddTask(Group group) {
        stage.setScene(AddTaskScreen.getScene(group));
        stage.setTitle("New Task");
        stage.show();
    }

    public static void toRemoveTask(Group group, List<Task> tasks) {
        stage.setScene(RemoveTaskScreen.getScene(group, tasks));
        stage.setTitle("Remove Task");
        stage.show();
    }

    public static void toGroup(Group group) throws Exception {
        stage.setScene(GroupScreen.getScene(group));
        stage.setTitle("Group");
        stage.show();
    }

    public static void toAccount(User user) {
        stage.setScene(AccountScreen.getScene(user));
        stage.setTitle("Account Configuration");
        stage.show();
    }

    public static void toResources(Group group) {
        stage.setScene(ResourcesScreen.getScene(group));
        stage.setTitle("Resources");
        stage.show();
    }

    public static void toAddResource(Group group) {
        stage.setScene(AddResourceScreen.getScene(group));
        stage.setTitle("Add Recource");
        stage.show();
    }

    public static void toRmResource(Group group) {
        stage.setScene(RemoveResourceScreen.getScene(group));
        stage.setTitle("Remove Recource");
        stage.show();
    }

    public static void toGroupConfig(Group group) {
        stage.setScene(GroupConfigScreen.getScene(group));
        stage.setTitle("Group Configuration");
        stage.show();
    }

    public static void toRmUser(Group group) {
        try {
            stage.setScene(RemoveUserScreen.getScene(group));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Remove User");
        stage.show();
    }

    public static void toUserStats(User user, Group group) {
        try {
            stage.setScene(UserStatsScreen.getScene(user, group));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("User Stats");
        stage.show();
    }

    public static void toGroupStats(Group group) {
        try {
            stage.setScene(GroupStatsScreen.getScene(group));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        stage.setTitle("User Stats");
        stage.show();
    }
}
