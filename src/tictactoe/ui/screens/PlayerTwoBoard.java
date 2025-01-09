package tictactoe.ui.screens;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.GetXOImageUseCase;
import tictactoe.domain.usecases.IsWinnerUseCase;
import tictactoe.ui.alert.PlayerTwoPopUp;

public class PlayerTwoBoard extends Board {
  
    PlayerTwoBoard(Stage owner) {
        super(owner);
        Stage stage = new Stage();
        stage.setScene(new Scene(new PlayerTwoPopUp(stage, this)));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);
        stage.show();
    }

    @Override
    protected void printXO(Tile tile) {
        if (isGameFinished()) {
            return; //Game Finished Alert
        }
        if (!recordPositionsUseCase.getPositions().contains(tile.getPosition())) {
            return;
        }
        tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(isX));
        playSound();
        recordPositionsUseCase.recordPositions(tile, isX);
        checkWinner();
        reverseXO();
    }
}
