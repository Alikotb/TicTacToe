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

    public boolean sendInvitation(String json) {
        return service.sendInvitation(json);
    }

    public boolean sendMove(String json) {
        return service.login(json);
    }

    public boolean logout(String json) {
        return service.logout(json);
    }
    
     public boolean updateScore(String json) {
        return service.updateScore(json);
    }
     public boolean updateIsAvailable(String json) {
        return service.updateIsAvailable(json);
    }
}
