package tictactoe.ui.screens;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.IsWinnerUseCase;
import tictactoe.domain.usecases.PlayBackgroundMusicUseCase;
import tictactoe.domain.usecases.PlaySoundUseCase;
import tictactoe.domain.usecases.RecordPositionUseCase;

public class Board extends BorderPane {

    protected final ImageView imageView;
    protected final FlowPane flowPaneCenter;
    protected final FlowPane flowPaneRow1;
    protected final Tile tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9;
    protected final Line line1;
    protected final Line line2;
    protected final Line line3;
    protected final FlowPane flowPaneRow2;
    protected final Line line4;
    protected final Line line5;
    protected final Line line6;
    protected final FlowPane flowPaneRow3;
    protected final Line line7;
    protected final Line line8;
    protected final VBox vbPlayer2;
    protected final ImageView ivPlayer2;
    protected final Label userNamePlayer2;
    protected final VBox vbPlayer1;
    protected final ImageView ivPlayer1;
    protected final Label userNamePlayer1;

    ArrayList<Tile> tiles;
    protected RecordPositionUseCase recordPositionsUseCase;
    protected PlaySoundUseCase playSound;
    protected IsWinnerUseCase winnerCkeck;

    boolean isX, isFinished;

    public Board() {
        PlayBackgroundMusicUseCase.getInstance().stopBackgroundMusic();

        imageView = new ImageView(new Image("/resources/images/logo.png"));
        flowPaneCenter = new FlowPane();
        flowPaneRow1 = new FlowPane();
        tile1 = new Tile(1);
        line1 = new Line();
        tile2 = new Tile(2);
        line2 = new Line();
        tile3 = new Tile(3);
        line3 = new Line();
        flowPaneRow2 = new FlowPane();
        tile4 = new Tile(4);
        line4 = new Line();
        tile5 = new Tile(5);
        line5 = new Line();
        tile6 = new Tile(6);
        line6 = new Line();
        flowPaneRow3 = new FlowPane();
        tile7 = new Tile(7);
        line7 = new Line();
        tile8 = new Tile(8);
        line8 = new Line();
        tile9 = new Tile(9);
        vbPlayer2 = new VBox();
        ivPlayer2 = new ImageView(new Image("/resources/images/player1.png"));
        userNamePlayer2 = new Label();
        vbPlayer1 = new VBox();
        ivPlayer1 = new ImageView(new Image("/resources/images/player1.png"));
        userNamePlayer1 = new Label();

        isX = true;
        tiles = new ArrayList<>();
        recordPositionsUseCase = new RecordPositionUseCase();
        playSound = new PlaySoundUseCase();
        winnerCkeck = new IsWinnerUseCase();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(800.0);

        BorderPane.setAlignment(imageView, javafx.geometry.Pos.TOP_CENTER);
        imageView.setFitHeight(108.0);
        imageView.setFitWidth(308.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        BorderPane.setMargin(imageView, new Insets(70.0, 0.0, 0.0, 0.0));
        imageView.setImage(new Image(getClass().getResource("/resources/images/logo.png").toExternalForm()));
        setTop(imageView);

        BorderPane.setAlignment(flowPaneCenter, javafx.geometry.Pos.CENTER);
        flowPaneCenter.setAlignment(javafx.geometry.Pos.CENTER);
        flowPaneCenter.setOrientation(javafx.geometry.Orientation.VERTICAL);
        flowPaneCenter.setPrefHeight(300.0);
        flowPaneCenter.setPrefWidth(320.0);

        flowPaneRow1.setPrefHeight(100.0);
        flowPaneRow1.setPrefWidth(320.0);

        tile1.getBtn().setMnemonicParsing(false);
        tile1.getBtn().setPrefHeight(100.0);
        tile1.getBtn().setPrefWidth(100.0);
        tile1.getBtn().setCursor(Cursor.HAND);
        tile1.getBtn().setBackground(null);

        line1.setEndY(250.0);
        line1.setStartY(160.0);
        line1.setStroke(javafx.scene.paint.Color.WHITE);
        line1.setStrokeWidth(10.0);

        tile2.getBtn().setMnemonicParsing(false);
        tile2.getBtn().setPrefHeight(100.0);
        tile2.getBtn().setPrefWidth(100.0);
        tile2.getBtn().setCursor(Cursor.HAND);
        tile2.getBtn().setBackground(null);

        line2.setEndY(250.0);
        line2.setStartY(160.0);
        line2.setStroke(javafx.scene.paint.Color.WHITE);
        line2.setStrokeWidth(10.0);

        tile3.getBtn().setMnemonicParsing(false);
        tile3.getBtn().setPrefHeight(100.0);
        tile3.getBtn().setPrefWidth(100.0);
        tile3.getBtn().setCursor(Cursor.HAND);
        tile3.getBtn().setBackground(null);

        line3.setEndX(550.0);
        line3.setStartX(240.0);
        line3.setStroke(javafx.scene.paint.Color.WHITE);
        line3.setStrokeWidth(10.0);

        flowPaneRow2.setPrefHeight(100.0);
        flowPaneRow2.setPrefWidth(320.0);

        tile4.getBtn().setMnemonicParsing(false);
        tile4.getBtn().setPrefHeight(100.0);
        tile4.getBtn().setPrefWidth(100.0);
        tile4.getBtn().setCursor(Cursor.HAND);
        tile4.getBtn().setBackground(null);

        line4.setEndY(250.0);
        line4.setStartY(160.0);
        line4.setStroke(javafx.scene.paint.Color.WHITE);
        line4.setStrokeWidth(10.0);

        tile5.getBtn().setMnemonicParsing(false);
        tile5.getBtn().setPrefHeight(100.0);
        tile5.getBtn().setPrefWidth(100.0);
        tile5.getBtn().setCursor(Cursor.HAND);
        tile5.getBtn().setBackground(null);

        line5.setEndY(250.0);
        line5.setStartY(160.0);
        line5.setStroke(javafx.scene.paint.Color.WHITE);
        line5.setStrokeWidth(10.0);

        tile6.getBtn().setMnemonicParsing(false);
        tile6.getBtn().setPrefHeight(100.0);
        tile6.getBtn().setPrefWidth(100.0);
        tile6.getBtn().setCursor(Cursor.HAND);
        tile6.getBtn().setBackground(null);

        line6.setEndX(550.0);
        line6.setStartX(240.0);
        line6.setStroke(javafx.scene.paint.Color.WHITE);
        line6.setStrokeWidth(10.0);

        flowPaneRow3.setPrefHeight(100.0);
        flowPaneRow3.setPrefWidth(320.0);

        tile7.getBtn().setMnemonicParsing(false);
        tile7.getBtn().setPrefHeight(100.0);
        tile7.getBtn().setPrefWidth(100.0);
        tile7.getBtn().setCursor(Cursor.HAND);
        tile7.getBtn().setBackground(null);

        line7.setEndY(250.0);
        line7.setStartY(160.0);
        line7.setStroke(javafx.scene.paint.Color.WHITE);
        line7.setStrokeWidth(10.0);

        tile8.getBtn().setMnemonicParsing(false);
        tile8.getBtn().setPrefHeight(100.0);
        tile8.getBtn().setPrefWidth(100.0);
        tile8.getBtn().setCursor(Cursor.HAND);
        tile8.getBtn().setBackground(null);

        line8.setEndY(250.0);
        line8.setStartY(160.0);
        line8.setStroke(javafx.scene.paint.Color.WHITE);
        line8.setStrokeWidth(10.0);

        tile9.getBtn().setMnemonicParsing(false);
        tile9.getBtn().setPrefHeight(100.0);
        tile9.getBtn().setPrefWidth(100.0);
        tile9.getBtn().setCursor(Cursor.HAND);
        tile9.getBtn().setBackground(null);
        setCenter(flowPaneCenter);

        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile4);
        tiles.add(tile5);
        tiles.add(tile6);
        tiles.add(tile7);
        tiles.add(tile8);
        tiles.add(tile9);

        BorderPane.setAlignment(vbPlayer2, javafx.geometry.Pos.CENTER);
        vbPlayer2.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vbPlayer2.setPrefHeight(200.0);
        vbPlayer2.setPrefWidth(240.0);
        BorderPane.setMargin(vbPlayer2, new Insets(36.0, 0.0, 0.0, 0.0));

        ivPlayer2.setFitHeight(120.0);
        ivPlayer2.setFitWidth(120.0);
        ivPlayer2.setPickOnBounds(true);
        ivPlayer2.setPreserveRatio(true);
        ivPlayer2.setImage(new Image(getClass().getResource("/resources/images/player2.png").toExternalForm()));
        VBox.setMargin(ivPlayer2, new Insets(0.0, 0.0, 15.0, 0.0));

        Font font = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/MyCustomFont.ttf"), 25.0);  // 16px size
        setStyle("-fx-background-color: linear-gradient(to bottom, #EBF8FF, #71B9D7, #0D88B7)");

//        if (mode == MODE_PC) {
//            userNamePlayer1.setText("Player 1");
//            userNamePlayer2.setText("PC");
//        } else if (mode == MODE_PLAYER) {
//            userNamePlayer1.setText("Player 1");
//            userNamePlayer2.setText("Player 2");
//        } else if (mode == MODE_ONLINE) {
//            userNamePlayer1.setText("Yousef");
//            userNamePlayer2.setText("Ali");
//        }
        userNamePlayer2.setTextFill(javafx.scene.paint.Color.WHITE);
        userNamePlayer2.setWrapText(true);
        userNamePlayer2.setFont(font);
        setRight(vbPlayer2);

        BorderPane.setAlignment(vbPlayer1, javafx.geometry.Pos.CENTER);
        vbPlayer1.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vbPlayer1.setPrefHeight(200.0);
        vbPlayer1.setPrefWidth(240.0);

        ivPlayer1.setFitHeight(120.0);
        ivPlayer1.setFitWidth(120.0);
        ivPlayer1.setPickOnBounds(true);
        ivPlayer1.setPreserveRatio(true);
        ivPlayer1.setImage(new Image(getClass().getResource("/resources/images/player1.png").toExternalForm()));
        VBox.setMargin(ivPlayer1, new Insets(0.0, 0.0, 15.0, 0.0));

        userNamePlayer1.setTextFill(javafx.scene.paint.Color.WHITE);
        userNamePlayer1.setFont(font);
        BorderPane.setMargin(vbPlayer1, new Insets(36.0, 0.0, 0.0, 0.0));
        setLeft(vbPlayer1);

        flowPaneRow1.getChildren().add(tile1.getBtn());
        flowPaneRow1.getChildren().add(line1);
        flowPaneRow1.getChildren().add(tile2.getBtn());
        flowPaneRow1.getChildren().add(line2);
        flowPaneRow1.getChildren().add(tile3.getBtn());
        flowPaneCenter.getChildren().add(flowPaneRow1);
        flowPaneCenter.getChildren().add(line3);
        flowPaneRow2.getChildren().add(tile4.getBtn());
        flowPaneRow2.getChildren().add(line4);
        flowPaneRow2.getChildren().add(tile5.getBtn());
        flowPaneRow2.getChildren().add(line5);
        flowPaneRow2.getChildren().add(tile6.getBtn());
        flowPaneCenter.getChildren().add(flowPaneRow2);
        flowPaneCenter.getChildren().add(line6);
        flowPaneRow3.getChildren().add(tile7.getBtn());
        flowPaneRow3.getChildren().add(line7);
        flowPaneRow3.getChildren().add(tile8.getBtn());
        flowPaneRow3.getChildren().add(line8);
        flowPaneRow3.getChildren().add(tile9.getBtn());
        flowPaneCenter.getChildren().add(flowPaneRow3);
        vbPlayer2.getChildren().add(ivPlayer2);
        vbPlayer2.getChildren().add(userNamePlayer2);
        vbPlayer1.getChildren().add(ivPlayer1);
        vbPlayer1.getChildren().add(userNamePlayer1);

        setListeners();

    }

    protected void printXO(Tile tile) {
    }

    protected void checkWinner() {
        int result = winnerCkeck.isWinner(recordPositionsUseCase);
        if (result == 1) {
            isFinished = true;
            highlightWinningTiles(winnerCkeck.getWinningPositions());
        } else if (result == 2) {
            isFinished = true;
            highlightWinningTiles(winnerCkeck.getWinningPositions());
        } 
    }

    protected void highlightWinningTiles(List<Integer> winningPositions) {
        for (Integer position : winningPositions) {
            Tile tile = getTileByPosition(position);
            if (tile != null) {
                tile.getBtn().setStyle("-fx-background-color: #89CFF0");
            }
        }
    }

    private Tile getTileByPosition(int position) {
        for (Tile tile : tiles) {
            if (tile.getPosition() == position) {
                return tile;
            }
        }
        return null;
    }

    protected boolean isGameFinished() {
        return recordPositionsUseCase.getPositions().isEmpty()|| isFinished;
    }

    protected void reverseXO() {
        isX = !isX;
    }

    private void setListeners() {
        for (Tile tile : tiles) {
            tile.getBtn().setOnAction((e) -> {
                printXO(tile);
            });

        }
    }

    protected void playSound() {
        if (isX) {
            playSound.playSound(2);
        } else {
            playSound.playSound(1);
        }

    }

}
