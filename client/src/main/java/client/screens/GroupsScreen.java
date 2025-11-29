package client.screens;

import client.components.ElementSetup;
import client.models.Group;
import client.models.Membership;
import client.models.User;
import client.services.MembershipAPI;
import client.services.NotificationWS;
import client.services.Session;
import client.services.UserAPI;
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
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.util.ArrayList;
import java.util.List;

public class GroupsScreen {
    private static GridPane groupsGrid = new GridPane();
    private static final int maxHorGroups = 3;
    private static int index = 0;
    private static final User user = Session.getUser();

    public static Scene getScene() throws Exception {
        groupsGrid.getChildren().clear();
        index = 0;
        List<Group> userGroups = UserAPI.getUserGroups(user.getId());

        NotificationWS.init(n -> {
            Notifications.create()
                    .title(n.type.replace("_", " "))
                    .text(n.message)
                    .position(Pos.TOP_RIGHT)
                    .hideAfter(Duration.seconds(3))
                    .owner(SceneManager.getStage())
                    .showInformation();
        });

        for (Group group : userGroups) {
            addGroupBox(createGroupBox(group));
        }

        List<Long> groupIds = new ArrayList<>();

        for (Group g : userGroups) {
            Long id = g.getGroupId();
            if (!groupIds.contains(id)) {
                groupIds.add(id);
            }
        }

        NotificationWS.subscribeGroups(groupIds);

        Label emptyLabel = new Label("CREATE OR JOIN GROUP AND\n      IT WILL APPEAR HERE!");
        emptyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

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

        VBox root = new VBox(header, ((!userGroups.isEmpty()) ? groupsGrid : emptyLabel), allButtonsBox);
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

    public static VBox createGroupBox(Group group) throws Exception {
        int fontSize = 38;
        if (group.getName().length() > 7) fontSize = 20;
        else if (group.getName().length() > 4) fontSize = 25;

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
            try {
                SceneManager.toGroup(group);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });


        groupName.setAlignment(Pos.CENTER);
        Separator line = new Separator();
        line.setStyle("-fx-border-color: #aabefa;");

        VBox memberBox = new VBox();
        memberBox.setAlignment(Pos.CENTER_LEFT);

        List<Membership> memberships = MembershipAPI.getByGroup(group.getGroupId());
        int i = 0;
        for(Membership m : memberships) {
            if (i < 3) {
                User member = UserAPI.getById(m.getUserId());
                Label memberLabel = new Label(member.getName());
                memberLabel.setFont(Font.font("Arial", 18));
                memberBox.getChildren().add(memberLabel);
            } else {
                Label othersLabel = new Label("Others(" + (memberships.size() - 3) + ")");
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