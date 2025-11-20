package client.screens;

import client.components.ElementSetup;
import client.services.GroupAPI;
import client.services.MembershipAPI;
import client.services.Session;
import client.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class JoinScreen {
    public static Scene getScene() {
        Label header = new Label("JOIN GROUP");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        TextField idField = new TextField();
        ElementSetup.tfSetup(idField, "Group ID");

        Label errorLabel = new Label();
        errorLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 18));
        errorLabel.setStyle("-fx-text-fill: red;");

        Button joinButton = new Button("JOIN");
        joinButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        joinButton.setDefaultButton(true);
        ElementSetup.buttonSetup(joinButton, "10", "16");
        joinButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    MembershipAPI.join(Session.getUser().getId(), Long.parseLong(idField.getText()), "user");
                    SceneManager.toGroup(GroupAPI.getById(Long.parseLong(idField.getText())));
                } catch (Exception e) {
                    errorLabel.setText("Error");
                    throw new RuntimeException(e);
                }
            }
        });

        Button backButton = new Button("BACK");
        backButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        backButton.setDefaultButton(false);
        ElementSetup.buttonSetup(backButton, "10", "16");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toGroupsScreen();
            }
        });

        HBox buttonsBox = new HBox(backButton, joinButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(50);

        VBox idNErrorBox = new VBox(idField, errorLabel);
        idNErrorBox.setSpacing(10);
        idNErrorBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(header, idNErrorBox, buttonsBox);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);
        root.setPadding(new Insets(30, 30, 30, 30));
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 300, 300);
    }
}