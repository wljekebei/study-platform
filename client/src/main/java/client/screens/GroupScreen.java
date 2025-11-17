package client.screens;

import client.components.ElementSetup;
import client.util.SceneManager;
import javafx.application.Platform;
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

import java.util.ArrayList;
import java.util.List;

public class GroupScreen {

    public static Scene getScene(
            String groupName,
            int groupId,
            String description,
            List<UserEntry> users,
            List<TaskEntry> tasks
    ) {

        // ---------- HEADER (Название + ID) ----------
        Label title = new Label(groupName);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));

        Label id = new Label("ID: " + groupId);
        id.setFont(Font.font("Arial", 20));
        id.setStyle("-fx-text-fill: gray;");

        HBox nameBox = new HBox(title, id);
        nameBox.setSpacing(20);
        nameBox.setAlignment(Pos.CENTER_LEFT);

        // ---------- DESCRIPTION ----------
        Label descLabel = new Label(description);
        descLabel.setFont(Font.font("Arial", 18));

        Pane descPane = wrapBox(descLabel);

        // ---------- USERS LIST (scrollable) ----------
        VBox userList = new VBox();
        userList.setSpacing(10);

        users = new ArrayList<>(users);
        users.sort((a, b) -> {
            List<String> order = List.of("owner", "admin", "member");
            return Integer.compare(order.indexOf(a.role), order.indexOf(b.role));
        });

        for (UserEntry u : users) {
            Label name = new Label(u.username);
            name.setFont(Font.font("Arial", 18));

            if (u.role.equals("owner")) name.setStyle("-fx-text-fill: #1F75FF;");
            else if (u.role.equals("admin")) name.setStyle("-fx-text-fill: #00AA00;");
            else name.setStyle("-fx-text-fill: black;");

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


        // ---------- TASK TABLE ----------
        TableView<TaskEntry> taskTable = new TableView<>();

        TableColumn<TaskEntry, String> colTitle = new TableColumn<>("TITLE");
        colTitle.setCellValueFactory(c -> c.getValue().taskTitleProperty());

        TableColumn<TaskEntry, String> colDesc = new TableColumn<>("DESCRIPTION");
        colDesc.setCellValueFactory(c -> c.getValue().taskDescProperty());

        TableColumn<TaskEntry, String> colDeadline = new TableColumn<>("DEADLINE");
        colDeadline.setCellValueFactory(c -> c.getValue().deadlineProperty());

        TableColumn<TaskEntry, String> colStatus = new TableColumn<>("STATUS");
        colStatus.setCellValueFactory(c -> c.getValue().statusProperty());

        taskTable.getColumns().addAll(colTitle, colDesc, colDeadline, colStatus);
        taskTable.getItems().addAll(tasks);

        taskTable.setRowFactory(tv -> {
            TableRow<TaskEntry> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) { // двойной клик
                    TaskEntry task = row.getItem();
                    openTaskConfig(task);
                }
            });

            return row;
        });

        colTitle.setPrefWidth(120);      // Ширина колонки TASK
        colDesc.setPrefWidth(442);
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

                // Фон всей шапки таблицы
                Node header = taskTable.lookup(".column-header-background");
                if (header != null) {
                    header.setStyle("""
                -fx-background-color: #B6C9FF;
                -fx-border-color: transparent;
            """);
                }

                // Цвет текста всех названий колонок
                taskTable.lookupAll(".table-column > .label").forEach(label ->
                        label.setStyle("""
                    -fx-text-fill: black;
                    -fx-font-weight: bold;
                """)
                );

                // Каждая отдельная колонка (контейнер)
                taskTable.lookupAll(".column-header").forEach(col ->
                        col.setStyle("""
                    -fx-background-color: #B6C9FF;
                    -fx-border-color: transparent;
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

        Button addButton = new Button("ADD TASK");
        ElementSetup.buttonSetup(addButton, "12", "16");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toAddTask();
            }
        });

        Button rmButton = new Button("REMOVE TASK");
        ElementSetup.buttonSetup(rmButton, "12", "16");
        rmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toRemoveTask();
            }
        });

        HBox buttonBox = new HBox(addButton, rmButton, resButton);
        buttonBox.setSpacing(26);

        // ---------- LEFT SIDE ----------
        VBox left = new VBox(nameBox, descPane, buttonBox);
        left.setSpacing(20);               // Было 47 — делаем ближе
        left.setPrefWidth(520);            // Чуть шире, чтобы TASKS влезали красиво

        // ---------- USERS SIDE ----------
        usersPane.setPrefWidth(220);       // Делаем шире Users
        usersPane.setMinWidth(220);

        // ---------- TOP PART ----------
        HBox topRoot = new HBox(left, usersPane);
        topRoot.setSpacing(50);            // Нормальный отступ между левым блоком и USERS
        topRoot.setPadding(new Insets(20, 20, 0, 20));
        topRoot.setAlignment(Pos.TOP_LEFT);

        HBox bottomRoot = new HBox(tasksPane);
        bottomRoot.setSpacing(40);
        bottomRoot.setAlignment(Pos.TOP_LEFT);
        bottomRoot.setPadding(new Insets(0, 20, 20, 20));

        // ---------- ROOT ----------
        VBox root = new VBox(topRoot, bottomRoot);
        root.setSpacing(30);
        root.setStyle("-fx-background-color: #D9E6FF;");

        Platform.runLater(() -> {
            ScrollBar bar = (ScrollBar) userScroll.lookup(".scroll-bar:vertical");
            if (bar != null) {
                // Прячем фон полосы
                bar.setStyle("-fx-background-color: transparent;");

                // Меняем сам ползунок
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

    // helper to wrap block in rounded background pane
    private static Pane wrapBox(javafx.scene.Node node) {
        VBox box = new VBox(node);
        box.setPadding(new Insets(15));
        box.setStyle("""
            -fx-background-color: #C5D6FF;
            -fx-background-radius: 12;
        """);
        return box;
    }

    // ---------- DATA CLASSES ----------
    public static class UserEntry {
        public String username;
        public String role; // owner / admin / member
        public UserEntry(String u, String r) { username = u; role = r; }
    }

    public static class TaskEntry {
        private javafx.beans.property.SimpleStringProperty taskTitle;
        private javafx.beans.property.SimpleStringProperty taskDesc;
        private javafx.beans.property.SimpleStringProperty deadline;
        private javafx.beans.property.SimpleStringProperty status;

        public TaskEntry(String t, String desc, String d, String s) {
            taskTitle = new javafx.beans.property.SimpleStringProperty(t);
            taskDesc = new javafx.beans.property.SimpleStringProperty(desc);
            deadline = new javafx.beans.property.SimpleStringProperty(d);
            this.status = new javafx.beans.property.SimpleStringProperty(s);
        }

        public javafx.beans.property.StringProperty taskTitleProperty() { return taskTitle; }
        public javafx.beans.property.StringProperty taskDescProperty() { return taskDesc; }
        public javafx.beans.property.StringProperty deadlineProperty() { return deadline; }
        public javafx.beans.property.StringProperty statusProperty() { return status; }
    }

    private static void openTaskConfig(TaskEntry task) {
        SceneManager.toTaskConfig();
        // Тут вызываешь свою сцену
//        SceneManager.toTaskConfigScreen(
//                task.taskNameProperty().get(),
//                task.deadlineProperty().get(),
//                task.statusProperty().get()
//        );
    }
}