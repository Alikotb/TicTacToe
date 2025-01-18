package tictactoe.ui.alert;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class MessagePopup extends Label {

    public MessagePopup(String username, String message) {

        setAlignment(javafx.geometry.Pos.CENTER);
        setText(username + message);
        setPadding(new Insets(25.0, 50.0, 25.0, 50.0));
        setFont(new Font(24.0));

    }
}
