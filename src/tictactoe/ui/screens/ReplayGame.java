package tictactoe.ui.screens;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tictactoe.domain.model.Record;
import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.GetXOImageUseCase;
import tictactoe.ui.alert.EndReplayGameAlert;

public class ReplayGame extends Board {
    
    private Record gameRecord;
    private boolean isX;
    private Tile tile;
    private Timeline timeline;
    private String  winnerName;

    public ReplayGame(Stage stage, Record gameRecord) {
        super(stage);
        this.gameRecord = gameRecord;
        selectWinnerPlayer();
        recordBtn.setDisable(true);
        forfeitBtn.setDisable(true);
        replayMoves();
    }

    private void selectWinnerPlayer() {
        userNamePlayer1.setText(gameRecord.getUser1());
        userNamePlayer2.setText(gameRecord.getUser2());
        player1Score.setText(null);
        player2Score.setText(null);

        switch (gameRecord.getWinner()) {
            case 'W': {
                winnerName  = gameRecord.getUser1();
                break;
            }
            case 'L': {
                winnerName  = gameRecord.getUser2();
                break;
            }
        }
    }

    private void replayMoves() {
        int[] positions = getPositionsArray();
        timeline = new Timeline();
        for (int i = 0; i < positions.length; i++) {
            int index = i;
            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds(index * 2),
                    print -> {
                        tile = tiles.get(positions[index]);
                        if (index % 2 != 0) {
                            tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(isX));
                            playSound.playSound(2);
                        } else {
                            tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(!isX));
                            playSound.playSound(1);
                        }
                    }
            );
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.play();
        timeline.setOnFinished(event -> {
            new Timeline(new KeyFrame(
                    Duration.seconds(2),
                    e -> {
                        showAlert();
                    }
            )).play();
        });
    }

    private int[] getPositionsArray() {
        String positions = gameRecord.getPsitions();
        int[] positionArray = new int[positions.length()];
        for (int i = 0; i < positions.length(); i++) {
            positionArray[i] = Character.getNumericValue(positions.charAt(i)) - 1;
        }
        return positionArray;
    }

    private void loadNextPage() {
        Scene scene = new Scene(new OfflineBase(stage), 800, 600);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
    }

    private void showAlert() {
        Stage alertStage = new Stage();        
        Scene alertScene = new EndReplayGameAlert().showAlert(stage,alertStage, this::loadNextPage,gameRecord.getWinner(),winnerName);
        alertStage.setScene(alertScene);
        alertStage.initOwner(stage); 
        alertStage.initStyle(StageStyle.UNDECORATED);
        alertStage.show();
    }
}
