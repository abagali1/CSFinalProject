package anupb.resources;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {

    public Audio() {
        try

        {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Audio.class.getResource("C:\\Users\\anupb\\Desktop\\CSProject\\src\\anupb\\audio\\TetrisTheme.mp3"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}