package tictactoe.ui.screens;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Home extends BorderPane {

    protected final Button offlineButton;
    protected final Button onlineButton;
    protected final FlowPane flowPane;
    protected final ImageView imageView;

    public Home(Stage stage) {
        offlineButton = new Button();
        onlineButton = new Button();
        flowPane = new FlowPane();
        imageView = new ImageView();

        this.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
        setPrefHeight(600.0);
        setPrefWidth(800.0);


        offlineButton.setPrefWidth(490);
        offlineButton.setPrefHeight(90);
        offlineButton.setMnemonicParsing(false);
        offlineButton.setText("OFFLINE");

        offlineButton.setOnAction((ActionEvent event) -> {
            Scene scene = new Scene(new OfflineBase(stage), 800, 600);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());

        });

        FlowPane.setMargin(offlineButton, new Insets(50.0, 0.0, 0.0, 155.0));
        onlineButton.setPrefWidth(490);
        onlineButton.setPrefHeight(90);
        onlineButton.setMnemonicParsing(false);
        onlineButton.setText("ONLINE");
        onlineButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        onlineButton.setOnAction((ActionEvent event) -> {
            Scene scene = new Scene(new LogInBase(stage), 800, 600);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());

        });

        FlowPane.setMargin(onlineButton, new Insets(50.0, 0.0, 0.0, 155.0));

        onlineButton.setFont(new Font("System Bold", 20.0));

        BorderPane.setAlignment(flowPane, javafx.geometry.Pos.CENTER);
        flowPane.setPrefHeight(600.0);
        flowPane.setPrefWidth(800.0);

        imageView.setFitHeight(118.0);
        imageView.setFitWidth(308.0);
        imageView.setImage(new Image(getClass().getResource("/resources/images/logo.png").toExternalForm()));
        FlowPane.setMargin(imageView, new Insets(70.0, 0.0, 0.0, 246.0));
        setTop(flowPane);

        onlineButton.getStyleClass().add("homeBtn");
        offlineButton.getStyleClass().add("homeBtn");

        flowPane.getChildren().add(imageView);
        flowPane.getChildren().add(offlineButton);
        flowPane.getChildren().add(onlineButton);

    }
}
