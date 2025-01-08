package tictactoe.ui.screens;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.GetRandomPositionUseCase;
import tictactoe.domain.usecases.GetXOImageUseCase;
import tictactoe.domain.usecases.GetTileUseCase;
import tictactoe.ui.alert.PlayerOnePopUp;

public class PcBoard extends Board {

    public PcBoard(Stage owner) {
       super(owner);
        Stage stage = new Stage();
        stage.setScene(new Scene(new PlayerOnePopUp(stage, this)));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);
        stage.show();
    }

    @Override
    protected void printXO(Tile tile) {
        if (!recordPositionsUseCase.getPositions().contains(tile.getPosition())) {
            return;
        }
        tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(isX));
        recordPositionsUseCase.recordPositions(tile, isX);
        reverseXO();
        printXO();
    }

    protected void printXO() {

        if (isGameFinished()) {
            return; // Game Finished Alert
        }
        int randomPosition = GetRandomPositionUseCase.getRandomPosition(recordPositionsUseCase.getPositions());
        Tile tile = GetTileUseCase.getTile(tiles, randomPosition);
        tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(isX));
        recordPositionsUseCase.recordPositions(tile, isX);
        reverseXO();
    }
}
