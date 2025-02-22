package tictactoe.ui.screens;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.GetXOImageUseCase;
import tictactoe.domain.usecases.RecordingUseCase;
import tictactoe.domain.usecases.ShowPopupUseCase;
import tictactoe.ui.alert.PromptUserName;

public class PcBoard extends Board {

    public PcBoard(Stage owner) {
        super(owner);
        Stage stage = new Stage();
        stage.setScene(new Scene(new PromptUserName(stage, this)));
        ShowPopupUseCase.showPopup(owner, stage, 206, StageStyle.UNDECORATED);

        timer.setOnTimeStopped(() -> {
            printXO();
        });

        isPc = true;
        isPlaying = true;
        playerTwoImage.setImage(new Image("/resources/images/robot.png"));

        forfeitBtn.setOnAction(e -> {
            timer.cancel();
            RecordingUseCase.Pos = "";
            playSound.playSound(5);
            owner.setScene(new Scene(new Home(owner)));
        });
    }

    @Override
    protected void printXO(Tile tile) {
        if (!isPlaying) {
            return;
        }

        if (!recordPositionsUseCase.getPositions().contains(tile.getPosition()) || isFinished) {
            return;
        }

        if (isFirst) {
            isFirst = false;
            recordBtn.setDisable(true);
        }
        tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(isX));
        recordPositionsUseCase.recordPositions(tile, isX);
        isPlaying = false;
        checkWinner();
        reverseXO();
        timer.startTimer(1);
    }
}
