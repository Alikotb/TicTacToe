package tictactoe.ui.alert;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tictactoe.ui.screens.Home;

public class ConnectionLostPopup extends FlowPane {

    protected final ImageView lostConnectionImgView;
    protected final Label label;
    protected final Button goHomeBtn;

    public ConnectionLostPopup(Stage stage, Stage connectionLostStage) {

        lostConnectionImgView = new ImageView();
        label = new Label();
        goHomeBtn = new Button();

        setAlignment(javafx.geometry.Pos.CENTER);
        setColumnHalignment(javafx.geometry.HPos.CENTER);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setOrientation(javafx.geometry.Orientation.VERTICAL);
        setPrefHeight(300.0);
        setPrefWidth(300.0);

        lostConnectionImgView.setFitHeight(150.0);
        lostConnectionImgView.setFitWidth(200.0);
        lostConnectionImgView.setPickOnBounds(true);
        lostConnectionImgView.setPreserveRatio(true);
        lostConnectionImgView.setImage(new Image("/resources/images/lost-connection.png"));
        FlowPane.setMargin(lostConnectionImgView, new Insets(0.0, 0.0, 25.0, 25.0));

        label.setText("Connection Lost");
        label.setFont(new Font("System Bold", 18.0));
        FlowPane.setMargin(label, new Insets(0.0, 0.0, 25.0, 0.0));

        goHomeBtn.setAlignment(javafx.geometry.Pos.CENTER);
        goHomeBtn.setMnemonicParsing(false);
        goHomeBtn.setText("Go Home");
        goHomeBtn.setOnAction((e) -> {
            stage.setScene(new Scene(new Home(stage)));
            connectionLostStage.close();
                });
        FlowPane.setMargin(goHomeBtn, new Insets(0.0));

        getChildren().add(lostConnectionImgView);
        getChildren().add(label);
        getChildren().add(goHomeBtn);

    }
}
