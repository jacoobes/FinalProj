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
import java.util.HashMap;

public class Scene2 extends SceneAlpha {
    private final SpriteComponent sc = new SpriteComponent();
    private final MC joe = new MC(sc);
    private static final HashMap<Integer, Integer> resolveMap = new HashMap<>();
    static
    {
        resolveMap.put(0,10);
        resolveMap.put(1,5);
        resolveMap.put(2,30);
        resolveMap.put(3,30);
    }
    public Scene2(ResourceLoader rl) {
        super(rl, new ImageIcon(rl.getPicture("happy").resize(1.5f).getImage()));
        sc.setPreferredSize(new Dimension(300, 600));
        sc.setOpaque(false);
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
        var spritePanel = new JPanel();
        spritePanel.add(sc);
        spritePanel.setVisible(false);
        spritePanel.setOpaque(false);
        rl.getFrame().getContentPane().add(sc3,Scene3.class.getName());

        for(int i = 0; i < choiceButtons.size(); i++)
        {
            int finalI = i;
            choiceButtons.get(i).addActionListener(e -> {
                choicePanel.setVisible(false);
                spritePanel.setVisible(true);
                rl.getMyProfile(true).addResolve(resolveMap.get(finalI));
                Clock.start(150);
            });
        }


        var gbcPanel = new GridBagConstraints();
        gbcPanel.insets = new Insets(300,0,0,0);
        gbcPanel.ipady = 150;
        var gbcSprite = new GridBagConstraints();
        gbcSprite.insets = new Insets(0,700,0,0);
        getMainBGround().add(spritePanel,gbcSprite);
        getMainBGround().add(choicePanel,gbcPanel);
        rl.getFrame().jf.pack();
    }
    @Override
    public void update(Event<Boolean> event) {}
}
