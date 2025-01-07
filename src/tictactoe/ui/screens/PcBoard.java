package tictactoe.ui.screens;

import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.GetRandomPositionUseCase;
import tictactoe.domain.usecases.GetTileIconUseCase;
import tictactoe.domain.usecases.GetTileUseCase;

public class PcBoard extends Board {

    int currentPosition;

    @Override
    protected void printXO(Tile tile) {
        if (!handlePositionsUseCase.getPositions().contains(tile.getPosition())) {
            return;
        }
        tile.getBtn().setGraphic(GetTileIconUseCase.getTileIcon(isX));
        handlePositionsUseCase.recordPositions(tile, isX);
        reverseXO();
        printXO();
    }

    private void printXO() {

        if (isGameFinished()) {
            return; // Game Finished Alert
        }
        int randomPosition = GetRandomPositionUseCase.getRandomPosition(handlePositionsUseCase.getPositions());
        Tile tile = GetTileUseCase.getTile(tiles, randomPosition);
        tile.getBtn().setGraphic(GetTileIconUseCase.getTileIcon(isX));
        handlePositionsUseCase.recordPositions(tile, isX);
        reverseXO();
    }

}
