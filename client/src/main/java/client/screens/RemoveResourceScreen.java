package client.screens;

import client.components.ElementSetup;
import client.models.Group;
import client.models.Resource;
import client.services.ResourceAPI;
import client.util.SceneManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class RemoveResourceScreen {

    public static Scene getScene(Group group) {
        Label header = new Label("REMOVE RESOURCE");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        List<Resource> resources;
        try {
            resources = ResourceAPI.getByGroup(group.getGroupId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ComboBox<String> resChoice = new ComboBox<>();
        for (Resource res : resources) {
            resChoice.getItems().add(res.getTitle());
        }
        resChoice.setPrefWidth(300);
        resChoice.setStyle("""
                -fx-background-color: #4D7CFE;
                -fx-text-fill: white;
                -fx-font-size: 18;
                -fx-background-radius: 8;
                """);

        Button confButton = new Button("REMOVE");
        confButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        confButton.setDefaultButton(true);
        ElementSetup.buttonSetup(confButton, "10", "16");
        confButton.setOnAction(a -> {
            String selected = resChoice.getValue();
            if (selected != null) {
                try {
                    for (Resource res : resources) {
                        if (res.getTitle().equals(selected)) {
                            ResourceAPI.delete(res.getId());
                            break;
                        }
                    }
                    SceneManager.toResources(group);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                SceneManager.toResources(group);
            }
        });

        Button backButton = new Button("BACK");
        backButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        backButton.setDefaultButton(false);
        ElementSetup.buttonSetup(backButton, "10", "16");
        backButton.setOnAction(a -> SceneManager.toResources(group));

        HBox buttonsBox = new HBox(backButton, confButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(50);

        VBox root = new VBox(header, resChoice, buttonsBox);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);
        root.setPadding(new Insets(30, 30, 30, 30));
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 400, 400);
    }
}
