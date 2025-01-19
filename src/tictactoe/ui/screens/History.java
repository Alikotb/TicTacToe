package tictactoe.ui.screens;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tictactoe.domain.model.Record;
import tictactoe.domain.usecases.RecordingUseCase;
import tictactoe.ui.view.RecordItem;

public class History extends ListView {

    private Gson gson;
    private static Record gameRecord;

    public History(Stage historyStage, Stage mainStage) {

        setStyle("-fx-background-color: #1F509A; -fx-background-radius: 15;");

        File[] gameFiles = RecordingUseCase.getAllFiles();
        ObservableList<RecordItem> records = FXCollections.observableArrayList();

        if (gameFiles != null && gameFiles.length > 0) {
            for (File file : gameFiles) {
                try {
                    FileReader reader = new FileReader(file);
                    gson = new GsonBuilder().create();
                    gameRecord = gson.fromJson(reader, Record.class);

                    records.add(new RecordItem(gameRecord));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("gameFiles is null.");
        }
        setSelectionListener(historyStage, mainStage);
        setCellFactory();
        setPrefDimensions();
        setItems(records);
    }

    public History(Stage historyStage, Stage stage, String username, int socre) {
        setStyle("-fx-background-color: #1F509A; -fx-background-radius: 15;");

        File[] gameFiles = RecordingUseCase.getAllFilesoLINE(username);
        ObservableList<RecordItem> records = FXCollections.observableArrayList();

        if (gameFiles != null && gameFiles.length > 0) {
            for (File file : gameFiles) {
                try {
                    FileReader reader = new FileReader(file);
                    gson = new GsonBuilder().create();
                    gameRecord = gson.fromJson(reader, Record.class);

                    records.add(new RecordItem(gameRecord));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("gameFiles is null.");
        }
        setSelectionListener(historyStage, stage, username, socre);
        setCellFactory();
        setPrefDimensions();
        setItems(records);
    }

    private void setSelectionListener(Stage historyStage, Stage stage) {
        getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    RecordItem currentUser = (RecordItem) newValue;
                    historyStage.close();
                    Scene scene = new Scene(new ReplayGame(stage, currentUser.getRecord()), 800, 600);
                    stage.setScene(scene);
                }
            }
        });
    }

    private void setSelectionListener(Stage historyStage, Stage stage, String username, int socre) {
        getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    RecordItem currentUser = (RecordItem) newValue;
                    historyStage.close();
                    Scene scene = new Scene(new ReplayGame(stage, currentUser.getRecord(), username, socre), 800, 600);
                    stage.setScene(scene);
                }
            }
        });
    }

    private void setCellFactory() {
        setCellFactory(value -> new ListCell<RecordItem>() {
            @Override
            protected void updateItem(RecordItem item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    VBox container = new VBox();
                    container.setPrefWidth(300.0);
                    container.getChildren().add(item);
                    container.setStyle("-fx-padding: 10px; -fx-background-color: #fff; -fx-background-radius: 25;");
                    setGraphic(container);

                } else {
                    setStyle("-fx-background-color: #1F509A;");
                    setGraphic(null);
                }

            }
        }
        );
    }

    private void setPrefDimensions() {
        setPrefHeight(500.0);
        setPrefWidth(700.0);
    }

}
