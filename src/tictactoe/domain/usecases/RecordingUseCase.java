/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.domain.usecases;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe.domain.model.Record;

/**
 *
 * @author LeaderTech
 */
public class RecordingUseCase {

    public static String Pos = "";
    private static final String directoryPath = System.getenv("LOCALAPPDATA") + File.separator + "XOGame";
 public static void saveToFile(String actions, String userName1, String userName2, char win) {
     //System.getenv("APPDATA");
        File directory = new File(directoryPath);
        Date date = new Date();
        String filePath = directoryPath + File.separator + date.getTime() + ".json";
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directoryPath);
            } else {
                System.err.println("Failed to create directory: " + directoryPath);
                return;
            }
        }
        Record r = new Record();
        r.setPsitions(actions);
        r.setUser1(userName1);
        r.setUser2(userName2);
        r.setWinner(win);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(gson.toJson(r));
            writer.close();
        }
        catch (IOException ex) {
            Logger.getLogger(RecordingUseCase.class.getName()).log(Level.SEVERE, null, ex);
        }        finally{
            RecordingUseCase.Pos="";
        }
    }
    public static File[] getAllFiles() {
        File[] txtFiles = null;
        File dir = new File(directoryPath);
        if (dir.exists() && dir.isDirectory()) {
            FilenameFilter txtFilter = (file, name) -> name.toLowerCase().endsWith(".json");
            txtFiles = dir.listFiles(txtFilter);
        } else {
            System.out.println("The specified path is not a valid directory.");
        }
        return txtFiles;
    }

}
