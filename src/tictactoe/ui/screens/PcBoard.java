package tictactoe.ui.screens;

import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.GetRandomPositionUseCase;
import tictactoe.domain.usecases.GetXOImageUseCase;
import tictactoe.domain.usecases.GetTileUseCase;

public class PcBoard extends Board {

    int currentPosition;

    @Override
    protected void printXO(Tile tile) {
        if (!recordPositionsUseCase.getPositions().contains(tile.getPosition())) {
            return;
        }
        tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(isX));
        recordPositionsUseCase.recordPositions(tile, isX);
        checkWinner();
        reverseXO();
        printXO();
    }

    private void printXO() {

        if (isGameFinished()) {
            return; // Game Finished Alert
        }
        int randomPosition = GetRandomPositionUseCase.getRandomPosition(recordPositionsUseCase.getPositions());
        Tile tile = GetTileUseCase.getTile(tiles, randomPosition);
        tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(isX));
        recordPositionsUseCase.recordPositions(tile, isX);
        checkWinner();
        reverseXO();
    }

}
