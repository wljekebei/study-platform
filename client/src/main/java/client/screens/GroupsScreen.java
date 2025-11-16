package client.screens;

import client.components.ElementSetup;
import client.models.Group;
import client.models.User;
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
    private static int maxHorGroups = 3;
    private static int index = 0;
    private static List<Group> groups = MockDB.getGroups();

    public static Scene getScene() {
        groupsGrid.getChildren().clear();
        index = 0;
        for (Group group : groups) {
            addGroupBox(createGroupBox(group.getName(), group.getMembers()));
        }

        Label header = new Label("GROUPS");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 52));

        groupsGrid.setAlignment(Pos.CENTER);
//        groupsGrid.setPadding(new Insets(0, 40, 0, 40));
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
                // ScreenManager.toCreate
            }
        });

        // REMOVE

//        VBox acssBox = createGroupBox("ACSS", List.of("Kolay", "Vasyl", "wljekebei", "Timur", "Masha", "kokot228"));
//        VBox tsiktBox = createGroupBox("TSIKT", List.of("HEIKO228", "Andrianka"));
//        VBox mat3Box = createGroupBox("MAT33333", List.of("Vasyl", "Misha Titov", "Liza"));
//        VBox osdBox = createGroupBox("OSD", List.of("Taras", "Slava", "HEIKO228", "Andrew"));
//        VBox tsiktBox1 = createGroupBox("TSIKT", List.of("HEIKO228", "Andrianka"));
//        VBox mat3Box1 = createGroupBox("MAT33333", List.of("Vasyl", "Misha Titov", "Liza"));
//        VBox osdBox1 = createGroupBox("OSD", List.of("Taras", "Slava", "HEIKO228", "Andrew"));
//
//        addGroupBox(acssBox);
//        addGroupBox(tsiktBox);
//        addGroupBox(mat3Box);
//        addGroupBox(osdBox);
//        addGroupBox(tsiktBox1);
//        addGroupBox(mat3Box1);
//        addGroupBox(osdBox1);

        // REMOVE ^

        HBox buttonBox = new HBox(joinButton, createButton);
        buttonBox.setSpacing(386);

        VBox root = new VBox(header, groupsGrid, buttonBox);
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

    public static VBox createGroupBox(String name, List<User> members) {
        int fontSize = 38; // set limit at 10 symbols for group name !!
        if (name.length() > 8) fontSize = 20;
        else if (name.length() > 5) fontSize = 25;

        Label groupName = new Label(name);
        groupName.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
        groupName.setPrefWidth(200);
        groupName.setMinHeight(40);
        groupName.setPrefHeight(40);

        groupName.setStyle("""
            -fx-text-fill: #3161e8;
            -fx-cursor: hand;
        """);

        groupName.setOnMouseClicked(e -> {
            // SceneManager.toGroup();
        });


        groupName.setAlignment(Pos.CENTER);
        Separator line = new Separator();
        line.setStyle("-fx-border-color: #aabefa;");

        VBox memberBox = new VBox();
        memberBox.setAlignment(Pos.CENTER_LEFT);

        int i = 0;
        for(User member : members) {
            if (i < 3) {
                Label memberLabel = new Label(member.getName());
                memberLabel.setFont(Font.font("Arial", 18));
                memberBox.getChildren().add(memberLabel);
            } else {
                Label othersLabel = new Label("Others(" + (members.size() - 3) + ")");
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