package tictactoe.ui.screens;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.GetXOImageUseCase;
import tictactoe.domain.usecases.IsWinnerUseCase;
import tictactoe.domain.usecases.RecordingUseCase;
import tictactoe.ui.alert.PromptUserNames;

public class PlayerTwoBoard extends Board {

    PlayerTwoBoard(Stage owner) {
        super(owner);
        Stage stage = new Stage();
        stage.setScene(new Scene(new PromptUserNames(stage, this)));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        forfeitBtn.setOnAction(e -> {
            timer.cancel();
            RecordingUseCase.Pos = "";
            playSound.playSound(5);
            owner.setScene(new Scene(new Home(owner)));
        });
    }

    @Override
    protected void printXO(Tile tile) {
        if (isGameFinished()) {
            return;
        }
        if (!recordPositionsUseCase.getPositions().contains(tile.getPosition()) || isFinished) {
            return;
        }
        
        if (isFirst) {
            isFirst=false;
            recordBtn.setDisable(true);
        }
        
        tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(isX));
        recordPositionsUseCase.recordPositions(tile, isX);

        reverseXO();
        timer.cancel();
        timer.startTimer(5, isX); // TODO
        checkWinner();
    }
}
