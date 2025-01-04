package tictactoe.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

public class RecordItem extends HBox {

    protected final ImageView userImage;
    protected final Label userName;
    protected final Region region;
    protected final Label winStatus;

    public RecordItem(String username, boolean isWon) {

        userImage = new ImageView();
        userName = new Label();
        region = new Region();
        winStatus = new Label();

        setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        setPrefHeight(84.0);
        setPrefWidth(334.0);

        userImage.setFitHeight(64.0);
        userImage.setFitWidth(64.0);
        userImage.setPickOnBounds(true);
        userImage.setPreserveRatio(true);
        userImage.setImage(new Image("/resources/images/player1.png"));
        HBox.setMargin(userImage, new Insets(0.0, 8.0, 0.0, 8.0));

        userName.setText(username);
        userName.setTextFill(javafx.scene.paint.Color.valueOf("#1f509a"));
        userName.setFont(new Font(24.0));

        HBox.setHgrow(region, javafx.scene.layout.Priority.ALWAYS);
        region.setPrefHeight(85.0);
        region.setPrefWidth(10.0);

        winStatus.setAlignment(javafx.geometry.Pos.TOP_RIGHT);

        if (isWon) {
            winStatus.setText("VICTORY");
            winStatus.setTextFill(javafx.scene.paint.Color.valueOf("#28a745"));
        } else {
            winStatus.setText("DEFEAT");
            winStatus.setTextFill(javafx.scene.paint.Color.valueOf("#FF4C4C"));
        }

//        winStatus.setFont(new Font("System Bold", 24.0));
        winStatus.setPadding(new Insets(0.0, 8.0, 0.0, 0.0));
        HBox.setMargin(winStatus, new Insets(0.0, 8.0, 0.0, 0.0));

        getChildren().add(userImage);
        getChildren().add(userName);
        getChildren().add(region);
        getChildren().add(winStatus);

    }
}
