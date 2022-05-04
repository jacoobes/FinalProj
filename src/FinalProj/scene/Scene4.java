package FinalProj.scene;

import FinalProj.Game;
import FinalProj.MC;
import FinalProj.components.ChoiceButton;
import FinalProj.components.SemiTransparentTextField;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.Clock;
import basicgraphics.SpriteComponent;

import javax.swing.*;
import java.awt.*;

public class Scene4 extends SceneAlpha {
    private final ChoiceButton next = new ChoiceButton(">");
    private final SpriteComponent sc = new SpriteComponent();
    private final MC joe = new MC(sc);
    private final SemiTransparentTextField speechBubble = new SemiTransparentTextField(getGameFont(20f));
    private int buttonClicks = 0;

    public Scene4(ResourceLoader rl)
    {
        super(rl, new ImageIcon(rl.getPicture("happy").resize(1.5f).getImage()));
        Clock.addTask(joe.animate());
        Clock.start(75);
        var textPanel = new JPanel(new GridBagLayout());
        sc.setPreferredSize(new Dimension(300,600));

        speechBubble.setForeground(Color.darkGray);
        textPanel.setVisible(true);
        textPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        speechBubble.setVisible(true);

        var textEmitter = new TextEmitter("""
                I also brought you here because I wanted
                to say something.
                """).addSub(this).setJText(speechBubble);

        textPanel.add(speechBubble, gbc);
        textPanel.add(next, gbc);

        next.addActionListener(e -> {
            next.toggleVis();
            speechBubble.setText("");
            var textEmitter2 = new TextEmitter("""
                    This place we love so much,
                    It brings back memories, yes?
                    Ever since we were kids,
                    my love is for you and for you only.
                    Will you marry me?
                    """)
                    .addSub(this)
                    .setJText(speechBubble);
            new Timer(75,textEmitter2).start();
            Clock.start(75);
        });

        new Timer(75, textEmitter).start();
        getMainBGround().add(textPanel);
        getMainBGround().add(sc);
    }
    @Override
    public void update(Event<Boolean> event) {
        next.toggleVis();
        buttonClicks++;
        if(buttonClicks == 2)
        {
            System.out.println("Scene4 finished");
            next.addActionListener(e -> {
                var scene5 = new Scene5(resourceLoader);
                resourceLoader.getFrame().getContentPane().add(scene5, Scene5.class.getName());
                Game.transitionScene(this, Scene5.class.getName());
            });
        }


        Clock.stop();
    }
}
