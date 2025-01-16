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
import javafx.stage.Stage;
import tictactoe.domain.model.Record;
import tictactoe.domain.usecases.RecordingUseCase;
import tictactoe.view.RecordItem;

public class History extends ListView {

    private Gson gson; 
    private static Record gameRecord;
    
    public History(Stage historyStage,Stage stage) {

        setStyle("-fx-background-color: #1F509A; -fx-background-radius: 15;");

        File[] gameFiles = RecordingUseCase.getAllFiles();
        ObservableList<RecordItem> records = FXCollections.observableArrayList();

        
        if (gameFiles != null&& gameFiles.length > 0) {
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
            System.err.println("gameFiles is null.");
        }

        getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    RecordItem currentUser = (RecordItem) newValue;
                    historyStage.close();
                    Scene scene = new Scene(new ReplayGame(stage,currentUser.getRecord()), 800, 600);
                    stage.setScene(scene);
                }
            }
        });

        setCellFactory(value -> new ListCell<RecordItem>() {
            @Override
            protected void updateItem(RecordItem item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    setStyle("-fx-background-color: #fff;");
                    setGraphic(item);

                } else {
                    setStyle("-fx-background-color: #1F509A;");
                    setGraphic(null);
                }
            }

        });

        setPrefHeight(500.0);
        setPrefWidth(350.0);
        setItems(records);
    }
}
