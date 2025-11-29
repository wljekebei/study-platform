package client.screens;

import client.components.ElementSetup;
import client.dto.UserStats;
import client.models.ActivityLog;
import client.models.Group;
import client.models.Task;
import client.models.User;
import client.services.*;
import client.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserStatsScreen {

    public static Scene getScene(User user, Group group) throws Exception {

        List<Group> userGroups = UserAPI.getUserGroups(user.getId());

        UserStats stats = UserStatsAPI.get(user.getId());

        List<Task> userTasks = new ArrayList<>();
        for (Group g : userGroups) {
            userTasks.addAll(TaskAPI.getByGroup(g.getGroupId()));
        }

        Label header = new Label("STATS");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 40));

        Label userLabel = new Label(user.getName());
        userLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 22));
        userLabel.setStyle("-fx-text-fill: gray;");

        HBox titleBox = new HBox(header, userLabel);
        titleBox.setSpacing(30);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        Button backButton = new Button("BACK");
        backButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        backButton.setDefaultButton(false);
        ElementSetup.buttonSetup(backButton, "10", "12");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (user.equals(Session.getUser())) SceneManager.toGroupsScreen();
                else {
                    try {
                        SceneManager.toGroup(group);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        HBox topBox = new HBox(backButton, titleBox);
        topBox.setSpacing(20);
        topBox.setAlignment(Pos.CENTER_LEFT);

        // stats
        List<ActivityLog> logs = ActivityLogAPI.getByUser(user.getId());

        String prettyLastLogin = "-";
        if (!logs.isEmpty()) {
            ActivityLog last = logs.get(logs.size() - 1);
            String lastLogin = last.getTimestamp();
            LocalDateTime dt = LocalDateTime.parse(lastLogin);
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            prettyLastLogin = dt.format(fmt);
        }

        VBox lastLoginBox = createStatBox("LAST LOGIN", prettyLastLogin);
        lastLoginBox.getChildren().get(1).setStyle("-fx-font-size: 20; -fx-font-weight: BOLD");
        VBox groupsBox = createStatBox("GROUPS", stats.groupsCount.toString());
        VBox tasksBox = createStatBox("TASKS", stats.totalTasks.toString());
        VBox resBox = createStatBox("RESOURCES", stats.uploadedResources.toString());

        HBox statsBoxes = new HBox(lastLoginBox, groupsBox, tasksBox, resBox);
        statsBoxes.setSpacing(20);
        statsBoxes.setAlignment(Pos.CENTER);

        // done / not done
        PieChart completionChart = new PieChart();
        completionChart.setTitle("TASK COMPLETION");

        if (stats.totalTasks > 0) {
            completionChart.getData().add(new PieChart.Data("DONE", stats.tasksDone));
            completionChart.getData().add(new PieChart.Data("NOT DONE", stats.tasksNotDone));
        }

        Pane completionPane = wrapBox(completionChart);

        // tasks per group
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("GROUP");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("TASKS");

        // chatgpt
        BarChart<String, Number> groupChart = new BarChart<>(xAxis, yAxis);
        groupChart.setTitle("TASKS PER GROUP");
        groupChart.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (Group g : userGroups) {
            long count = 0;

            for (Task t : userTasks) {
                if (Objects.equals(t.getGroupId(), g.getGroupId())) {
                    count++;
                }
            }

            series.getData().add(new XYChart.Data<>(g.getName(), count));
        }


        groupChart.getData().add(series);
        groupChart.setCategoryGap(20);
        groupChart.setBarGap(5);

        Pane groupChartPane = wrapBox(groupChart);

        HBox chartsBox = new HBox(completionPane, groupChartPane);
        chartsBox.setSpacing(30);
        chartsBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(topBox, statsBoxes, chartsBox);
        root.setSpacing(35);
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 800, 600);
    }

    private static VBox createStatBox(String title, String value) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));

        VBox box = new VBox(titleLabel, valueLabel);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.setPadding(new Insets(15, 25, 15, 25));
        box.setStyle("""
                -fx-background-color: #C5D6FF;
                -fx-background-radius: 12;
            """);

        return box;
    }

    private static Pane wrapBox(javafx.scene.Node node) {
        VBox box = new VBox(node);
        box.setPadding(new Insets(15));
        box.setStyle("""
                    -fx-background-color: #C5D6FF;
                    -fx-background-radius: 12;
                """);
        return box;
    }
}
