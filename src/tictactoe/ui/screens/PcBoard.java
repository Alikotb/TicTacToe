package tictactoe.ui.screens;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.GetRandomPositionUseCase;
import tictactoe.domain.usecases.GetXOImageUseCase;
import tictactoe.domain.usecases.GetTileUseCase;
import tictactoe.ui.alert.PromptUserName;

public class PcBoard extends Board {

    public PcBoard(Stage owner) {
        super(owner);
        Stage stage = new Stage();
        stage.setScene(new Scene(new PromptUserName(stage, this)));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);
        stage.show();

        timer.setOnTimeStopped(() -> {
            printXO();
        });
    }

    @Override
    protected void printXO(Tile tile) {
        if (!recordPositionsUseCase.getPositions().contains(tile.getPosition())) {
            return;
        }
        tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(isX));
        recordPositionsUseCase.recordPositions(tile, isX);
        checkWinner();
        reverseXO();
        timer.startTimer(1);
    }
}
