package tictactoe.ui.alert;

import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import tictactoe.domain.usecases.PlayBackgroundMusicUseCase;
import tictactoe.domain.usecases.RecordingUseCase;
import tictactoe.ui.screens.Board;
import tictactoe.ui.screens.LogInBase;
import tictactoe.ui.screens.NewGame1Base;
import tictactoe.ui.screens.OfflineBase;
import tictactoe.ui.screens.OnlineBoard;

public class EndGameAlert {
    protected File file;
    private Media video;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private Stage stage;
    private Stage alertStage;
    private Board board;
    int score;
    String username;
    
    public EndGameAlert(char status, Stage stage, Board board,int score,String username ) {
    this(status,stage,board);    
    this.score = score;
    this.username= username;
    }
    public EndGameAlert(char status, Stage stage, Board board) {
        this.stage = stage;
        this.board = board;
        
        if (status == 'w') {
            file = new File("src/resources/videos/win2.mp4");
        } else if (status == 'l') {
            file = new File("src/resources/videos/lose.mp4");
        } else {
            file = new File("src/resources/videos/draw.mp4");
        }
    }

    public void show() {
        if (file != null) {

            video = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(video);
            mediaView = new MediaView(mediaPlayer);

            mediaPlayer.setOnReady(() -> mediaPlayer.play());

            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.stop());

            Label videoLabel = new Label();
            videoLabel.setGraphic(mediaView);
            VBox.setMargin(videoLabel, new Insets(0, 200, 20, 200));

            Button restartButton = new Button("Restart");
            Button exitButton = new Button("Exit");
            restartButton.setId("restartBtn");
            exitButton.setId("exitBtn");
            restartButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");
            exitButton.setStyle("-fx-background-color: red; -fx-text-fill: white;-fx-font-size: 24px; -fx-font-weight: bold;");

            restartButton.setOnAction(e -> {
                mediaPlayer.stop();
                board.restartGame();
                alertStage.close();
            });

            exitButton.setOnAction(e -> {
                mediaPlayer.stop();
                alertStage.close();
                PlayBackgroundMusicUseCase.getInstance().startBackgroundMusic();
                Parent root = stage.getScene().getRoot();
                if (root instanceof OnlineBoard) {
                    Scene newgameScene = new Scene(new NewGame1Base(stage,username,score), 800, 600);
                    stage.setScene(newgameScene);
                    newgameScene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
                } else {
                    Scene offlineScene = new Scene(new OfflineBase(stage), 800, 600);
                    stage.setScene(offlineScene);
                    offlineScene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
                }
                

            });

            FlowPane buttonPane = new FlowPane();
            buttonPane.setHgap(200);
            buttonPane.setAlignment(Pos.CENTER);
            buttonPane.getChildren().addAll(restartButton, exitButton);

            VBox vbox = new VBox(10, videoLabel, buttonPane);
            vbox.setStyle("-fx-alignment: center; -fx-padding: 5; -fx-background-color: #1F509A;");

            Scene alertScene = new Scene(vbox, 700, 500);
            alertStage = new Stage();

            alertStage.setScene(alertScene);
            alertStage.initStyle(StageStyle.UNDECORATED);
            alertStage.setResizable(false);
            alertStage.initOwner(stage);
            alertStage.setX(stage.getX() + (stage.getWidth() / 2) - 350);
            alertStage.setY(stage.getY() + (stage.getHeight() / 2) - 250);
            alertStage.setOnCloseRequest(e -> {
                RecordingUseCase.Pos = "";
                e.consume();
            });

            alertStage.show();

        } else {
            System.out.println("Sorry File is Empty");
        }
    }
}
