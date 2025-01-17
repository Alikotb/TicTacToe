package tictactoe.data.network;

import java.io.IOException;
import tictactoe.data.ConnectionService;

public class NetworkService {

    private final ConnectionService server = ConnectionService.getInstance();

    public boolean signUp(String json) {
        return sendRequest(json);
    }

    public boolean login(String json) {
        return sendRequest(json);
    }

    public boolean getOnlineUsers(String json) {
        return sendRequest(json);
    }

    public boolean sendInvitation(String json) {
        return sendRequest(json);
    }

    private boolean sendRequest(String json) {
        if (!server.connect()) {
            return false;
        }

        try {
            server.getDos().writeUTF(json);
        } catch (IOException ex) {
            System.err.println("couldn't write to output stream : " + ex.getMessage());
        }
        return true;
    }

    public boolean sendMove(String json) {
        return sendRequest(json);
    }

    public boolean logout(String json) {
        return sendRequest(json);
    }
    
     public boolean updateScore(String json) {
        return sendRequest(json);
    }
}
