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
import java.util.List;
import java.util.Random;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.json.Json;
import tictactoe.data.repository.Repo;
import tictactoe.domain.model.User;
import tictactoe.ui.alert.IncomingRequestDialog;

public class OnlineUsers extends BorderPane {

    private ObservableList<HBox> userList;
    private final List<Image> avatarImages;
    private final Random random;
    private static ArrayList<User> users = new ArrayList();
    private String username;
    private int score;
    private final Font font = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/MyCustomFont.ttf"), 25.0);

    public OnlineUsers(Stage onlineStage, Stage mainStage, String name, int sc) {
        username = name;
        score = sc;
        random = new Random();
        avatarImages = loadAvatars();
        
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

                    String json = Json.createObjectBuilder()
                            .add("action", 4)
                            .add("username-player1", username)
                            .add("username-player2", player2)
                            .add("score-player1", score)
                            .add("score-player2", Integer.valueOf(scoreLabel.getText()))
                            .add("status", 1) // invite
                            .build().toString();
                    if (new Repo().sendInvitation(json)) {
                        System.out.println("request sent successfully");
                    } else {
                        System.out.println("request not sent");
                    }
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
        Image avatarImage = getRandomAvatar();
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
            statusLabel.setStyle(statusLabel.getStyle()+"-fx-text-fill: red;");
        } else {
            statusLabel.setStyle(statusLabel.getStyle()+"-fx-text-fill: green;");
        }

        HBox userBox = new HBox(25, avatar, userInfo, statusLabel);
        userBox.setStyle("-fx-border-width: 1;");
        return userBox;
    }

    private List<Image> loadAvatars() {
        List<Image> images = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            try {
                String imagePath = String.format("/resources/images/avaters/img%d.jpg", i);
                images.add(new Image(getClass().getResource(imagePath).toExternalForm()));
            } catch (NullPointerException e) {
                System.out.println("Image not found: img" + i + ".jpg");
            }
        }
        return images;
    }

    private Image getRandomAvatar() {
        if (avatarImages.isEmpty()) {
            return null;
        }
        return avatarImages.get(random.nextInt(avatarImages.size()));
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
