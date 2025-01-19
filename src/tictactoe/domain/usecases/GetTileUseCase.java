/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.domain.usecases;

import java.util.ArrayList;
import tictactoe.domain.model.Tile;

/**
 *
 * @author medos
 */
public class GetTileUseCase {
    
    public static Tile getTile(ArrayList<Tile> tiles, int position) {
         for (Tile tile : tiles) {
             if (tile.getPosition() == position) {
                 return tile;
             }
         }
         return null;
    }
    
}
