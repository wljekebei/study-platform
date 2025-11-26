package client.screens;

import client.components.ElementSetup;
import client.dto.GroupStats;
import client.models.Group;
import client.services.GroupStatsAPI;
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

public class GroupStatsScreen {

    public static Scene getScene(Group group) throws Exception {
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
        backButton.setOnAction(e -> {
            try {
                SceneManager.toGroup(group);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        HBox topBox = new HBox(backButton, titleBox);
        topBox.setSpacing(20);
        topBox.setAlignment(Pos.CENTER_LEFT);

        GroupStats stats = GroupStatsAPI.get(group.getGroupId());

        long memberCount     = stats.members     != null ? stats.members     : 0L;
        long totalTasks      = stats.tasks       != null ? stats.tasks       : 0L;
        long linkResources   = stats.links       != null ? stats.links       : 0L;
        long fileResources   = stats.files       != null ? stats.files       : 0L;
        long doneTasks       = stats.done        != null ? stats.done        : 0L;
        long inProgressTasks = stats.inProgress  != null ? stats.inProgress  : 0L;
        long openTasks       = stats.open        != null ? stats.open        : 0L;

        VBox membersBox = createStatBox("MEMBERS", String.valueOf(memberCount));
        VBox tasksBox   = createStatBox("TASKS",   String.valueOf(totalTasks));
        VBox linksBox   = createStatBox("LINKS",   String.valueOf(linkResources));
        VBox filesBox   = createStatBox("FILES",   String.valueOf(fileResources));

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
