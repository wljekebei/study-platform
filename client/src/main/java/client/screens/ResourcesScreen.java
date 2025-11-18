package client.screens;

import client.components.ElementSetup;
import client.models.Group;
import client.models.Resource;
import client.util.MockDB;
import client.util.SceneManager;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class ResourcesScreen {

    static List<Resource> resList = MockDB.getResources();

    public static Scene getScene(Group group) {

        Label header = new Label("RECOURCES");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        Button backButton = new Button("BACK");
        backButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        backButton.setDefaultButton(false);
        ElementSetup.buttonSetup(backButton, "10", "16");

        Button addButton = new Button("ADD");
        addButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        addButton.setDefaultButton(false);
        ElementSetup.buttonSetup(addButton, "10", "16");

        Button rmButton = new Button("REMOVE");
        rmButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        rmButton.setDefaultButton(false);
        ElementSetup.buttonSetup(rmButton, "10", "16");

        backButton.setOnAction(a -> SceneManager.toGroup(group));

        HBox buttonsBox = new HBox(backButton, addButton, rmButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(40);

        VBox linksBox = new VBox();
        VBox filesBox = new VBox();
        linksBox.setSpacing(15);
        filesBox.setSpacing(15);

        for (Resource r : resList) {

            Label title = new Label(r.getTitle());
            title.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));

            Label path = new Label(r.getPath_or_url());
            path.setFont(Font.font("Arial", 15));
            path.setWrapText(true);

            if (r.getType().equals("link")) {
                path.setStyle("""
                    -fx-text-fill: #2A52BE;
                    -fx-underline: true;
                    -fx-cursor: hand;
                """);

                path.setOnMouseClicked(e -> {
                    try {
                        java.awt.Desktop.getDesktop().browse(new java.net.URI(r.getPath_or_url()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }


            VBox item = new VBox(title, path);
            item.setSpacing(4);
            item.setPadding(new Insets(4, 4, 4, 4));

            if (r.getType().equals("link")) {
                linksBox.getChildren().add(item);
            } else {
                filesBox.getChildren().add(item);
            }
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
        root.setPadding(new Insets(25, 25, 25, 25));
        root.setStyle("-fx-background-color: #D9E6FF;");

        Scene scene = new Scene(root, 720, 600);
        Platform.runLater(() -> {
            customizeScrollbar(linksScroll);
            customizeScrollbar(filesScroll);
        });

        return scene;
    }


    private static void customizeScrollbar(ScrollPane sp) {

        ScrollBar bar = (ScrollBar) sp.lookup(".scroll-bar:vertical");

        if (bar != null) {
            bar.setStyle("-fx-background-color: transparent;");

            Node thumb = bar.lookup(".thumb");
            if (thumb != null) {
                thumb.setStyle("""
                        -fx-background-color: #6B8EFF;
                        -fx-background-radius: 6;
                        -fx-padding: 3;
                        """);
            }
        }
    }
}
