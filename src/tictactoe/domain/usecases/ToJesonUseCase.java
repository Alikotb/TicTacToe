package tictactoe.domain.usecases;

import javax.json.Json;

public class ToJesonUseCase {

    public static String toJson(String email, String password) {
        return Json.createObjectBuilder().
                add("action", 2)
                .add("email", email)
                .add("password", password)
                .build().
                toString();

    }

   public static String toJsonScoreUpdate(String email, int score) {
    return Json.createObjectBuilder()
            .add("action", 7) 
            .add("username", email)
            .add("score", score)
            .build()
            .toString();
}
    public static String updateIsAvailable(String playerName) {
        return Json.createObjectBuilder()
                .add("action", 8)
                .add("username", playerName)
                .build()
                .toString();
    }

}
