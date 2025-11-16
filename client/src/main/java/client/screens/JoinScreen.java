package client.screens;

import client.components.ElementSetup;
import client.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class JoinScreen {
    public static Scene getScene() {
        Label header = new Label("JOIN GROUP");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        TextField idField = new TextField();
        ElementSetup.tfSetup(idField, "Group ID");

        Button joinButton = new Button("JOIN");
        joinButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        joinButton.setDefaultButton(false);
        ElementSetup.buttonSetup(joinButton, "10", "16");
        joinButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // joining logic
                // SceneManager.toGroup

                // REMOVE
                SceneManager.toGroupsScreen();
                // REMOVE ^
            }
        });

        VBox root = new VBox(header, idField, joinButton);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);
        root.setPadding(new Insets(30, 30, 30, 30));
        return new Scene(root, 300, 300);
    }
}