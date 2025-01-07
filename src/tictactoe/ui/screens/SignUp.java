package tictactoe.ui.screens;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignUp extends BorderPane {

    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final TextField username;
    protected final TextField email;
    protected final TextField password;
    protected final TextField confirmPassword;
    protected final VBox vBox;
    protected final Button signUpButton;
    protected final FlowPane flowPane;
    protected final Text text;
    protected final Button LogInButton;
    protected final FlowPane flowPane0;
    protected final Button backButton;
    protected final ImageView backIcon;
    protected final ImageView imageView;

    public SignUp(Stage stage) {

        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        username = new TextField();
        email = new TextField();
        password = new TextField();
        confirmPassword = new TextField();
        vBox = new VBox();
        signUpButton = new Button();
        flowPane = new FlowPane();
        text = new Text();
        LogInButton = new Button();
        flowPane0 = new FlowPane();
        backButton = new Button();
        backIcon = new ImageView();
        imageView = new ImageView();


        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        rowConstraints.setPrefHeight(20.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setPrefHeight(20.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setPrefHeight(20.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setPrefHeight(20.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        username.setPromptText("Username");
        username.setOpaqueInsets(new Insets(0.0));
        GridPane.setMargin(username, new Insets(0.0, 150.0, 0.0, 150.0));

        GridPane.setRowIndex(email, 1);
        email.setPromptText("Email");
        GridPane.setMargin(email, new Insets(0.0, 150.0, 0.0, 150.0));

        GridPane.setRowIndex(password, 2);
        password.setPromptText("Password");
        GridPane.setMargin(password, new Insets(0.0, 150.0, 0.0, 150.0));

        GridPane.setRowIndex(confirmPassword, 3);
        confirmPassword.setPromptText("Confirm password");
        GridPane.setMargin(confirmPassword, new Insets(0.0, 150.0, 0.0, 150.0));
        setCenter(gridPane);

        BorderPane.setAlignment(vBox, javafx.geometry.Pos.CENTER);
        vBox.setPrefHeight(150.0);
        vBox.setPrefWidth(605.0);

        signUpButton.setMnemonicParsing(false);
        signUpButton.setPrefHeight(100.0);
        signUpButton.setPrefWidth(220.0);
        signUpButton.setText("SIGN UP");
        signUpButton.setFont(new Font(16.0));
        signUpButton.setOnAction((ActionEvent event) -> {
            Scene scene = new Scene(new LogInBase(stage), 800, 600);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
        });

        VBox.setMargin(signUpButton, new Insets(20.0, 0.0, 0.0, 280.0));

        flowPane.setPrefHeight(200.0);
        flowPane.setPrefWidth(200.0);
        
        Font font = Font.loadFont(getClass().getResourceAsStream("MyCustomFont.ttf"), 25.0);

        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Already Have An Account?");
        text.setFont(Font.font("Black Han Sans", FontWeight.BOLD, 22));
        text.setFill(Color.WHITE);
        FlowPane.setMargin(text, new Insets(0.0, 0.0, 0.0, 230.0));
        text.getStyleClass().add("mytext");

        LogInButton.setMnemonicParsing(false);
        LogInButton.setText("LOGIN");
        LogInButton.setOnAction((ActionEvent event) -> {
            Scene scene = new Scene(new LogInBase(stage), 800, 600);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
        });

        FlowPane.setMargin(LogInButton, new Insets(0.0, 0.0, 0.0, 0.0));
        VBox.setMargin(flowPane, new Insets(0.0));
        setBottom(vBox);
        flowPane0.setMargin(backButton, new Insets(0.0, 0.0, 0.0, 20.0));
        BorderPane.setAlignment(flowPane0, javafx.geometry.Pos.CENTER);
        flowPane0.setPrefHeight(150.0);
        flowPane0.setPrefWidth(600.0);

        backButton.setMnemonicParsing(false);
        backButton.setPrefHeight(70.0);
        backButton.setPrefWidth(70.0);

        backIcon.setDisable(true);
        backIcon.setFitHeight(70.0);
        backIcon.setFitWidth(70.0);
        backIcon.setImage(new Image(getClass().getResource("/resources/images/back.png").toExternalForm()));
        backButton.setGraphic(backIcon);
        backButton.setOnAction((ActionEvent event) -> {
            Scene scene = (new Scene(new LogInBase(stage), 800, 600));
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
        });

        imageView.setFitHeight(100.0);
        imageView.setFitWidth(230.0);
        imageView.setImage(new Image(getClass().getResource("/resources/images/logo.png").toExternalForm()));
        FlowPane.setMargin(imageView, new Insets(50.0, 10.0, 0.0, 180.0));
        setTop(flowPane0);

        username.setId("username");
        email.setId("email");
        password.setId("password");
        confirmPassword.setId("confirmPassword");
        signUpButton.setId("signUpButton");
        LogInButton.setId("LogInButton");
        imageView.setId("logoImage ");
        backButton.setId("backButton");

        this.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        gridPane.getChildren().add(username);
        gridPane.getChildren().add(email);
        gridPane.getChildren().add(password);
        gridPane.getChildren().add(confirmPassword);
        vBox.getChildren().add(signUpButton);
        flowPane.getChildren().add(text);
        flowPane.getChildren().add(LogInButton);
        vBox.getChildren().add(flowPane);
        flowPane0.getChildren().add(backButton);
        flowPane0.getChildren().add(imageView);

    }
}
