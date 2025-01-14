package tictactoe.domain.usecases;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

public class PlaySoundUseCase {

    private static final File O_SOUND = new File("src/resources/sounds/oTone.wav");
    private static final File X_SOUND = new File("src/resources/sounds/xTone.wav");
    private static final File TIMER_SOUND = new File("src/resources/sounds/timer.wav");
    private static final File WIN_SOUND = new File("src/resources/sounds/win2.wav");
    private static final File DRAW_SOUND = new File("src/resources/sounds/draw.wav");
    private static final File LOSE_SOUND = new File("src/resources/sounds/lose.wav");

    private final Map<Integer, Clip> soundClips = new HashMap<>();

    public PlaySoundUseCase() {
        preloads();
    }

    private void preloads() {
        preloadSound(1, O_SOUND);
        preloadSound(2, X_SOUND);
        preloadSound(3, TIMER_SOUND);
        preloadSound(4, WIN_SOUND);
        preloadSound(5, DRAW_SOUND);
        preloadSound(6, LOSE_SOUND);
    }

    private void preloadSound(int soundNum, File soundFile) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            soundClips.put(soundNum, clip);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            Logger.getLogger(PlaySoundUseCase.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Failed to preload sound: " + soundFile.getPath());
        }
    }

    public void playSound(int soundNum) {
        Clip clip = soundClips.get(soundNum);
        if (clip != null) {
            new Thread(() -> {
                if (clip.isRunning()) {
                    clip.stop();
                }
                clip.setFramePosition(0); 
                clip.start();
            }).start();
        } else {
            System.err.println("Invalid sound number: " + soundNum);
        }
    }

    public void stopSound(int soundNum) {
        Clip clip = soundClips.get(soundNum);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void closeAll() {
        soundClips.values().forEach(clip -> {
            if (clip.isOpen()) {
                clip.close();
            }
        });
        soundClips.clear();
    }
}
