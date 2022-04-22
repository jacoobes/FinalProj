package FinalProj.scene;

import FinalProj.Game;
import FinalProj.utils.Publisher;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.Subs;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;

public abstract class SceneAlpha extends BasicContainer implements Subs<Boolean> {
    protected ResourceLoader resourceLoader;
    protected Font gameFont;
    protected JLabel mainBGround;
    protected SceneAlpha(
            ResourceLoader rl,
            ImageIcon background
    )
    {
        super();
        this.resourceLoader = rl;
        this.gameFont = rl.getBitStrFont().deriveFont(20f);
        var mainPanel = Game.mainPanel(background);
        mainPanel.setLayout(getLayout());
        mainBGround = mainPanel;
        setStringLayout(layoutScene());
        add("Background", mainPanel);
    }
    protected String[][] layoutScene()
    {
        return new String[][] { { "Background" } };
    }
    @Override
    public abstract void update(Event<Boolean> event);
}
