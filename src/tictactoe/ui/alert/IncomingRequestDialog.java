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
    private final int ACCEPTED = 2;
    private final int DECLINED = 3;

    public Optional<Boolean> showRequestDialog(Stage stage, JsonObject jsonObj) {
        this.stage = stage;
        repo = new Repo();

        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Game Challenge");
        dialog.setHeaderText(null);

        dialog.getDialogPane().setStyle("-fx-background-color: #1F509A; -fx-background-radius: 10;");

        Label titleLabel = new Label("You have received a game challenge!");
        titleLabel.setStyle("-fx-font-family: 'Black Han Sans'; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        ImageView avatarImage = new ImageView(this.getClass().getResource("/resources/images/avatar.png").toString());
        avatarImage.setFitWidth(60);
        avatarImage.setFitHeight(60);
        avatarImage.setStyle("-fx-background-radius: 50; -fx-border-radius: 50;");

        Label opponentLabel = new Label("Username : " + jsonObj.getString("username-player1"));
        opponentLabel.setStyle("-fx-font-family: 'Black Han Sans'; -fx-text-fill: white; -fx-font-size: 14px;");

        Label scoreLabel = new Label("Score : " + jsonObj.getInt("score-player1"));
        scoreLabel.setStyle("-fx-font-family: 'Black Han Sans'; -fx-text-fill: white; -fx-font-size: 14px;");

        VBox labelsContainer = new VBox(3, opponentLabel, scoreLabel);
        labelsContainer.setAlignment(Pos.CENTER_LEFT);

        HBox headerContainer = new HBox(10, avatarImage, labelsContainer);
        headerContainer.setAlignment(Pos.CENTER_LEFT);

        Label messageLabel = new Label(
                "You have been invited to join an exciting game.\nWill you accept the challenge or decline it?");
        messageLabel.setStyle("-fx-font-family: 'Black Han Sans'; -fx-text-fill: white; -fx-font-size: 12px; -fx-wrap-text: true;");
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(400);

        ButtonType acceptButtonType = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
        ButtonType declineButtonType = new ButtonType("Decline", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(acceptButtonType, declineButtonType);

        Button acceptButton = (Button) dialog.getDialogPane().lookupButton(acceptButtonType);
        acceptButton.setStyle("-fx-font-family: 'Black Han Sans'; -fx-background-color: green; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 20; -fx-cursor: hand; -fx-padding: 10 20;-fx-cursor: hand;");

        acceptButton.setOnAction((e) -> {
            String json = Json.createObjectBuilder()
                    .add("action", 4)
                    .add("status", ACCEPTED)
                    .add("username-player1", jsonObj.getString("username-player1"))
                    .add("username-player2", jsonObj.getString("username-player2"))
                    .add("score-player1", jsonObj.getInt("score-player1"))
                    .add("score-player2", jsonObj.getInt("score-player2"))
                    .build().toString();
            repo.sendInvitation(json);
        });

        Button declineButton = (Button) dialog.getDialogPane().lookupButton(declineButtonType);
        declineButton.setStyle("-fx-font-family: 'Black Han Sans'; -fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 20; -fx-cursor: hand; -fx-padding: 10 20;-fx-cursor: hand;");

        declineButton.setOnAction((e) -> {
            String json = Json.createObjectBuilder()
                    .add("action", 4)
                    .add("status", DECLINED)
                    .add("username-player1", jsonObj.getString("username-player1"))
                    .add("username-player2", jsonObj.getString("username-player2"))
                    .build().toString();
            repo.sendInvitation(json);
        });

        HBox buttonContainer = new HBox(10, acceptButton, declineButton);
        buttonContainer.setAlignment(Pos.CENTER);

        VBox contentPane = new VBox(15, titleLabel, headerContainer, messageLabel, buttonContainer);
        contentPane.setStyle("-fx-font-family: 'Black Han Sans'; -fx-padding: 15; -fx-alignment: center;");

        dialog.getDialogPane().setContent(contentPane);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == acceptButtonType) {
                stage.setScene(new Scene(new Board(new Stage())));
                return true;  // accept button clicked
            } else {
                return false; // decline button clicked
            }
        });

        return dialog.showAndWait();
    }
}
