package FinalProj.scene;

import FinalProj.Game;
import FinalProj.MC;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.SoundPlayer;
import FinalProj.utils.Subs;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;
import basicgraphics.SpriteComponent;

import javax.swing.*;
import java.awt.*;

public abstract class SceneAlpha extends BasicContainer implements Subs<Boolean> {
    protected ResourceLoader resourceLoader;
    protected Font gameFont;
    protected JLabel mainBGround;
    protected SoundPlayer sp;
    protected SpriteComponent sc = new SpriteComponent();
    protected MC mainCharac = new MC(sc);
    public SceneAlpha(
            ResourceLoader rl,
            ImageIcon background
    ) {
        super();
        this.resourceLoader = rl;
        this.gameFont = rl.getBitStrFont();
        sp = rl.getSoundPlayer();
        var mainPanel = Game.mainPanel(background);
        mainPanel.setLayout(getLayout());
        mainBGround = mainPanel;
        setStringLayout(layoutScene());
        setPreferredSize(mainPanel.getPreferredSize());
        add("Background", mainPanel);
    }
    protected String[][] layoutScene()
    {
        return new String[][] { { "Background" } };
    }
    @Override
    public abstract void update(Event<Boolean> event);

    public Font getGameFont(float size) {
        return gameFont.deriveFont(size);
    }

    public JLabel getMainBGround() { return mainBGround; }
}
