package client.screens;

import client.util.SceneManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class GroupsScreen {
    private static GridPane groupsGrid = new GridPane();
    private static int maxHorGroups = 3;
    private static int index = 0;

    public static Scene getScene() {
        Label header = new Label("GROUPS");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 52));
        header.setStyle("""
            -fx-text-fill: #4D7CFE;
            -fx-cursor: hand;
        """);

        header.setOnMouseClicked(e -> {
            // SceneManager.toGroup();
        });

        groupsGrid.setHgap(20);
        groupsGrid.setVgap(20);

        VBox acssBox = createGroupBox("ACSS", List.of("Kolay", "Vasyl", "wljekebei", "Timur", "Masha", "kokot228"));
        VBox tsiktBox = createGroupBox("TSIKT", List.of("HEIKO228", "Andrianka"));

        addGroupBox(acssBox);
        addGroupBox(tsiktBox);

        VBox root = new VBox(header, groupsGrid);
        root.setSpacing(40);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 600, 400);
    }

    public static void addGroupBox(VBox groupBox) { // adds finished vbox to the grid

        int column = index % maxHorGroups;
        int row = index / maxHorGroups;

        index++;

        groupsGrid.add(groupBox, column, row);
    }

    public static VBox createGroupBox(String name, List<String> members) {
        Label groupName = new Label(name);
        groupName.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 30));

        VBox memberBox = new VBox();
        memberBox.setAlignment(Pos.CENTER_LEFT);

        int i = 0;
        for(String member : members) {
            if (i < 3) {
                Label memberLabel = new Label(member);
                memberLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
                memberBox.getChildren().add(memberLabel);
            } else {
                memberBox.getChildren().add(new Label("Others(" + (members.size() - 3) + ")"));
                break;
            }
            i++;
        }

        VBox groupBox = new VBox(groupName, memberBox);
        groupBox.setAlignment(Pos.CENTER);
        // setstyle

        return groupBox; // if something changes -> create groupBox again
    }
}