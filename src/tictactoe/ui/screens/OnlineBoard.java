package tictactoe.ui.screens;

import javafx.application.Platform;
import javafx.stage.Stage;
import javax.json.Json;
import javax.json.JsonObject;
import tictactoe.data.repository.Repo;
import tictactoe.domain.model.Tile;
import tictactoe.domain.usecases.GetRandomPositionUseCase;
import tictactoe.domain.usecases.GetTileUseCase;
import tictactoe.domain.usecases.GetXOImageUseCase;
import static tictactoe.ui.screens.Board.isPlaying;

public class OnlineBoard extends Board {

    Repo repo = new Repo();
    static int position;
    public static OnlineBoard board;

    public OnlineBoard(Stage owner, JsonObject json, boolean isX) {
        super(owner);
        this.isX = isX; 
        System.out.println("isX from OnlineBoard constructor ->  " + isX);

        if (isX) {
            isPlaying = true;
            player1Score.setText(json.getInt("score-player1") + "");
            player2Score.setText(json.getInt("score-player2") + "");
            userNamePlayer1.setText(json.getString("username-player1"));
            userNamePlayer2.setText(json.getString("username-player2"));
        } else { // O
            isPlaying = false;
            player2Score.setText(json.getInt("score-player2") + "");
            player1Score.setText(json.getInt("score-player1") + "");
            userNamePlayer2.setText(json.getString("username-player2"));
            userNamePlayer1.setText(json.getString("username-player1"));
        }
        board = this;
        timer.startTimer(5, true);
        timer.setOnTimeStopped(() -> {
            printXO();
        });

    }

    @Override
    protected void printXO(Tile tile) {
        if (!isPlaying) {
            return;
        }
        if (isGameFinished()) {
            return;
        }
        if (!recordPositionsUseCase.getPositions().contains(tile.getPosition())) {
            return;
        }
        tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(isX));
        playSound();
        recordPositionsUseCase.recordPositions(tile, isX);

        isPlaying = false;

        timer.cancel();
        timer.startTimer(5, !isX);
        checkWinner();
        sendRequest(tile.getPosition());
    }

    @Override
    protected void printXO() {

        if (isGameFinished()) {
            return;
        }

        int randomPosition = GetRandomPositionUseCase.getRandomPosition(recordPositionsUseCase.getPositions());
        Tile tile = GetTileUseCase.getTile(tiles, randomPosition);

        if (isX && isPlaying) {
            tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(isX));
            recordPositionsUseCase.recordPositions(tile, isX);
            playSound();
            isPlaying = false;
            timer.cancel();
            timer.startTimer(5, !isX);
            sendRequest(randomPosition);
        } else if (!isX && isPlaying) {
            tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(isX));
            recordPositionsUseCase.recordPositions(tile, isX);
            playSound();
            isPlaying = false;
            timer.cancel();
            timer.startTimer(5, !isX);
            sendRequest(randomPosition);
        }
    }

    private void sendRequest(int pos) {
        String json = Json.createObjectBuilder()
                .add("action", 5)
                .add("username-player1", userNamePlayer1.getText())
                .add("username-player2", userNamePlayer2.getText())
                .add("isX", isX)
                .add("position", pos)
                .build().toString();
        repo.sendMove(json);
    }

    protected void printXOOpponent() {

        Tile tile = GetTileUseCase.getTile(tiles, position);
        tile.getBtn().setGraphic(GetXOImageUseCase.getXOImage(!isX));
        recordPositionsUseCase.recordPositions(tile, isX);
        if (isX && isPlaying) {
            timer.cancel();
            timer.startTimer(5, isX);
        } else if (!isX && isPlaying) {
            timer.cancel();
            timer.startTimer(5, isX);
        }

    }

    public void nextTurn(JsonObject json) {
        Platform.runLater(() -> {
            position = json.getInt("position");
            isPlaying = true;
            printXOOpponent();

            if (json.getBoolean("isX")) {
                playSound.playSound(2);
            } else {
                playSound.playSound(1);
            }
        });

    }

}
