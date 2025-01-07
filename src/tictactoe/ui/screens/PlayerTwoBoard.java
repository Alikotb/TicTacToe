package tictactoe.ui.screens;

import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.GetTileIconUseCase;

public class PlayerTwoBoard extends Board {

    @Override
    protected void printXO(Tile tile) {
        if (isGameFinished()) {
            return; //Game Finished Alert
        }
        if (!handlePositionsUseCase.getPositions().contains(tile.getPosition())) {
            return;
        }
        tile.getBtn().setGraphic(GetTileIconUseCase.getTileIcon(isX));
        handlePositionsUseCase.recordPositions(tile, isX);
        reverseXO();
    }
}
