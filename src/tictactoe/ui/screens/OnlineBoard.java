package tictactoe.ui.screens;

import javafx.stage.Stage;
import javax.json.JsonObject;

public class OnlineBoard extends Board {

    public OnlineBoard(Stage owner, JsonObject json, boolean isX) {
        super(owner);
    }

}
