package tictactoe.data.network;

import java.io.IOException;
import tictactoe.data.ConnectionService;

public class NetworkService {

    private final ConnectionService server = ConnectionService.getInstance();

    public boolean signUp(String json) {
        if (!server.connect()) {
            return false;
        }
        // TODO
        return true;
    }

    public boolean login(String json) {
       return sendRequest(json);
    }

    public boolean getOnlineUsers(String json) {
        if (!server.connect()) {
            return false;
        }
        // TODO
        return true;
    }

    public boolean sendRequest(String json) {
        if (!server.connect()) {
            return false;
        }

        try {
            server.getDos().writeUTF(json);
            System.err.println("request send");
        } catch (IOException ex) {
            System.err.println("couldn't write to output stream : " + ex.getMessage());
        }
        return true;
    }

    public boolean sendMove() {
        if (!server.connect()) {
            return false;
        }
        // TODO
        return true;
    }

    public boolean logout(String json) {
        if (!server.connect()) {
            return false;
        }

        // TODO 
        return true;
    }
}
