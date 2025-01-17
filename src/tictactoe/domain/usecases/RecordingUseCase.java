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
    //the path in windows   C:\Users\pc"user"\AppData\Local\XOGame
    //the path in MAC   /Users/username/Library/Application Support/XOGame
    private static String pathDir;
    private static final String PATH_WINDOWS = System.getenv("LOCALAPPDATA") + File.separator + "XOGame";
    ;
    private static final String PATH_MAC = System.getProperty("user.home") + File.separator + "Library" + File.separator + "Application Support" + File.separator + "XOGame";

    public static void saveToFile(String actions, String userName1, String userName2, char win) {
        setPath();
        //System.getenv("APPDATA");
        File directory = new File(setPath());
        Date date = new Date();
        String filePath = setPath() + File.separator + date.getTime() + ".json";
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + setPath());
            } else {
                System.err.println("Failed to create directory: " + setPath());
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
        } catch (IOException ex) {
            Logger.getLogger(RecordingUseCase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RecordingUseCase.Pos = "";
        }
    }

    public static File[] getAllFiles() {
        File[] txtFiles = null;
        File dir = new File(setPath());
        if (dir.exists() && dir.isDirectory()) {
            FilenameFilter txtFilter = (file, name) -> name.toLowerCase().endsWith(".json");
            txtFiles = dir.listFiles(txtFilter);
        } else {
            System.out.println("The specified path is not a valid directory.");
        }
        return txtFiles;
    }

    ////////for online  game
    public static void saveToFileOnline(String user, String actions, String userName1, String userName2, char win) {
        //System.getenv("APPDATA");
        String onlineDir = setPath() + File.separator + user;
        File directory = new File(onlineDir);
        Date date = new Date();
        String filePath = onlineDir + File.separator + date.getTime() + ".json";
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + onlineDir);
            } else {
                System.err.println("Failed to create directory: " + onlineDir);
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
        } catch (IOException ex) {
            Logger.getLogger(RecordingUseCase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RecordingUseCase.Pos = "";
        }
    }

    public static File[] getAllFilesoLINE(String username) {
        String onlineDir = setPath() + File.separator + username;
        File[] txtFiles = null;

        File dir = new File(onlineDir);
        if (dir.exists() && dir.isDirectory()) {
            FilenameFilter txtFilter = (file, name) -> name.toLowerCase().endsWith(".json");
            txtFiles = dir.listFiles(txtFilter);
        } else {
            System.out.println("The specified path is not a valid directory.");
        }
        return txtFiles;
    }

    private static String setPath() {
        if (System.getProperty("os.name").startsWith("Windows")) {
            pathDir = PATH_WINDOWS;
        } else if (System.getProperty("os.name").startsWith("Mac")) {
            pathDir = PATH_MAC;
        } else {
            pathDir = null;
        }
        return pathDir;
    }

}
