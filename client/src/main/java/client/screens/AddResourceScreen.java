package client.screens;

import client.components.ElementSetup;
import client.models.Group;
import client.models.Resource;
import client.services.Session;
import client.util.MockDB;
import client.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import java.io.File;

public class AddResourceScreen {
    public static Scene getScene(Group group) {
        Label header = new Label("NEW RESOURCE");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 38));

        Label typeLabel = new Label("Type:");
        typeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));

        ComboBox<String> typeChoice = new ComboBox<>();
        typeChoice.getItems().add("Link");
        typeChoice.getItems().add("File");
        typeChoice.setPrefWidth(150);
        typeChoice.setStyle("""
                -fx-background-color: #4D7CFE;
                -fx-text-fill: white;
                -fx-font-size: 18;
                -fx-background-radius: 8;
                """);
        typeChoice.setValue("Link");

        TextField titleField = new TextField();
        ElementSetup.tfSetup(titleField, "Title");

        TextField linkField = new TextField();
        ElementSetup.tfSetup(linkField, "Link");

        Label fileLabel = new Label("No file selected");
        fileLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        Button chooseFileButton = new Button("CHOOSE FILE");
        chooseFileButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        ElementSetup.buttonSetup(chooseFileButton, "8", "16");

        chooseFileButton.setVisible(false);
        fileLabel.setVisible(false);

        File[] selectedFile = new File[1];

        chooseFileButton.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Select file");
            File file = chooser.showOpenDialog(titleField.getScene().getWindow());

            if (file != null) {
                selectedFile[0] = file;
                fileLabel.setText(file.getName());
            }
        });

        typeChoice.setOnAction(e -> {
            String value = typeChoice.getValue();
            if (value != null && value.equalsIgnoreCase("file")) {
                linkField.setVisible(false);
                chooseFileButton.setVisible(true);
                fileLabel.setVisible(true);
            } else {
                linkField.setVisible(true);
                chooseFileButton.setVisible(false);
                fileLabel.setVisible(false);
            }
        });

        Button confButton = new Button("CONFIRM");
        confButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        confButton.setDefaultButton(true);
        ElementSetup.buttonSetup(confButton, "10", "16");
        confButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String type = typeChoice.getValue();
                if (type == null) type = "Link";

                String storedType = type.toLowerCase();
                String pathOrUrl = "";

                if (storedType.equals("link")) {
                    pathOrUrl = linkField.getText();
                } else if (storedType.equals("file")) {
                    if (selectedFile[0] != null) {
                        // for now like that
                        pathOrUrl = selectedFile[0].getName();
                    } else {
                        pathOrUrl = "";
                    }
                }

                MockDB.getResources().add(
                        new Resource(
                                52L,
                                group.getGroup_id(),
                                Session.getUser().getUser_id(),
                                titleField.getText(),
                                storedType,
                                pathOrUrl,
                                "14:47"
                        )
                );
                SceneManager.toResources(group);
            }
        });

        Button backButton = new Button("BACK");
        backButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        backButton.setDefaultButton(false);
        ElementSetup.buttonSetup(backButton, "10", "16");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.toResources(group);
            }
        });

        HBox buttonsBox = new HBox(backButton, confButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(50);

        HBox typeBox = new HBox(typeLabel, typeChoice);
        typeBox.setAlignment(Pos.CENTER);
        typeBox.setSpacing(10);

        VBox fieldBox = new VBox(typeBox, titleField, linkField, chooseFileButton, fileLabel);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setSpacing(10);

        VBox root = new VBox(header, fieldBox, buttonsBox);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(25);
        root.setPadding(new Insets(30, 30, 30, 30));
        root.setStyle("-fx-background-color: #D9E6FF;");

        return new Scene(root, 400, 450);
    }
}