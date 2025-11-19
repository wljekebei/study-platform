package client.screens;

import client.components.ElementSetup;
import client.models.Group;
import client.models.User;
import client.services.Session;
import client.util.MockDB;
import client.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class GroupsScreen {
    private static GridPane groupsGrid = new GridPane();
    private static final int maxHorGroups = 3;
    private static int index = 0;
    private static List<Group> groups = MockDB.getGroups();
    private static final User user = Session.getUser();

    public static Scene getScene() {
        groupsGrid.getChildren().clear();
        index = 0;
        List<Group> userGroups = new ArrayList<>();

        for (Group g : groups) {
            if (g.getMembers() != null && g.getMembers().contains(user)) {
                userGroups.add(g);
            }
        }

        for (Group group : userGroups) {
            addGroupBox(createGroupBox(group));
        }

        Label header = new Label("GROUPS");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 52));

        groupsGrid.setAlignment(Pos.CENTER);
        groupsGrid.setHgap(50);
        groupsGrid.setVgap(50);

        Button joinButton = new Button("JOIN");
        joinButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        joinButton.setDefaultButton(false);
        ElementSetup.buttonSetup(joinButton, "10", "16");
        joinButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toJoin();
            }
        });

        Button createButton = new Button("CREATE");
        createButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        createButton.setDefaultButton(false);
        ElementSetup.buttonSetup(createButton, "10", "16");
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toCreate();
            }
        });

        Button accButton = new Button("MY ACCOUNT");
        accButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
        accButton.setDefaultButton(false);
        ElementSetup.buttonSetup(accButton, "10", "16");
        accButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toAccount(user);
            }
        });

        HBox buttonBox = new HBox(joinButton, createButton);
        buttonBox.setSpacing(20);

        HBox allButtonsBox = new HBox(accButton, buttonBox);
        allButtonsBox.setSpacing(200);

        VBox root = new VBox(header, groupsGrid, allButtonsBox);
        root.setSpacing(40);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(10, 20, 10, 20));
        root.setStyle("-fx-background-color: #D9E6FF;");

        int height = 370 + (((index - 1) / maxHorGroups) * 200);

        return new Scene(root, 600, height); // 500 for 2, 300 for 1
    }

    public static void addGroupBox(VBox groupBox) { // adds finished vbox to the grid

        int column = index % maxHorGroups;
        int row = index / maxHorGroups;

        index++;

        groupsGrid.add(groupBox, column, row);
    }

    public static VBox createGroupBox(Group group) {
        int fontSize = 38; // set limit at 10 symbols for group name !!
        if (group.getName().length() > 8) fontSize = 20;
        else if (group.getName().length() > 5) fontSize = 25;

        Label groupName = new Label(group.getName());
        groupName.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
        groupName.setPrefWidth(200);
        groupName.setMinHeight(40);
        groupName.setPrefHeight(40);

        groupName.setStyle("""
            -fx-text-fill: #3161e8;
            -fx-cursor: hand;
        """);

        groupName.setOnMouseClicked(e -> {
            SceneManager.toGroup(group);
        });


        groupName.setAlignment(Pos.CENTER);
        Separator line = new Separator();
        line.setStyle("-fx-border-color: #aabefa;");

        VBox memberBox = new VBox();
        memberBox.setAlignment(Pos.CENTER_LEFT);

        int i = 0;
        for(User member : group.getMembers()) {
            if (i < 3) {
                Label memberLabel = new Label(member.getName());
                memberLabel.setFont(Font.font("Arial", 18));
                memberBox.getChildren().add(memberLabel);
            } else {
                Label othersLabel = new Label("Others(" + (group.getMembers().size() - 3) + ")");
                othersLabel.setFont(Font.font("Arial", 18));
                memberBox.getChildren().add(othersLabel);
                break;
            }
            i++;
        }

        VBox groupBox = new VBox(groupName, line, memberBox);
        groupBox.setSpacing(5);
        groupBox.setAlignment(Pos.TOP_CENTER);
        groupBox.setPadding(new Insets(5, 10, 10, 10));
        groupBox.setStyle("""
        -fx-background-radius: 13;
        -fx-background-color: #aabefa;
        """);

        return groupBox; // if something changes -> create groupBox again
    }
}