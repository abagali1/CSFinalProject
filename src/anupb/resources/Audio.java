package anupb.resources;

import javax.sound.sampled.*;
import java.io.File;

public class Audio {

    private File audiofile;
    private AudioInputStream stream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip clip;

    public Audio(String filename) throws Exception{
        audiofile = new File(filename);
        stream = AudioSystem.getAudioInputStream(audiofile);
        format = stream.getFormat();
        info = new DataLine.Info(Clip.class, format);
        clip = (Clip) AudioSystem.getLine(info);
    }

    public void start(){
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public Clip getClip(){
        return clip;
    }

    public String getFile(){
        return audiofile.getName();
    }

}
