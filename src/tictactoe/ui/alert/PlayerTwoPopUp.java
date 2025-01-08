package tictactoe.ui.alert;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tictactoe.ui.screens.Board;

public class PlayerTwoPopUp extends FlowPane {

    protected final Label userName1Label;
    protected final TextField userName1TextField;
    protected final Label userName2Label;
    protected final TextField userName2TextField;
    protected final FlowPane buttonsFlowPane;
    protected final Button OkBtn;
    protected final Button closeBtn;

    public PlayerTwoPopUp(Stage stage, Board board) {

        userName1Label = new Label();
        userName1TextField = new TextField();
        userName2Label = new Label();
        userName2TextField = new TextField();
        buttonsFlowPane = new FlowPane();
        OkBtn = new Button();
        closeBtn = new Button();

        setAlignment(javafx.geometry.Pos.CENTER);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setOrientation(javafx.geometry.Orientation.VERTICAL);
        setPrefHeight(350.0);
        setPrefWidth(412.0);

        userName1Label.setText("Enter Player1:");
        userName1Label.setFont(new Font(25.0));
        userName1Label.setPadding(new Insets(8.0, 0.0, 0.0, 16.0));

        userName1TextField.setPrefHeight(60.0);
        userName1TextField.setPrefWidth(380.0);
        userName1TextField.setPromptText("Player1");
        userName1TextField.setFont(new Font(20.0));
        FlowPane.setMargin(userName1TextField, new Insets(8.0, 16.0, 16.0, 16.0));

        userName2Label.setText("Enter Player2:");
        userName2Label.setFont(new Font(25.0));
        userName2Label.setPadding(new Insets(8.0, 0.0, 0.0, 16.0));

        userName2TextField.setPrefHeight(60.0);
        userName2TextField.setPrefWidth(380.0);
        userName2TextField.setPromptText("Player2");
        userName2TextField.setFont(new Font(20.0));
        FlowPane.setMargin(userName2TextField, new Insets(8.0, 16.0, 0.0, 16.0));

        buttonsFlowPane.setAlignment(javafx.geometry.Pos.CENTER);
        buttonsFlowPane.setHgap(100.0);
        buttonsFlowPane.setPrefWidth(200.0);

        OkBtn.setMnemonicParsing(false);
        OkBtn.setText("OK");
        OkBtn.setOnAction(e -> {
            String userName1 = userName1TextField.getText().trim();
            String userName2 = userName2TextField.getText().trim();

            board.setUserNamePlayer1(userName1.isEmpty() ? "Player1" : userName1);
            board.setUserNamePlayer2(userName2.isEmpty() ? "Player2" : userName2);
            board.setPlayer1Score("0");
            board.setPlayer2Score("0");
            stage.close();
        });
        FlowPane.setMargin(OkBtn, new Insets(0.0));

        closeBtn.setMnemonicParsing(false);
        closeBtn.setText("close");
        closeBtn.setOnAction(e -> {
            board.setUserNamePlayer1("Player1");
            board.setUserNamePlayer2("Player2");
            stage.close();
        });
        FlowPane.setMargin(closeBtn, new Insets(0.0));
        FlowPane.setMargin(buttonsFlowPane, new Insets(8.0, 0.0, 0.0, 0.0));

        getChildren().add(userName1Label);
        getChildren().add(userName1TextField);
        getChildren().add(userName2Label);
        getChildren().add(userName2TextField);
        buttonsFlowPane.getChildren().add(OkBtn);
        buttonsFlowPane.getChildren().add(closeBtn);
        getChildren().add(buttonsFlowPane);

    }
}
