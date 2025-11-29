package client.screens;

import client.components.ElementSetup;
import client.models.Group;
import client.models.Resource;
import client.services.ResourceAPI;
import client.services.Session;
import client.util.SceneManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URI;
import java.util.List;

public class ResourcesScreen {

    public static Scene getScene(Group group) {

        Label header = new Label("RECOURCES");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        Button backButton = new Button("BACK");
        backButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        ElementSetup.buttonSetup(backButton, "10", "16");
        backButton.setOnAction(a -> {
            try {
                SceneManager.toGroup(group);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Button addButton = new Button("ADD");
        addButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        ElementSetup.buttonSetup(addButton, "10", "16");
        addButton.setOnAction(a -> SceneManager.toAddResource(group));

        Button rmButton = new Button("REMOVE");
        rmButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        ElementSetup.buttonSetup(rmButton, "10", "16");
        rmButton.setOnAction(a -> SceneManager.toRmResource(group));

        HBox buttonsBox = new HBox(backButton, addButton);
        try {
            if (GroupScreen.getUserRole(Session.getUser().getId(), group).equalsIgnoreCase("admin")) {
                buttonsBox.getChildren().add(rmButton);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(40);

        List<Resource> resList;
        try {
            resList = ResourceAPI.getByGroup(group.getGroupId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        VBox linksBox = new VBox();
        VBox filesBox = new VBox();
        linksBox.setSpacing(15);
        filesBox.setSpacing(15);

        int fileNum = 0;
        int linkNum = 0;

        for (Resource r : resList) {

            Label title = new Label(r.getTitle());
            title.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));

            String displayedText;

            if (r.getType().equalsIgnoreCase("file")) {
                fileNum++;
                File f = new File(r.getPathOrUrl());
                displayedText = f.getName().substring(14);
            } else {
                linkNum++;
                displayedText = r.getPathOrUrl();
            }

            Label path = new Label(displayedText);
            path.setFont(Font.font("Arial", 15));
            path.setWrapText(true);

            if (r.getType().equalsIgnoreCase("link")) {
                path.setStyle("""
            -fx-text-fill: #2A52BE;
            -fx-underline: true;
            -fx-cursor: hand;
        """);

                path.setOnMouseClicked(e -> {
                    try {
                        java.awt.Desktop.getDesktop().browse(new URI(r.getPathOrUrl()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                VBox item = new VBox(title, path);
                item.setSpacing(4);
                item.setPadding(new Insets(4));
                linksBox.getChildren().add(item);

            } else {
                Button downloadButton = new Button("DOWNLOAD");
                downloadButton.setFont(Font.font("Arial", FontWeight.NORMAL, 8));
                ElementSetup.buttonSetup(downloadButton, "6", "8");

                downloadButton.setOnAction(e -> {
                    try {
                        File source = new File(r.getPathOrUrl());
                        if (!source.exists()) {
                            System.err.println("File not found: " + source.getAbsolutePath());
                            return;
                        }

                        FileChooser chooser = new FileChooser();
                        chooser.setTitle("Save file");
                        chooser.setInitialFileName(source.getName());
                        File dest = chooser.showSaveDialog(downloadButton.getScene().getWindow());

                        if (dest != null) {
                            java.nio.file.Files.copy(
                                    source.toPath(),
                                    dest.toPath(),
                                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
                            );
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                VBox item = new VBox(title, path, downloadButton);
                item.setSpacing(4);
                item.setPadding(new Insets(4));
                filesBox.getChildren().add(item);
            }
        }

        Label infoLabel = new Label();
        infoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        if (fileNum == 0) {
            filesBox.getChildren().add(new Label("Files will appear here"));
        }
        if (linkNum == 0) {
            linksBox.getChildren().add(new Label("Links will appear here"));
        }

        ScrollPane linksScroll = new ScrollPane(linksBox);
        ScrollPane filesScroll = new ScrollPane(filesBox);

        linksScroll.setFitToWidth(true);
        filesScroll.setFitToWidth(true);

        linksScroll.setPrefHeight(350);
        filesScroll.setPrefHeight(350);

        linksScroll.setMaxWidth(300);
        filesScroll.setMaxWidth(300);

        linksScroll.setStyle("""
        -fx-background: #C5D6FF;
        -fx-border-color: #C5D6FF;
        -fx-padding: 10;
    """);

        filesScroll.setStyle("""
        -fx-background: #C5D6FF;
        -fx-border-color: #C5D6FF;
        -fx-padding: 10;
    """);

        Label linksLabel = new Label("LINKS");
        linksLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 22));

        VBox linksBlock = new VBox(linksLabel, linksScroll);
        linksBlock.setSpacing(12);
        linksBlock.setPadding(new Insets(10));

        Pane linksPane = GroupScreen.wrapBox(linksBlock);

        Label filesLabel = new Label("FILES");
        filesLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 22));

        VBox filesBlock = new VBox(filesLabel, filesScroll);
        filesBlock.setSpacing(12);
        filesBlock.setPadding(new Insets(10));

        Pane filesPane = GroupScreen.wrapBox(filesBlock);

        HBox columns = new HBox(linksPane, filesPane);
        columns.setSpacing(20);
        columns.setAlignment(Pos.CENTER);

        VBox root = new VBox(header, buttonsBox, columns);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(35);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #D9E6FF;");

        Scene scene = new Scene(root, 720, 600);

        return scene;
    }
}
