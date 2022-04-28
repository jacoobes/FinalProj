package FinalProj.components;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public interface WindowCloseListener extends WindowListener {
    @Override
    void windowClosing(WindowEvent e);

    @Override
    void windowClosed(WindowEvent e);
}
