package tictactoe.ui.alert;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tictactoe.ui.screens.Board;

public class PromptUserName extends FlowPane {

    protected final Button closeBtn;
    protected final Label label;
    protected final TextField userNameTextField;
    protected final FlowPane buttonsFlowPane;
    protected final Button OkBtn;

    public PromptUserName(Stage stage, Board board) {

        closeBtn = new Button();
        label = new Label();
        userNameTextField = new TextField();
        OkBtn = new Button();
        buttonsFlowPane = new FlowPane();
        
        this.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());

        setAlignment(javafx.geometry.Pos.CENTER);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setOrientation(javafx.geometry.Orientation.VERTICAL);
        setPrefHeight(250.0);
        setPrefWidth(412.0);

        closeBtn.setMnemonicParsing(false);
        closeBtn.setText("close");
        closeBtn.setOnAction(e -> {
            board.setUserNamePlayer1("Player1");
            board.setUserNamePlayer2("Pc");
            stage.close();
        });

        FlowPane.setMargin(closeBtn, new Insets(16.0, 0.0, 0.0, 16.0));

        label.setText("Enter Your Name:");
        label.setFont(new Font(25.0));
        label.setPadding(new Insets(8.0, 0.0, 0.0, 16.0));

        userNameTextField.setPrefHeight(60.0);
        userNameTextField.setPrefWidth(380.0);
        userNameTextField.setPromptText("Enter Your Name");
        userNameTextField.setFont(new Font(20.0));
        userNameTextField.setId("userPrombet");
        FlowPane.setMargin(userNameTextField, new Insets(8.0, 16.0, 16.0, 16.0));

        buttonsFlowPane.setAlignment(javafx.geometry.Pos.CENTER);
        buttonsFlowPane.setHgap(100.0);
        buttonsFlowPane.setPrefWidth(350.0);

        OkBtn.setMnemonicParsing(false);
        OkBtn.setText("OK");
        OkBtn.setOnAction(e -> {
            String userName = userNameTextField.getText().trim();
            board.setUserNamePlayer1(userName.isEmpty() ? "Player1" : userName);
            board.setUserNamePlayer2("Pc");
            stage.close();
        });
        FlowPane.setMargin(OkBtn, new Insets(0.0, 0.0, 0.0, 0.0));
        FlowPane.setMargin(buttonsFlowPane, new Insets(8.0, 0.0, 0.0, 0.0));

        OkBtn.setStyle("-fx-background-color:linear-gradient(to bottom, #FF6B6B, #D32F2F, #B71C1C); "
                + "-fx-background-radius: 50px; "
                + "-fx-font-family: 'Black Han Sans'; "
                + "-fx-text-fill: white; "
                + "-fx-font-size: 20px; "
                + "-fx-cursor: hand;");

        closeBtn.setStyle("-fx-background-color:linear-gradient(to bottom, #FF6B6B, #D32F2F, #B71C1C); "
                + "-fx-background-radius: 50px; "
                + "-fx-font-family: 'Black Han Sans'; "
                + "-fx-text-fill: white; "
                + "-fx-font-size: 20px; "
                + "-fx-cursor: hand;");
//        
        OkBtn.setPrefHeight(50);
        OkBtn.setPrefWidth(90);
        closeBtn.setPrefHeight(50);
        closeBtn.setPrefWidth(100);

        getChildren().add(label);
        getChildren().add(userNameTextField);
        getChildren().add(buttonsFlowPane);
        buttonsFlowPane.getChildren().add(OkBtn);
        buttonsFlowPane.getChildren().add(closeBtn);

    }
}
