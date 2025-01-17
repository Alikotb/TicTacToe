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
import tictactoe.domain.usecases.IsWinnerUseCase;
import tictactoe.domain.usecases.RecordingUseCase;
import tictactoe.domain.usecases.ToJesonUseCase;
import tictactoe.ui.alert.EndGameAlert;
import static tictactoe.ui.screens.Board.isPlaying;

public class OnlineBoard extends Board {

    Repo repo = new Repo();
    static int position;
    public static OnlineBoard board;
    protected IsWinnerUseCase winnerCkeck;

    public OnlineBoard(Stage owner, JsonObject json, boolean isX) {
        super(owner);
        this.isX = isX;
        winnerCkeck = new IsWinnerUseCase();
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

        forfeitBtn.setOnAction(e -> {
            timer.cancel();
            isFinished = true;
            sendRequest(0);
            if (isX) {
                displayEndGameAlertLoseP1('l');
                System.out.println("player 1 lose");
            } else {
                displayEndGameAlertLoseP2('l');
            }
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
        recordPositionsUseCase.recordPositions(tile, !isX);
        checkWinner();
    }

    public void nextTurn(JsonObject json) {
        Platform.runLater(() -> {
            position = json.getInt("position");
            if (position == 0) {
                timer.cancel();
                if (isX) {
                    player1ScoreValue = Integer.parseInt(player2Score.getText()) + 100;
                    setPlayer2Score(player1ScoreValue);
                    updateScoreInDatabase(userNamePlayer2.getText(), player1ScoreValue);
                    displayEndGameAlertWinP1('w');

                } else {
                    player2ScoreValue = Integer.parseInt(player2Score.getText()) + 100;
                    setPlayer2Score(player2ScoreValue);
                    updateScoreInDatabase(userNamePlayer2.getText(), player2ScoreValue);
                    displayEndGameAlertWinP2('w');

                }
            }
            isPlaying = true;
            printXOOpponent();

            if (json.getBoolean("isX")) {
                playSound.playSound(2);
            } else {
                playSound.playSound(1);
            }
        });

    }

    @Override
    protected void checkWinner() {
        player1ScoreValue = Integer.parseInt(player1Score.getText());
        player2ScoreValue = Integer.parseInt(player2Score.getText());

        int winner = winnerCkeck.isWinner(recordPositionsUseCase);
        if (winner != 0) {
            isFinished = true;
            isPlaying = false;
            timer.cancel();

            if (winner == 1) {
                highlightWinningTiles(winnerCkeck.getWinningPositions());
                player1ScoreValue = Integer.parseInt(player1Score.getText()) + 100;
                setPlayer1Score(player1ScoreValue);
                updateScoreInDatabase(userNamePlayer1.getText(), player1ScoreValue);

                if (isX) {
                    displayEndGameAlertWinP1('w');
                } else {
                    displayEndGameAlertLoseP2('l');
                }

            } else if (winner == 2) {
                highlightWinningTiles(winnerCkeck.getWinningPositions());
                player2ScoreValue = Integer.parseInt(player2Score.getText()) + 100;
                setPlayer2Score(player2ScoreValue);
                updateScoreInDatabase(userNamePlayer2.getText(), player2ScoreValue);

                if (!isX) {
                    displayEndGameAlertWinP2('w');
                } else {
                    displayEndGameAlertLoseP2('l');
                }

            } else if (winner == 3) {
                new EndGameAlert('e', stage, this).show();
            }

            return;
        }
        timer.cancel();
        timer.startTimer(5, isX);
    }

    private void displayEndGameAlertWinP1(char result) {
        new EndGameAlert(result, stage, this, player1ScoreValue).show();
    }

    private void displayEndGameAlertLoseP1(char result) {
        new EndGameAlert(result, stage, this, player1ScoreValue).show();
    }

    private void displayEndGameAlertWinP2(char result) {
        new EndGameAlert(result, stage, this, player2ScoreValue).show();
    }

    private void displayEndGameAlertLoseP2(char result) {
        new EndGameAlert(result, stage, this, player2ScoreValue).show();
    }

    private void updateScoreInDatabase(String playerName, int score) {
        String updateScoreRequest = ToJesonUseCase.toJsonScoreUpdate(playerName, score);
        if (!repo.updateScore(updateScoreRequest)) {
            System.err.println("Failed to update " + playerName + "score");
        }
    }
}
