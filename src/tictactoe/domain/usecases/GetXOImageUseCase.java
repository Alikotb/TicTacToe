package tictactoe.domain.usecases;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GetXOImageUseCase {
    public static ImageView getXOImage(boolean isX) {
        Image img;
        if (isX) {
            img = new Image("/resources/images/X.png");
        } else {
            img = new Image("/resources/images/O.png");
        }
        
        return new ImageView(img);
    }
}
