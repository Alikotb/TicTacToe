package tictactoe.ui.screens;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.json.JsonObject;
import tictactoe.data.repository.Repo;
import tictactoe.domain.usecases.HashingUseCase;
import tictactoe.domain.usecases.ToJesonUseCase;
import tictactoe.domain.usecases.ValidationUseCase;

public class LogInBase extends BorderPane {

    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final RowConstraints rowConstraints3;
    protected final ImageView imageView;
    protected final Button back;
    protected final TextField emailTextField;
    protected final PasswordField passwordTextField;
    protected static Label errorLabel;
    protected final Button Login;
    protected final Label label;
    protected final Button Login2;
    protected static Stage mystage;
    protected Repo repo;
    protected ValidationUseCase validator ;

    public LogInBase(Stage mystage) {
        LogInBase.mystage = mystage;
        this.repo = new Repo();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        rowConstraints3 = new RowConstraints();
        imageView = new ImageView();
        back = new Button();
        emailTextField = new TextField();
        passwordTextField = new PasswordField();
        errorLabel = new Label();
        Login = new Button();
        label = new Label();
        Login2 = new Button();
        mystage.setResizable(false);

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

        rowConstraints.setMaxHeight(258.0);
        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(181.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMaxHeight(266.0);
        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(121.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMaxHeight(195.0);
        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(99.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMaxHeight(181.0);
        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(68.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints3.setMaxHeight(111.0);
        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPrefHeight(111.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        imageView.setFitHeight(118.0);
        imageView.setFitWidth(308.0);
        imageView.setImage(new Image(getClass().getResource("/resources/images/logo.png").toExternalForm()));
        GridPane.setMargin(imageView, new Insets(100.0, 0.0, 0.0, 246.0));

        back.setMnemonicParsing(false);
        back.setId("back");
        back.setText("");
         ImageView imageView0 = new ImageView(new Image(getClass().getResource("/resources/images/back.png").toExternalForm()));
        imageView0.setFitWidth(75);
        imageView0.setFitHeight(75);
        back.setGraphic(imageView0);
        back.setOnAction((ActionEvent event) -> {
            Scene scene = new Scene(new Home(mystage), 800, 600);
            mystage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());

        });

        GridPane.setMargin(back, new Insets(0.0, 0.0, 75.0, 37.0));

        GridPane.setRowIndex(emailTextField, 1);
        emailTextField.setMaxHeight(60.0);
        emailTextField.setMaxWidth(480.0);
        emailTextField.setPrefHeight(60.0);
        emailTextField.setPrefWidth(480.0);
        emailTextField.setPromptText("Username");
        emailTextField.setId("UsernameTxt");
        GridPane.setMargin(emailTextField, new Insets(50.0, 0.0, 0.0, 160.0));
        emailTextField.setFont(new Font(25.0));

        GridPane.setRowIndex(passwordTextField, 2);
        passwordTextField.setMaxHeight(60.0);
        passwordTextField.setMaxWidth(480.0);
        passwordTextField.setPromptText("Password");
        passwordTextField.setId("PasswordTxt");
        GridPane.setMargin(passwordTextField, new Insets(0.0, 0.0, 0.0, 160.0));
        passwordTextField.setFont(new Font(25.0));
        
        GridPane.setRowIndex(errorLabel, 3);
        errorLabel.setText("");
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setId("errorLabel");
        GridPane.setMargin(errorLabel, new Insets(5.0, 0.0, 0.0, 160.0));
        

        GridPane.setRowIndex(Login, 4);
        Login.setMaxHeight(80.0);
        Login.setMaxWidth(200.0);
        Login.setMnemonicParsing(false);
        Login.setText("LOGIN");
        Login.setId("Login");
        Login.setOnAction((ActionEvent event) -> {
            validator = new ValidationUseCase();
            String email = emailTextField.getText();
            String hashPassword = HashingUseCase.hashPassword(passwordTextField.getText());
             String validationError = validator.validateFields(emailTextField ,passwordTextField);
             System.err.println(validationError);
            if (validationError!=null){
                errorLabel.setText(validationError);
                return;
            }
            
            String loginRequest = ToJesonUseCase.toJson(email, hashPassword);
            boolean isConnected = repo.login(loginRequest);
            if (!isConnected) {
                errorLabel.setText("Connection faild");
            }
        });
        GridPane.setMargin(Login, new Insets(0.0, 0.0, 0.0, 300.0));

        GridPane.setRowIndex(label, 5);
        label.setPrefHeight(31.0);
        label.setPrefWidth(290.0);
        label.setText("Don't Have Account?");
        label.setId("label");
        GridPane.setMargin(label, new Insets(0.0, 0.0, 0.0, 225.0));

        GridPane.setRowIndex(Login2, 5);
        Login2.setMnemonicParsing(false);
        Login2.setPrefHeight(31.0);
        Login2.setPrefWidth(125.0);
        Login2.setText("SIGN UP");
        Login2.setId("Login2");
        Login2.setOnAction((ActionEvent event) -> {
            Scene scene = new Scene(new SignUp(mystage), 800, 600);
            mystage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/resources/style/style.css").toExternalForm());
        });

        GridPane.setMargin(Login2, new Insets(0.0, 0.0, 0.0, 530.0));
        Login2.setPadding(new Insets(0.0, 15.0, 0.0, 0.0));
        setCenter(gridPane);

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        gridPane.getRowConstraints().add(rowConstraints3);
        gridPane.getChildren().add(imageView);
        gridPane.getChildren().add(back);
        gridPane.getChildren().add(emailTextField);
        gridPane.getChildren().add(passwordTextField);
        gridPane.getChildren().add(errorLabel);
        gridPane.getChildren().add(Login);
        gridPane.getChildren().add(label);
        gridPane.getChildren().add(Login2);

    }
    
    public static void navigateToNewGame(JsonObject jsonObj) {
        String status = jsonObj.getString("status");
        Platform.runLater(() -> {
            if ("success".equals(status)) {
                Scene scene = new Scene(new NewGame1Base(mystage, jsonObj.getString("username"),
                        jsonObj.getInt("score")), 800, 600);
                mystage.setScene(scene);
            } else { //data doesnot exist -> if ("failure".equals(status))
                errorLabel.setText("Invalid username or password");
            }
        });
        
    }
}
