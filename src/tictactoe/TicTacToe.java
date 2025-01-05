package tictactoe;

import tictactoe.ui.Home;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.ui.Board;
import tictactoe.ui.SignUp;
import tictactoe.ui.Splash;

/**
 *
 * @author medos
 */
public class TicTacToe extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new SignUp(stage);
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());

        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
