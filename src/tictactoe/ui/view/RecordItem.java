package tictactoe.ui.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import tictactoe.domain.model.Record;
import tictactoe.domain.usecases.RandomAvatarUseCase;

public class RecordItem extends HBox {

    protected final ImageView userImage;
    public Label playerOne;
    public Label playerTwo;
    public Label vsLabel;
    protected final Region region;
    protected final Label winStatus;
    protected Record record;

    public RecordItem(Record gameRecord) {
        this.record = gameRecord;
        userImage = new ImageView();
        playerOne = new Label();
        playerTwo = new Label();
        vsLabel = new Label("vs");
        region = new Region();
        winStatus = new Label();

        setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        setSpacing(5);
        setPadding(new Insets(10));

        userImage.setPickOnBounds(true);
        userImage.setPreserveRatio(true);
        userImage.setFitWidth(80);
        userImage.setFitHeight(80);
        Circle mask = new Circle(40, 40, 40);
        userImage.setClip(mask);
        userImage.setImage(new RandomAvatarUseCase().getRandomAvatar());
        HBox.setMargin(userImage, new Insets(0.0, 8.0, 0.0, 8.0));

        Font font = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/MyCustomFont.ttf"), 25.0);

        playerOne.setText(record.getUser1());
        playerOne.setTextFill(javafx.scene.paint.Color.valueOf("#1f509a"));
        playerOne.setFont(font);

        vsLabel.setTextFill(javafx.scene.paint.Color.valueOf("#000000"));
        vsLabel.setFont(font);
        HBox.setMargin(vsLabel, new Insets(0.0, 8.0, 0.0, 8.0));

        playerTwo.setText(record.getUser2());
        playerTwo.setTextFill(javafx.scene.paint.Color.valueOf("#1f509a"));
        playerTwo.setFont(font);

        HBox.setHgrow(region, javafx.scene.layout.Priority.ALWAYS);
        region.setPrefHeight(85.0);
        region.setPrefWidth(10.0);

        winStatus.setAlignment(javafx.geometry.Pos.TOP_RIGHT);

        if (record.getWinner() == 'W') {
            winStatus.setText("VICTORY");
            winStatus.setTextFill(javafx.scene.paint.Color.valueOf("#28a745"));

        } else if (record.getWinner() == 'E') {
            winStatus.setText("DRAW");
            winStatus.setTextFill(javafx.scene.paint.Color.valueOf("#ffa500"));
        } else {
            winStatus.setText("DEFEAT");
            winStatus.setTextFill(javafx.scene.paint.Color.valueOf("#FF4C4C"));
        }
        winStatus.setFont(font);
        winStatus.setPadding(new Insets(0.0, 8.0, 0.0, 0.0));
        HBox.setMargin(winStatus, new Insets(0.0, 8.0, 0.0, 0.0));

        getChildren().add(userImage);
        getChildren().add(playerOne);
        getChildren().add(vsLabel);
        getChildren().add(playerTwo);
        getChildren().add(region);
        getChildren().add(winStatus);
    }

    public Record getRecord() {
        return record;
    }
}
