package tictactoe.ui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class OfflineBase extends BorderPane {

    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final RowConstraints rowConstraints3;
    protected final ImageView imageView;
    protected final Button back;
    protected final Button PlayVsComp;
    protected final Button PlayWithFriend;
    protected final Button History;
    protected Stage mystage;

    public OfflineBase(Stage mystage) {
        this.mystage = mystage;
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        rowConstraints3 = new RowConstraints();
        mystage.setResizable(false);

        imageView = new ImageView();
        back = new Button();
        PlayVsComp = new Button();
        PlayWithFriend = new Button();
        History = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(800.0);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        this.getStyleClass().add("border-pane");

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        rowConstraints.setMaxHeight(118.0);
        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(67.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMaxHeight(149.0);
        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(150.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMaxHeight(137.0);
        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(122.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(122.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints3.setMinHeight(122.0);
        rowConstraints3.setPrefHeight(30.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setRowIndex(imageView, 1);
        imageView.setFitHeight(118.0);
        imageView.setFitWidth(308.0);
        imageView.setImage(new Image(getClass().getResource("/resources/images/logo.png").toExternalForm()));
        GridPane.setMargin(imageView, new Insets(0.0, 0.0, 0.0, 246.0));

        back.setMnemonicParsing(false);
        back.setPrefHeight(80.0);
        back.setPrefWidth(80.0);
        back.setText("");
        ImageView imageView0 = new ImageView(new Image(getClass().getResource("/resources/images/back.png").toExternalForm()));
        imageView0.setFitWidth(75);
        imageView0.setFitHeight(75);
        back.setGraphic(imageView0);
        back.setId("back");
        back.setOnAction((ActionEvent event) -> {
            Scene scene = new Scene(new Home(mystage), 800, 600);
            mystage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
        });

        // back.setBackground(null);
        GridPane.setMargin(back, new Insets(40.0, 0.0, 0.0, 37.0));

        GridPane.setRowIndex(PlayVsComp, 2);
        PlayVsComp.setMnemonicParsing(false);
        PlayVsComp.setPrefHeight(90.0);
        PlayVsComp.setPrefWidth(480.0);
        PlayVsComp.setText("Play Vs PC");
        PlayVsComp.setId("PlayVsComp");
        PlayVsComp.setOnAction(e -> {mystage.setScene(new Scene(new Board(Board.MODE_PC)));});

        GridPane.setMargin(PlayVsComp, new Insets(0.0, 0.0, 0.0, 160.0));

        GridPane.setRowIndex(PlayWithFriend, 3);
        PlayWithFriend.setMnemonicParsing(false);
        PlayWithFriend.setPrefHeight(90.0);
        PlayWithFriend.setPrefWidth(480.0);
        PlayWithFriend.setText("Play With a Friend");
        PlayWithFriend.setId("PlayWithFriend");

        GridPane.setMargin(PlayWithFriend, new Insets(0.0, 0.0, 0.0, 160.0));

        GridPane.setRowIndex(History, 4);
        History.setMnemonicParsing(false);
        History.setPrefHeight(90.0);
        History.setPrefWidth(480.0);
        History.setText(" History");
        History.setId("History");

        GridPane.setMargin(History, new Insets(0.0, 0.0, 0.0, 160.0));
        setCenter(gridPane);

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        gridPane.getRowConstraints().add(rowConstraints3);
        gridPane.getChildren().add(imageView);
        gridPane.getChildren().add(back);
        gridPane.getChildren().add(PlayVsComp);
        gridPane.getChildren().add(PlayWithFriend);
        gridPane.getChildren().add(History);
        gridPane.setAlignment(Pos.CENTER);
        //.setAlignment(Pos.CENTER);

    }
}
