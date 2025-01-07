package tictactoe.ui.screens;

import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.GetXOImageUseCase;

public class PlayerTwoBoard extends Board {

    @Override
    protected void printXO(Tile tile) {
        if (isGameFinished()) {
            return; //Game Finished Alert
        }
        if (!recordPositionsUseCase.getPositions().contains(tile.getPosition())) {
            return;
        }
        tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(isX));
        recordPositionsUseCase.recordPositions(tile, isX);
        reverseXO();
    }
}
