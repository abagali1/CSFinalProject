package anupb.resources;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BlockTimer extends Timer {
    public BlockTimer(int delay, ActionListener a){
        super(delay, a);
    }

    public void start(AddingListener a, Block b){
        a.add(b);
        super.start();

    }
}
