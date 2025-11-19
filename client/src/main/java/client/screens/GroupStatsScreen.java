package client.screens;

import client.components.ElementSetup;
import client.models.Group;
import client.models.Task;
import client.models.Resource;
import client.util.MockDB;
import client.util.SceneManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupStatsScreen {

    private static List<Task> tasks = MockDB.getTasks();
    private static List<Resource> resources = MockDB.getResources();

    public static Scene getScene(Group group) {
        Label header = new Label("GROUP STATS");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        Label groupName = new Label(group.getName());
        groupName.setFont(Font.font("Arial", FontWeight.NORMAL, 26));
        groupName.setStyle("-fx-text-fill: gray;");

        HBox titleBox = new HBox(header, groupName);
        titleBox.setSpacing(20);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        Button backButton = new Button("BACK");
        backButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        backButton.setDefaultButton(false);
        ElementSetup.buttonSetup(backButton, "10", "12");
        backButton.setOnAction(e -> SceneManager.toGroup(group));

        HBox topBox = new HBox(backButton, titleBox);
        topBox.setSpacing(20);
        topBox.setAlignment(Pos.CENTER_LEFT);

        int memberCount = group.getMembers().size();

        List<Task> groupTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (Objects.equals(t.getGroup_id(), group.getGroup_id())) {
                groupTasks.add(t);
            }
        }

        int totalTasks = groupTasks.size();
        int openTasks = 0;
        int inProgressTasks = 0;
        int doneTasks = 0;

        for (Task t : groupTasks) {
            String status = t.getStatus();
            if (status == null) continue;
            String s = status.toUpperCase().replace(" ", "_");

            if ("OPEN".equals(s)) openTasks++;
            else if ("IN_PROGRESS".equals(s)) inProgressTasks++;
            else if ("DONE".equals(s)) doneTasks++;
        }

        int linkResources = 0;
        int fileResources = 0;

        for (Resource r : resources) {
            if (!Objects.equals(r.getGroup_id(), group.getGroup_id())) continue;

            String type = r.getType();
            if (type == null) continue;

            if ("link".equalsIgnoreCase(type)) {
                linkResources++;
            } else if ("file".equalsIgnoreCase(type)) {
                fileResources++;
            }
        }


        VBox membersBox = createStatBox("MEMBERS", String.valueOf(memberCount));
        VBox tasksBox = createStatBox("TASKS", String.valueOf(totalTasks));
        VBox linksBox = createStatBox("LINKS", String.valueOf(linkResources));
        VBox filesBox = createStatBox("FILES", String.valueOf(fileResources));

        HBox statsBoxes = new HBox(membersBox, tasksBox, linksBox, filesBox);
        statsBoxes.setSpacing(25);
        statsBoxes.setAlignment(Pos.CENTER);

        PieChart statusPie = new PieChart();
        statusPie.setTitle("TASKS BY STATUS");

        if (openTasks > 0) {
            statusPie.getData().add(new PieChart.Data("OPEN", openTasks));
        }
        if (inProgressTasks > 0) {
            statusPie.getData().add(new PieChart.Data("IN PROGRESS", inProgressTasks));
        }
        if (doneTasks > 0) {
            statusPie.getData().add(new PieChart.Data("DONE", doneTasks));
        }

        Pane statusPiePane = wrapBox(statusPie);

        Label noTasksLabel = new Label("ADD TASKS TO SEE STATISTICS");
        noTasksLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        Pane noTasksPane = wrapBox(noTasksLabel);

        VBox root = new VBox(topBox, statsBoxes, (totalTasks != 0) ? statusPiePane : noTasksPane);
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
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(15));
        box.setStyle("""
                    -fx-background-color: #C5D6FF;
                    -fx-background-radius: 12;
                """);
        return box;
    }
}
