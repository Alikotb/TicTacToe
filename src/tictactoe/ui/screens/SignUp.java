package tictactoe.ui.screens;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignUp extends BorderPane {

    protected final FlowPane flowPane;
    protected final TextField userNameTextField;
    protected final TextField emailTextFeild;
    protected final TextField passwordTextFeild;
    protected final TextField confirmPasswordTextFeild;
    protected final Label errorLable;
    protected final Button signUpButton;
    protected final HBox hBox;
    protected final Label label;
    protected final Button LogInButton;
    protected final FlowPane flowPane0;
    protected final Button backButton;
    protected final ImageView imageView;

    public SignUp(Stage stage) {
        this.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
        flowPane = new FlowPane();
        userNameTextField = new TextField();
        emailTextFeild = new TextField();
        passwordTextFeild = new TextField();
        confirmPasswordTextFeild = new TextField();
        errorLable = new Label();
        signUpButton = new Button();
        hBox = new HBox();
        label = new Label();
        LogInButton = new Button();
        flowPane0 = new FlowPane();
        backButton = new Button();
        imageView = new ImageView();

        setMaxHeight(600.0);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(800.0);

        BorderPane.setAlignment(flowPane, javafx.geometry.Pos.CENTER);
        flowPane.setColumnHalignment(javafx.geometry.HPos.CENTER);
        flowPane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        flowPane.setPrefWidth(200.0);
        
        userNameTextField.setPrefHeight(60.0);
        userNameTextField.setPrefWidth(480.0);
        userNameTextField.setPromptText("Username");
        FlowPane.setMargin(userNameTextField, new Insets(20.0, 160.0, 0.0, 160.0));
        userNameTextField.setFont(new Font(25.0));

        emailTextFeild.setPrefHeight(60.0);
        emailTextFeild.setPrefWidth(480.0);
        emailTextFeild.setPromptText("Email");
        FlowPane.setMargin(emailTextFeild, new Insets(20.0, 160.0, 0.0, 160.0));
        emailTextFeild.setFont(new Font(25.0));

        passwordTextFeild.setPrefHeight(60.0);
        passwordTextFeild.setPrefWidth(480.0);
        passwordTextFeild.setPromptText("Password");
        FlowPane.setMargin(passwordTextFeild, new Insets(20.0, 160.0, 0.0, 160.0));
        passwordTextFeild.setFont(new Font(25.0));

        confirmPasswordTextFeild.setPrefHeight(60.0);
        confirmPasswordTextFeild.setPrefWidth(480.0);
        confirmPasswordTextFeild.setPromptText("Confirm password");
        FlowPane.setMargin(confirmPasswordTextFeild, new Insets(20.0, 160.0, 0.0, 160.0));
        confirmPasswordTextFeild.setFont(new Font(25.0));

        errorLable.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        errorLable.setTextFill(javafx.scene.paint.Color.valueOf("#ff4c4c"));
        FlowPane.setMargin(errorLable, new Insets(8.0, 0.0, 0.0, 0.0));

        signUpButton.setMnemonicParsing(false);
        signUpButton.setPrefHeight(60.0);
        signUpButton.setPrefWidth(200.0);
        signUpButton.setText("SIGN UP");
        FlowPane.setMargin(signUpButton, new Insets(20.0, 0.0, 0.0, 0.0));
        signUpButton.setCursor(Cursor.HAND);
        signUpButton.setOnAction((ActionEvent event) -> {
            Scene scene = new Scene(new LogInBase(stage), 800, 600);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
        });
        

        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setMaxHeight(94.0);
        hBox.setMinHeight(10.0);
        hBox.setPrefHeight(10.0);
        hBox.setPrefWidth(800.0);

        label.setText("Already Have An Account?");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        
        LogInButton.setText(" LOGIN");
        LogInButton.setTextFill(javafx.scene.paint.Color.valueOf("#062D68"));
        LogInButton.setCursor(Cursor.HAND);
        LogInButton.setBackground(null);
        LogInButton.setOnAction((ActionEvent event) -> {
            Scene scene = new Scene(new LogInBase(stage), 800, 600);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
        });
        FlowPane.setMargin(hBox, new Insets(20.0, 0.0, 0.0, 0.0));
        setCenter(flowPane);

        BorderPane.setAlignment(flowPane0, javafx.geometry.Pos.CENTER);
        flowPane0.setMaxHeight(104.0);
        flowPane0.setPrefHeight(132.0);
        flowPane0.setPrefWidth(800.0);

        backButton.setPrefWidth(84.0);
        backButton.setPickOnBounds(true);
        backButton.setGraphic(new ImageView(new Image(("/resources/images/back.png"))));
        backButton.setBackground(null);
        backButton.setOnAction((ActionEvent event) -> {
            Scene scene = (new Scene(new LogInBase(stage), 800, 600));
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
        });
        FlowPane.setMargin(backButton, new Insets(20.0, 0.0, 0.0, 65.0));
        backButton.setCursor(Cursor.HAND);

        imageView.setFitHeight(82.0);
        imageView.setFitWidth(215.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        FlowPane.setMargin(imageView, new Insets(20.0, 0.0, 0.0, 144.0));
        imageView.setImage(new Image(getClass().getResource("/resources/images/logo.png").toExternalForm()));
        setTop(flowPane0);
        
        userNameTextField.setId("username");
        emailTextFeild.setId("email");
        passwordTextFeild.setId("password");
        confirmPasswordTextFeild.setId("confirmPassword");
        signUpButton.setId("signUpButton");
        LogInButton.setId("LogInButton");
        label.setId("theLable");

        flowPane.getChildren().add(userNameTextField);
        flowPane.getChildren().add(emailTextFeild);
        flowPane.getChildren().add(passwordTextFeild);
        flowPane.getChildren().add(confirmPasswordTextFeild);
        flowPane.getChildren().add(errorLable);
        flowPane.getChildren().add(signUpButton);
        hBox.getChildren().add(label);
        hBox.getChildren().add(LogInButton);
        flowPane.getChildren().add(hBox);
        flowPane0.getChildren().add(backButton);
        flowPane0.getChildren().add(imageView);

    }
}
