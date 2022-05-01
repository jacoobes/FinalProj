package FinalProj.components;

import FinalProj.Game;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.SceneTracker;
import FinalProj.utils.YamlParser;

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
        if(rl.getMyProfile() == null) {
            return;
        }
        YamlParser.Data curProf = rl.getMyProfile();
        curProf.savedScene = SceneTracker.resolveStringName();
        try
        {
            rl.yamlizer.dump(curProf);
        } catch (IOException ex)
        {
            ex.printStackTrace();
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
