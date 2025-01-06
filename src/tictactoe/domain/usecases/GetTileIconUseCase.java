/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.domain.usecases;

import java.io.File;
import javafx.scene.image.ImageView;

/**
 *
 * @author medos
 */
public class GetTileIconUseCase {
    public static ImageView getTileIcon(boolean isX) {
        File file;
        if (isX) {
            file = new File("D:/intake 45/Tracks/Java/Project/TicTacToe/src/resources/images/X.png");
        } else {
            file = new File("D:/intake 45/Tracks/Java/Project/TicTacToe/src/resources/images/O.png");
        }
        
        return new ImageView(file.toURI().toString());
    }
}
