package tictactoe.domain.usecases;

import java.util.ArrayList;
import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.RecordingUseCase;

public class RecordPositionUseCase {

    ArrayList<Integer> positions = new ArrayList<>();
    ArrayList<Integer> playerOnePositions = new ArrayList<>();
    ArrayList<Integer> playerTwoPositions = new ArrayList<>();

    public RecordPositionUseCase() {
        for (int i = 1; i <= 9; i++) {
            positions.add(i);
        }
    }

    private void removePosition(Tile tile) {
        positions.remove(tile.getPosition());
    }

    private void addPosition(Tile tile, boolean isX) {
        if (isX) {
            RecordingUseCase.Pos += tile.getPosition();
            playerOnePositions.add(tile.getPosition());
        } else {
            RecordingUseCase.Pos += tile.getPosition();
            playerTwoPositions.add(tile.getPosition());
        }

    }

    public void recordPositions(Tile tile, boolean isX) {
        removePosition(tile);
        addPosition(tile, isX);
    }

    public ArrayList<Integer> getPlayerOnePositions() {
        return playerOnePositions;
    }

    public ArrayList<Integer> getPlayeTwoPositions() {
        return playerTwoPositions;
    }

    public ArrayList<Integer> getPositions() {
        return positions;
    }
    
    public void clear() {
        positions.clear();
        playerOnePositions.clear();
        playerTwoPositions.clear();
    }

}
