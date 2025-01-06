/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.domain.usecases;

import java.util.ArrayList;
import tictactoe.domain.model.Tile;

public class HandlePositionsUseCase {

    ArrayList<Integer> positions = new ArrayList<>();
    ArrayList<Integer> playerOnePositions = new ArrayList<>();
    ArrayList<Integer> pcPositions = new ArrayList<>();

    private void removePosition(Tile tile) {
        positions.remove(tile.getPosition());
    }

    private void addPosition(Tile tile) {
        playerOnePositions.add(tile.getPosition());
    }

    public void handlePositions(Tile tile) {
        removePosition(tile);
        addPosition(tile);
    }

    public ArrayList<Integer> getPlayerOnePositions() {
        return playerOnePositions;
    }

}
