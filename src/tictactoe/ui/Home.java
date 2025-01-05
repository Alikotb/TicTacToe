package tictactoe.ui;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Home extends BorderPane {

    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final Button offlineButton;
    protected final Button onlineButton;
    protected final FlowPane flowPane;
    protected final ImageView imageView;

    public Home(Stage stage) {

        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        offlineButton = new Button();
        onlineButton = new Button();
        flowPane = new FlowPane();
        imageView = new ImageView();

        this.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setPrefHeight(265.0);
        gridPane.setPrefWidth(600.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        rowConstraints.setMinHeight(100.0);
        rowConstraints.setPrefHeight(220.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(100.0);
        rowConstraints0.setPrefHeight(220.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setRowSpan(offlineButton, 2);
        offlineButton.setPrefWidth(490);
        offlineButton.setPrefHeight(100);
        offlineButton.setMnemonicParsing(false);
        offlineButton.setText("OFFLINE");

        offlineButton.setOnAction((ActionEvent event) -> {
            Scene scene = new Scene(new OfflineBase(stage), 800, 600);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());

        });

        GridPane.setMargin(offlineButton, new Insets(0.0, 0.0, 160.0, 150.0));
        GridPane.setRowIndex(onlineButton, 1);
        onlineButton.setPrefWidth(490);
        onlineButton.setPrefHeight(100);
        onlineButton.setMnemonicParsing(false);
        onlineButton.setText("ONLINE");
        onlineButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        onlineButton.setOnAction((ActionEvent event) -> {
            Scene scene = new Scene(new LogInBase(stage), 800, 600);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());

        });
        GridPane.setMargin(onlineButton, new Insets(0.0, 0.0, 60.0, 150.0));
        BorderPane.setMargin(gridPane, new Insets(0.0));
        setCenter(gridPane);
        BorderPane.setAlignment(flowPane, javafx.geometry.Pos.CENTER);
        flowPane.setPrefHeight(73.0);
        flowPane.setPrefWidth(600.0);

        imageView.setFitHeight(118.0);
        imageView.setFitWidth(308.0);
        imageView.setImage(new Image(getClass().getResource("/resources/images/logo.png").toExternalForm()));
        FlowPane.setMargin(imageView, new Insets(70.0, 0.0, 0.0, 246.0));
        setCenter(gridPane);
        setTop(flowPane);

        onlineButton.getStyleClass().add("homeBtn");
        offlineButton.getStyleClass().add("homeBtn");

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getChildren().add(offlineButton);
        gridPane.getChildren().add(onlineButton);
        flowPane.getChildren().add(imageView);

    }
}
