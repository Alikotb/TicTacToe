package tictactoe.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import tictactoe.domain.model.User;
import tictactoe.ui.alert.IncomingRequestDialog;
import tictactoe.ui.screens.OnlineUsers;
import tictactoe.ui.screens.SignUp;
import tictactoe.ui.screens.LogInBase;
import tictactoe.ui.alert.MessagePopup;
import tictactoe.ui.screens.NewGame1Base;
import tictactoe.ui.screens.OnlineBoard;

public class ConnectionService {

    private Socket server;
    private DataInputStream dis;
    private DataOutputStream dos;
    private OnlineBoard board;

    private static final String LOCAL_HOST = "127.0.0.1";
    private static final int PORT = 55555;
    private static final int ACTION_SIGN_UP = 1;
    private static final int ACTION_LOGIN = 2;
    private static final int ACTION_ONLINE_USERS = 3;
    private static final int ACTION_SEND_INVITATION = 4;
    private static final int ACTION_SEND_MOVE = 5;
    private static final int ACTION_LOGOUT = 6;
    private static final int RESPONSE_INCOMING_INVITATION = 1;
    private static final int RESPONSE_ACCEPTED = 2;
    private static final int RESPONSE_DECLINED = 3;
    private static final int RESPONSE_ACCEPT_INVITATION = 4;

    private static volatile ConnectionService instance;
    private Thread th;

    public static boolean running;

    public static ConnectionService getInstance() {
        if (instance == null) {
            instance = new ConnectionService();
        }
        return instance;
    }

    public boolean connect() {

        if (!isConnected()) {
            try {
                server = new Socket(LOCAL_HOST, PORT);
                dis = new DataInputStream(server.getInputStream());
                dos = new DataOutputStream(server.getOutputStream());
                startListening();
                return true;

            } catch (IOException ex) {
                return false;
            }
        }
        return true;
    }

    public void disconnect() {
        NewGame1Base.showConnectionLost();

        try {
            if (server != null) {
                server.close();
            }
            if (dis != null) {
                dis.close();
            }
            if (dos != null) {
                dos.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (th != null && th.isAlive()) {
            th.interrupt();
        }

    }

    public boolean isConnected() {
        return server != null && !server.isClosed();
    }

    public DataInputStream getDis() {
        return dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void startListening() {
        th = new Thread(() -> {

            while (!Thread.interrupted()) {
                handleAction();
            }
        });
        th.setDaemon(true);
        th.start();
    }

    private void handleAction() {
        try {
            int action = 0;
            String json = dis.readUTF();
            JsonObject jsonObj = Json.createReader(new StringReader(json)).readObject();
            action = jsonObj.getInt("action");

            switch (action) {

                case ACTION_SIGN_UP: {
                    SignUp.passtoLogin(jsonObj);
                    break;
                }

                case ACTION_LOGIN: {
                    LogInBase.navigateToNewGame(jsonObj);
                    break;
                }

                case ACTION_ONLINE_USERS: {
                    JsonArray AvailableUsers = jsonObj.getJsonArray("items");
                    ArrayList<User> users = new ArrayList();
                    for (int i = 0; i < AvailableUsers.size(); i++) {
                        User u = new User(AvailableUsers.getJsonObject(i).getString("username"), AvailableUsers.getJsonObject(i).getInt("score"));
                        users.add(u);
                    }
                    OnlineUsers.setUsers(users);
                    break;
                }

                case ACTION_SEND_INVITATION: {
                    handleSendInvitationResponse(jsonObj, json);
                    break;
                }

                case ACTION_SEND_MOVE: {
                    handleSendMove(json, jsonObj);
                    break;
                }

                case ACTION_LOGOUT: {
                    NewGame1Base.navigateToHome(jsonObj);
                    break;
                }

            }

        } catch (IOException ex) {
            disconnect();

        }

    }

    private void handleSendInvitationResponse(JsonObject jsonObj, String json) {
        int status = jsonObj.getInt("status");

        switch (status) {
            case RESPONSE_INCOMING_INVITATION: {    // 1
                Platform.runLater(() -> {
                    new IncomingRequestDialog().showRequestDialog(new Stage(), jsonObj);
                });

                break;
            }
            case RESPONSE_ACCEPTED: {
                board = null;
                NewGame1Base.navigateToOnlineBoard(jsonObj, true);
                break;
            }

            case RESPONSE_ACCEPT_INVITATION: {
                handleAcceptResponse(jsonObj);
                break;
            }
            case RESPONSE_DECLINED: {
                Platform.runLater(() -> {
                    NewGame1Base.showMessagePopup(jsonObj.getString("username-player2"), " Declined Your Invitation!");
                });

                break;
            }

        }
    }

    private void handleAcceptResponse(JsonObject jsonObj) {
        int code = jsonObj.getInt("code");

        if (code == 1) {
            board = null;
            NewGame1Base.navigateToOnlineBoard(jsonObj, false);
        } else if (code == 2) {
            NewGame1Base.showMessagePopup(jsonObj.getString("username-player1"), " Already in a Game!");
        } else {
            Platform.runLater(() -> {
                NewGame1Base.showMessagePopup(jsonObj.getString("username-player1"), " Has No Connection!");
            });

        }
    }

    private void handleSendMove(String json, JsonObject jsonObj) {
        if (board == null) {
            board = OnlineBoard.board;
        }

        board.nextTurn(jsonObj);
    }
}
