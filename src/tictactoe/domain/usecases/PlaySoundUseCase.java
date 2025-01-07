package tictactoe.domain.usecases;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

public class PlaySoundUseCase {

    private static final File O_SOUND  = new File("src/resources/sounds/oTone.wav");
    private static final File X_SOUND  = new File("src/resources/sounds/xTone.wav");
    private static final File TIMER_SOUND  = new File("src/resources/sounds/timer.wav");
    private static final File WIN_SOUND  = new File("src/resources/sounds/win2.wav");
    private static final File DRAW_SOUND  = new File("src/resources/sounds/draw.wav");
    private static final File LOSE_SOUND  = new File("src/resources/sounds/lose.wav");

    private Clip clip;

    public void playSound(int soundNum) {
        File filePath;

        switch (soundNum) {
            case 1:
                filePath = O_SOUND;
                break;
            case 2:
                filePath = X_SOUND;
                break;
            case 3:
                filePath = TIMER_SOUND;
                break;
            case 4:
                filePath = WIN_SOUND;
                break;
            case 5:
                filePath = DRAW_SOUND;
                break;
            case 6:
                filePath = LOSE_SOUND;
                break;
            default:
                System.err.println("Invalid sound name: " + soundNum);
                return;
        }

        playSoundFile(filePath);
    }

    private void playSoundFile(File filePath) {
        try {
            if (clip != null && clip.isOpen()) {
                clip.stop();
                clip.close();
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(filePath);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.setFramePosition(0);
            clip.start();

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            Logger.getLogger(PlaySoundUseCase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stopSound() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    public void closeAll() {
        clip.close();
    }

}
