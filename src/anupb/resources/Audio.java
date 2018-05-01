package anupb.resources;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;


public class Audio{

    private Clip clip;

    public Audio(String s) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream(s)));
            //AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(Audio.class.getResourceAsStream(s)));
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
                    baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void play() {
        if (clip == null && clip.isRunning())
            return;
        stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {
        if (clip.isRunning())
            clip.stop();
    }

    public void close() {
        stop();
        clip.close();
    }

    public Clip getClip() {
        return clip;
    }
}
