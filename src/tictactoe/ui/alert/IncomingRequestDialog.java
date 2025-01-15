package tictactoe.ui.alert;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.scene.Scene;
import javax.json.Json;
import javax.json.JsonObject;
import tictactoe.data.repository.Repo;
import tictactoe.ui.screens.Board;

public class IncomingRequestDialog {

    private static Stage stage;
    private static Repo repo;

    public Optional<Boolean> showRequestDialog(Stage stage, JsonObject jsonObj) {
        this.stage = stage;
        repo = new Repo();

        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Game Challenge");
        dialog.setHeaderText(null);

        dialog.getDialogPane().getStyleClass().add("dialog-pane");

        Label titleLabel = new Label("You have received a game challenge!");
        titleLabel.getStyleClass().add("dialog-title");

        ImageView avatarImage = new ImageView(this.getClass().getResource("/resources/images/avatar.png").toString());
        avatarImage.setFitWidth(60);
        avatarImage.setFitHeight(60);
        avatarImage.getStyleClass().add("avatar-image");

        Label opponentLabel = new Label("userName: " + jsonObj.getString("username-player1"));
        opponentLabel.getStyleClass().add("opponent-label");

        Label scoreLabel = new Label("score: " + jsonObj.getInt("score-player1"));
        scoreLabel.getStyleClass().add("score-label");

        VBox labelsContainer = new VBox(3, opponentLabel, scoreLabel);
        labelsContainer.setAlignment(Pos.CENTER_LEFT);

        HBox headerContainer = new HBox(10, avatarImage, labelsContainer);
        headerContainer.setAlignment(Pos.CENTER_LEFT);

        Label messageLabel = new Label(
                "You have been invited to join an exciting game.\nWill you accept the challenge or decline it?");
        messageLabel.getStyleClass().add("message-label");
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(400);

        ButtonType acceptButtonType = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
        ButtonType declineButtonType = new ButtonType("Decline", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(acceptButtonType, declineButtonType);

        Button acceptButton = (Button) dialog.getDialogPane().lookupButton(acceptButtonType);
        acceptButton.getStyleClass().add("accept-button");

        acceptButton.setOnAction((e) -> {
            String json = Json.createObjectBuilder()
                    .add("action", 4)
                    .add("status", 2) // accepted status
                    .add("username-player1", jsonObj.getString("username-player1"))
                    .add("username-player2", jsonObj.getString("username-player2"))
                    .add("score-player1", jsonObj.getInt("score-player1"))
                    .add("score-player2", jsonObj.getInt("score-player2"))
                    .build().toString();
            repo.sendInvitation(json);
        });

        Button declineButton = (Button) dialog.getDialogPane().lookupButton(declineButtonType);
        declineButton.getStyleClass().add("decline-button");
        declineButton.setOnAction((e) -> {
            // TODO
            String json = Json.createObjectBuilder()
                    .add("action", 4)
                    .add("status", 3) // declined status
                    .add("username-player1", jsonObj.getString("username-player1"))
                    .add("username-player2", jsonObj.getString("username-player2"))
                    .build().toString();
            repo.sendInvitation(json);
        });

        HBox buttonContainer = new HBox(10, acceptButton, declineButton);
        buttonContainer.setAlignment(Pos.CENTER);

        VBox contentPane = new VBox(15, titleLabel, headerContainer, messageLabel, buttonContainer);
        contentPane.getStyleClass().add("content-pane");

        dialog.getDialogPane().setContent(contentPane);

        // Handle the result
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == acceptButtonType) {
                stage.setScene(new Scene(new Board(new Stage())));
                return true;  // Accept button clicked
            } else {
                return false; // Decline button clicked
            }
        });

        //dialog.getDialogPane().getStylesheets().add(this.getClass().getResource("/CSS/homeStyle.css").toExternalForm());
        return dialog.showAndWait();
    }
}
