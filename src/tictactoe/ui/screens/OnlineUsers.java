package tictactoe.ui.screens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tictactoe.data.repository.Repo;
import tictactoe.domain.model.User;
import tictactoe.domain.usecases.RandomAvatarUseCase;
import tictactoe.domain.usecases.ToJsonUseCase;

public class OnlineUsers extends BorderPane {

    private ObservableList<HBox> userList;
    private static ArrayList<User> users = new ArrayList();
    private String username;
    private int score;
    private final RandomAvatarUseCase randomAvatar;
    private final Font font = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/MyCustomFont.ttf"), 25.0);

    public OnlineUsers(Stage onlineStage, Stage mainStage, String name, int sc) {
        username = name;
        score = sc;
        this.randomAvatar = new RandomAvatarUseCase();
        userList = FXCollections.observableArrayList();
        ListView<HBox> listView = new ListView<>(userList);
        Rectangle clip = new Rectangle(0, 0, 480, 520);
        clip.setArcWidth(25);
        clip.setArcHeight(25);
        listView.setClip(clip);

        listView.setCellFactory(param -> new ListCell<HBox>() {
            protected void updateItem(HBox item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    VBox container = new VBox();
                    container.setPrefWidth(150.0);
                    container.getChildren().add(item);
                    container.setStyle("-fx-padding: 5; -fx-background-color: #fff; -fx-background-radius: 25;");
                    setGraphic(container);
                } else {
                    setStyle("-fx-background-color: #1F509A;");
                    setGraphic(null);
                }
            }
        });
        showAvilableUsers(users);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    HBox selectedUser = (HBox) newValue;
                    VBox userInfo = (VBox) selectedUser.getChildren().get(1);
                    Label userNameLabel = (Label) userInfo.getChildren().get(0);
                    Label scoreLabel = (Label) userInfo.getChildren().get(1);
                    String player2 = userNameLabel.getText();
                    onlineStage.close();
                  
                    String json = ToJsonUseCase.toJson(
                            4,
                            username,
                            player2,
                            score, Integer.valueOf(scoreLabel.getText()),
                            1
                    );

                    new Repo().sendInvitation(json);

                }
            }
        });
        this.setStyle("-fx-background-color: #1F509A; -fx-border-color: #1F509A; -fx-border-width: 7; -fx-background-radius: 25;");

        Rectangle borderPaneClip = new Rectangle();
        borderPaneClip.setWidth(480);
        borderPaneClip.setHeight(520);
        borderPaneClip.setArcWidth(25);
        borderPaneClip.setArcHeight(25);
        this.setClip(borderPaneClip);

        this.setCenter(listView);

        onlineStage.setResizable(false);
        onlineStage.initStyle(StageStyle.UTILITY);
        onlineStage.initModality(Modality.APPLICATION_MODAL);
        onlineStage.initOwner(mainStage);
        onlineStage.setX(mainStage.getX() + (mainStage.getWidth() / 2) - 250);
        onlineStage.setY(mainStage.getY() + (mainStage.getHeight() / 2) - 250);
        onlineStage.setOnCloseRequest(event -> onlineStage.close());
    }

    private void addUser(String username, int score, String status) {
        HBox userBox = createUserBox(username, score, status);
        userList.add(userBox);
    }

    private void removeUser(String username) {
        userList.removeIf(userBox -> {
            Label userNameLabel = (Label) ((VBox) userBox.getChildren().get(1)).getChildren().get(0);
            return userNameLabel.getText().equals(username);
        });
    }

    private HBox createUserBox(String username, int score, String status) {
        Image avatarImage = randomAvatar.getRandomAvatar();
        ImageView avatar = new ImageView(avatarImage);
        avatar.setFitWidth(80);
        avatar.setFitHeight(80);
        Circle mask = new Circle(40, 40, 40);
        avatar.setClip(mask);

        VBox userInfo = new VBox(2);
        userInfo.setPrefWidth(150);
        Label userNameLabel = new Label(username);
        userNameLabel.setFont(font);
        userNameLabel.setStyle("-fx-padding: 5; -fx-text-fill: #1F509A;");

        Label scoreLabel = new Label("" + score);
        scoreLabel.setFont(font);
        scoreLabel.setStyle("-fx-padding: 5; -fx-text-fill: #1F509A;");
        userInfo.getChildren().addAll(userNameLabel, scoreLabel);

        Label statusLabel = new Label(status);
        statusLabel.setFont(font);
        statusLabel.setStyle("-fx-alignment: center-right; -fx-padding: 0 0 0 10;");

        if ("in-game".equals(status)) {
            statusLabel.setStyle(statusLabel.getStyle() + "-fx-text-fill: red;");
        } else {
            statusLabel.setStyle(statusLabel.getStyle() + "-fx-text-fill: green;");
        }

        HBox userBox = new HBox(25, avatar, userInfo, statusLabel);
        userBox.setStyle("-fx-border-width: 1;");
        return userBox;
    }
  
    private void showAvilableUsers(ArrayList<User> users) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                continue;
            }
            addUser(u.getUsername(), u.getScore(), "Available");
        }
    }

    public static void setUsers(ArrayList<User> users) {
        OnlineUsers.users = users;
    }

}
