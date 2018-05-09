package tetris.resources;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The audio class plays audio from a mp3 file
 * @author Anup Bagali
 * @author Teja Kocerla
 * @author Kevin Liu
 * @author Amit Rajesh
 */
public class Audio {
    /**
     * Makes a new Audio object
     * @param s Full path to audio file
     */
    public Audio(String s) {
        try

        {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Audio.class.getResource(s));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}