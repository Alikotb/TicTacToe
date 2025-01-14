package tictactoe.domain.usecases;

import javax.json.Json;


public class ToJesonUseCase {
      public static String toJson(String username , String password){  
        return Json.createObjectBuilder().add("action", 2).add("username",username).add("password",password).build().toString();
    
    }
    
}
