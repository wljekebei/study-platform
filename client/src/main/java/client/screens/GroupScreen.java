package client.screens;

import client.components.ElementSetup;
import client.models.Group;
import client.models.Task;
import client.models.User;
import client.util.MockDB;
import client.util.SceneManager;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;
import java.util.Objects;

public class GroupScreen {
    private static List<Task> tasks = MockDB.getTasks();

    public static Scene getScene(Group group) {

        // HEADER
        Label title = new Label(group.getName());
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));

        Label id = new Label("ID: " + group.getGroup_id());
        id.setFont(Font.font("Arial", 20));
        id.setStyle("-fx-text-fill: gray;");

        HBox nameBox = new HBox(title, id);
        nameBox.setSpacing(20);
        nameBox.setAlignment(Pos.CENTER_LEFT);

        // DESCRIPTION
        Label descLabel = new Label(group.getDescription());
        if (group.getDescription().isEmpty()) descLabel.setText(group.getName() + " group");
        descLabel.setFont(Font.font("Arial", 18));

        Pane descPane = wrapBox(descLabel);

        // USERS
        VBox userList = new VBox();
        userList.setSpacing(10);

        List<User> users = group.getMembers();

        // SORT BY ROLES FROM MEMBERSHIPS TABLE
//        users.sort((a, b) -> {
//            List<String> order = List.of("owner", "admin", "member");
//            return Integer.compare(order.indexOf(a.role), order.indexOf(b.role));
//        });
        // SORT BY ROLES FROM MEMBERSHIPS TABLE

        for (User u : users) {
            Label name = new Label(u.getName());
            name.setFont(Font.font("Arial", 18));

//            if (u.role.equals("owner")) name.setStyle("-fx-text-fill: #1F75FF;");
//            else if (u.role.equals("admin")) name.setStyle("-fx-text-fill: #00AA00;");
//            else name.setStyle("-fx-text-fill: black;");

            userList.getChildren().add(name);
        }

        ScrollPane userScroll = new ScrollPane(userList);
        userScroll.setFitToWidth(true);
        userScroll.setStyle("-fx-background: #C5D6FF; -fx-border-color: #C5D6FF; -fx-padding: 10;");
        userScroll.setPrefHeight(110);

        VBox usersBlock = new VBox(
                new Label("USERS"),
                userScroll
        );
        ((Label) usersBlock.getChildren().get(0))
                .setFont(Font.font("Arial", FontWeight.BOLD, 24));
        usersBlock.setSpacing(15);
        Pane usersPane = wrapBox(usersBlock);


        // TASKS
        TableView<Task> taskTable = new TableView<>();

        TableColumn<Task, String> colTitle = new TableColumn<>("TITLE");
        colTitle.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));

        TableColumn<Task, String> colDesc = new TableColumn<>("DESCRIPTION");
        colDesc.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescription()));

        TableColumn<Task, String> colDeadline = new TableColumn<>("DEADLINE");
        colDeadline.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDeadline()));

        TableColumn<Task, String> colStatus = new TableColumn<>("STATUS");
        colStatus.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStatus()));

        List<Task> filteredTasks = tasks.stream()
                .filter(t -> Objects.equals(t.getGroup_id(), group.getGroup_id()))
                .toList();

        taskTable.getColumns().addAll(colTitle, colDesc, colDeadline, colStatus);
        taskTable.getItems().setAll(filteredTasks);

        taskTable.setRowFactory(tv -> {
            TableRow<Task> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) { // двойной клик
                    Task task = row.getItem();
                    SceneManager.toTaskConfig(task, group);
                }
            });

            return row;
        });

        colTitle.setPrefWidth(142);      // Ширина колонки TASK
        colDesc.setPrefWidth(420);
        colDeadline.setPrefWidth(80);  // Ширина DEADLINE
        colStatus.setPrefWidth(90);    // Ширина STATUS

        taskTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        taskTable.setPrefHeight(290);
        taskTable.setPrefWidth(760);

        taskTable.setStyle("""
            -fx-background-color: transparent;
            -fx-control-inner-background: #C5D6FF;
            -fx-table-cell-border-color: transparent;
            -fx-table-header-border-color: transparent;
            -fx-padding: 0;
        """);


        taskTable.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            Platform.runLater(() -> {

                Node header = taskTable.lookup(".column-header-background");
                if (header != null) {
                    header.setStyle("""
                -fx-background-color: #B6C9FF;
                -fx-border-color: transparent;
            """);
                }

                taskTable.lookupAll(".table-column > .label").forEach(label ->
                        label.setStyle("""
                    -fx-text-fill: black;
                    -fx-font-weight: bold;
                """)
                );

                taskTable.lookupAll(".column-header").forEach(col ->
                        col.setStyle("""
                    -fx-background-color: #B6C9FF;
                """)
                );
            });
        });

        Label tasksLabel = new Label("TASKS");
        tasksLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        VBox tasksBlock = new VBox(
                tasksLabel,
                taskTable
        );
        tasksBlock.setSpacing(15);
        Pane tasksPane = wrapBox(tasksBlock);

        Button resButton = new Button("RESOURCES");
        ElementSetup.buttonSetup(resButton, "12", "16");
        resButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toResources(group);
            }
        });

        Button addButton = new Button("ADD TASK");
        ElementSetup.buttonSetup(addButton, "12", "16");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toAddTask(group);
            }
        });

        Button rmButton = new Button("REMOVE TASK");
        ElementSetup.buttonSetup(rmButton, "12", "16");
        rmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toRemoveTask(group, filteredTasks);
            }
        });

        Button backButton = new Button("BACK");
        backButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        backButton.setDefaultButton(false);
        ElementSetup.buttonSetup(backButton, "10", "11");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toGroupsScreen();
            }
        });

        Button confButton = new Button("EDIT");
        confButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        confButton.setDefaultButton(false);
        ElementSetup.buttonSetup(confButton, "10", "11");
        confButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toGroupConfig(group);
            }
        });

        HBox topButtonsBox = new HBox(backButton, confButton);
        topButtonsBox.setSpacing(10);
        topButtonsBox.setAlignment(Pos.CENTER_LEFT);

        HBox nameNButtonsBox = new HBox(topButtonsBox, nameBox);
        nameNButtonsBox.setSpacing(30);
        nameNButtonsBox.setAlignment(Pos.CENTER_LEFT);

        HBox buttonBox = new HBox(addButton, rmButton, resButton);
        buttonBox.setSpacing(26);

        VBox left = new VBox(nameNButtonsBox, descPane, buttonBox);
        left.setSpacing(20);               // Было 47 — делаем ближе
        left.setPrefWidth(520);            // Чуть шире, чтобы TASKS влезали красиво

        usersPane.setPrefWidth(220);       // Делаем шире Users
        usersPane.setMinWidth(220);

        HBox topRoot = new HBox(left, usersPane);
        topRoot.setSpacing(50);            // Нормальный отступ между левым блоком и USERS
        topRoot.setPadding(new Insets(20, 20, 0, 20));
        topRoot.setAlignment(Pos.TOP_LEFT);

        HBox bottomRoot = new HBox(tasksPane);
        bottomRoot.setSpacing(40);
        bottomRoot.setAlignment(Pos.TOP_LEFT);
        bottomRoot.setPadding(new Insets(0, 20, 20, 20));

        VBox root = new VBox(topRoot, bottomRoot);
        root.setSpacing(30);
        root.setStyle("-fx-background-color: #D9E6FF;");

        Platform.runLater(() -> {
            ScrollBar bar = (ScrollBar) userScroll.lookup(".scroll-bar:vertical");
            if (bar != null) {
                bar.setStyle("-fx-background-color: transparent;");

                Node thumb = bar.lookup(".thumb");
                if (thumb != null) {
                    thumb.setStyle("-fx-background-color: #6B8EFF; -fx-background-radius: 5;");
                }
            }
        });

        Platform.runLater(() -> {
            ScrollBar bar = (ScrollBar) taskTable.lookup(".scroll-bar:vertical");
            if (bar != null) {
                bar.setStyle("-fx-background-color: transparent;");

                bar.lookupAll(".increment-arrow").forEach(a -> a.setStyle("-fx-padding: 0; -fx-shape: ''"));
                bar.lookupAll(".decrement-arrow").forEach(a -> a.setStyle("-fx-padding: 0; -fx-shape: ''"));

                Region track = (Region) bar.lookup(".track");
                if (track != null)
                    track.setStyle("-fx-background-color: #C5D6FF;");

                Region thumb = (Region) bar.lookup(".thumb");
                if (thumb != null)
                    thumb.setStyle("-fx-background-color: #6B8EFF; -fx-background-radius: 5;");
            }
        });


        return new Scene(root, 800, 600);
    }

    static Pane wrapBox(javafx.scene.Node node) {
        VBox box = new VBox(node);
        box.setPadding(new Insets(15));
        box.setStyle("""
                    -fx-background-color: #C5D6FF;
                    -fx-background-radius: 12;
                """);
        return box;
    }
}