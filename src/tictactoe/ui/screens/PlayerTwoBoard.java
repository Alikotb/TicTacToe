package tictactoe.ui.screens;

import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.GetXOImageUseCase;
import tictactoe.domain.usecases.IsWinnerUseCase;

public class PlayerTwoBoard extends Board {
    IsWinnerUseCase winnerCkeck = new IsWinnerUseCase();

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
