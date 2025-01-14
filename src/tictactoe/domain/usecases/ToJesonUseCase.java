package tictactoe.domain.usecases;

import javax.json.Json;


public class ToJesonUseCase {
      public static String toJson(String email , String password){  
        return Json.createObjectBuilder().add("action", 2).add("email",email).add("password",password).build().toString();
    
    }
    
}
