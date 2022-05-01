package FinalProj.components;

import FinalProj.Game;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.SceneTracker;
import FinalProj.utils.YamlParser;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class WindowCloseListener implements WindowListener {
    ResourceLoader rl;
    public WindowCloseListener(ResourceLoader rl) {
        this.rl = rl;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if(rl.getMyProfile(true) == null) {
            return;
        }
        YamlParser.Data curProf = rl.getMyProfile(true);
        curProf.savedScene = SceneTracker.resolveStringName();
        int res = JOptionPane.showConfirmDialog(
                rl.getFrame().jf,
                "Save progress?",
                null,
                JOptionPane.YES_NO_OPTION
        );
        if (res == 0)
        {
            try
            {
                rl.yamlizer.dump(curProf);
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        else
        {
           YamlParser.Data backup = rl.getMyProfile(false);
            try
            {
              System.out.println(backup);
              rl.yamlizer.dump(backup);
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
    @Override
    public void windowOpened(WindowEvent e) {}
}
