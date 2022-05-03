package FinalProj.scene;

import FinalProj.MC;
import FinalProj.components.ChoiceButton;
import FinalProj.components.QuadChoicePanel;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.events.Event;
import basicgraphics.Clock;
import basicgraphics.SpriteComponent;

import javax.swing.*;
import java.awt.*;

public class Scene2 extends SceneAlpha {
    private final SpriteComponent sc = new SpriteComponent();
    private final MC joe = new MC(sc);
    public Scene2(ResourceLoader rl) {
        super(rl, new ImageIcon(rl.getPicture("happy").resize(1.5f).getImage()));
        sc.setPreferredSize(new Dimension(300, 600));
        Clock.addTask(joe.animate());
        if(!sp.isPlaying("happy"))
        {
            try
            {
             sp.loop("happy",-1);
            } catch(Throwable e)
            {
                System.out.println(e);
            }
        }

        var choicePanel = new QuadChoicePanel(new ChoiceButton[] {
           new ChoiceButton("This was where we first met.").addFont(getGameFont(20f)),
           new ChoiceButton("We needed to go outside, it was too congested!").addFont(getGameFont(20f)),
           new ChoiceButton("My love, I'd take you anywhere to make you happy.").addFont(getGameFont(20f)),
           new ChoiceButton("Your favorite lilies of course!").addFont(getGameFont(20f))
        });

        var choiceButtons = choicePanel.getButtons();

        var sc3 = new Scene3(rl);

        rl.getFrame().getContentPane().add(sc3,Scene3.class.getName());
        choiceButtons.get(0).addActionListener(e -> {
            choicePanel.setVisible(false);
            sc.repaint();
            rl.getMyProfile(true).addResolve(10);
        });
        choiceButtons.get(1).addActionListener(e -> {
            choicePanel.setVisible(false);
            sc.repaint();
            rl.getMyProfile(true).addResolve(5);
        });
        choiceButtons.get(2).addActionListener(e -> {
            choicePanel.setVisible(false);
            sc.repaint();
            rl.getMyProfile(true).addResolve(30);
        });
        choiceButtons.get(3).addActionListener(e -> {
            choicePanel.setVisible(false);
            rl.getMyProfile(true).addResolve(30);
        });

        var gbcPanel = new GridBagConstraints();
        gbcPanel.insets = new Insets(300,0,0,0);
        gbcPanel.ipady = 150;
        getMainBGround().add(sc);
        getMainBGround().add(choicePanel,gbcPanel);
        rl.getFrame().jf.pack();
    }
    @Override
    public void update(Event<Boolean> event) {}
}
