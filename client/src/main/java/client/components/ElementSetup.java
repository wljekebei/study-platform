package client.components;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class ElementSetup {
    public static void tfSetup(TextField tf, String promptText) {
        tf.setFont(Font.font("Arial", 18));
        tf.setPromptText(promptText);
        tf.setFocusTraversable(false);
    }

    public static void buttonSetup(Button button, String bgRadius, String fontSize) {
        button.setStyle("""
            -fx-background-color: #4D7CFE;
            -fx-text-fill: white;
            -fx-font-size: %s;
            -fx-background-radius: %s;
            -fx-cursor: hand;
        """.formatted(fontSize + "pt", bgRadius));
    } // fs 11 & 18
}