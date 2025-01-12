package tictactoe.data.repository;

import tictactoe.data.network.NetworkService;

public class Repo {

    private final NetworkService service = new NetworkService();

    public boolean signUp(String json) {
        return service.signUp(json);
    }

    public boolean login(String json) {
        return service.login(json);
    }

    public boolean getOnlineUsers(String json) {
        return service.getOnlineUsers(json);
    }

    public boolean sendRequest(String json) {
        return service.sendRequest(json);
    }

    public boolean sendMove(String json) {
        return service.login(json);
    }

    public boolean logout(String json) {
        return service.logout(json);
    }
}
