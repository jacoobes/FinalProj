package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.components.SemiTransparentTextField;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.Clock;
import basicgraphics.Task;

import javax.swing.*;

import java.awt.*;

import static FinalProj.utils.ResourceLoader.*;

public class Scene7 extends SceneAlpha {
    private final ChoiceButton next = new ChoiceButton(">");
    private final SemiTransparentTextField textBox;
    private int buttonClicks;

    public Scene7(ResourceLoader resourceLoader) {
        super(resourceLoader, new ImageIcon(resourceLoader.getPicture("sunset").resize(1.5f).getImage()));
        if (!sp.isPlaying("happy"))
        {
            try
            {
                sp.loop("happy", -1);
            } catch (Throwable e)
            {
                System.out.println(e);
            }
        }
            Clock.addTask(mainCharac.animate());
        var choices = resourceLoader.getMyProfile(true).choices;
        var response = switch (choices.get(1))
                {
                    case S6C0 -> String.format("%s, I will care for you forever also.", resourceLoader.getName());
                    case S6C1 -> "Oh... okay.";
                    case S6C2 -> "My love, I would protect you from anything as well.";
                    case S6C3 -> "I swear to love you until the day I die.";
                    default -> throw new IllegalArgumentException("Could not load choice 2");
                };

        textBox = new SemiTransparentTextField(getGameFont(20f));
        textBox.setVisible(true);

        var textPanel = new JPanel(new GridBagLayout()) {
            public void init() {
                setVisible(true);
                setOpaque(false);
                GridBagConstraints gbcPanel = new GridBagConstraints();
                gbcPanel.gridwidth = GridBagConstraints.REMAINDER;
                gbcPanel.anchor = GridBagConstraints.CENTER;
                add(textBox, gbcPanel);
                add(next, gbcPanel);
            }
        };

        textPanel.init();
        sc.setVisible(false);

        try
        {
            sp.loop("type",-1);
        } catch (Exception e)
        {
            System.out.println(e);
        }
        var textEmitter = new TextEmitter(String.format("""
                %s
                I am looking forward to our new life...
                
                Quick! Let us head home before the sun sets.
                """, response)).addSub(this).setJText(textBox);

        next.addActionListener(e -> {
            try
            {
                sp.loop("type",-1);
            } catch (Exception ex)
            {
                System.out.println(ex);
            }
            textBox.setText("");
            next.toggleVis();
            var textEmitter2 = new TextEmitter("""
                    As you walk home, a man stands in the way,
                    obstructing the path. He stares at the sky.
                    """)
                    .addSub(this)
                    .setJText(textBox);

            new Timer(75,textEmitter2).start();
        });


        new Timer(75, textEmitter).start();

        getMainBGround().add(textPanel, new GridBagConstraints());
        getMainBGround().add(sc, new GridBagConstraints());

    }

    @Override
    public void update(Event<Boolean> event) {
        try
        {
          sp.stop("type");
        } catch (Exception e)
        {
            System.out.println(e);
        }
        next.toggleVis();
        buttonClicks++;
        if(buttonClicks > 1)
        {
            next.removeActionListener(next.getActionListeners()[0]);
        }
        if(buttonClicks == 2)
        {
            next.addActionListener(e -> {

                Clock.start(75);
                sc.setVisible(true);
                try
                {
                    sp.loop("type",-1);
                } catch (Exception ex)
                {
                    System.out.println(ex);
                }
                textBox.setText("");
                next.toggleVis();
                var textEmitter3 = new TextEmitter("""
                    Pardon us, sir. You're in the way.
                    """)
                        .addSub(this)
                        .setJText(textBox);
                new Timer(75,textEmitter3).start();
            });
        }
        if (buttonClicks == 3)
        {
            Clock.addTask(new Task() {
                @Override
                public void run() {
                    if(sp.getVolume("happy") <= .1f && sp.getVolume("happy") >= 0.01f )
                    {
                        sp.stop("happy");
                        Clock.stop();
                        return;
                    }
                    sp.setVolume("happy",sp.getVolume("happy")-.1f);
                }
            });
            next.addActionListener(e -> {
                sc.setVisible(false);
                Clock.start(100);
                try
                {
                    sp.setVolume("breath",.1f);
                    sp.loop("breath",-1);
                    sp.loop("type",-1);
                } catch (Exception ex)
                {
                    System.out.println(ex);
                }
                textBox.setText("");
                next.toggleVis();
                var textEmitter4 = new TextEmitter("""
                    ... a.... aaa......
                    """)
                        .addSub(this)
                        .setJText(textBox);
                new Timer(75,textEmitter4).start();
            });
        }
        if(buttonClicks == 4)
        {
            next.addActionListener(e -> {
                var sc8 = new Scene8(resourceLoader);
                resourceLoader.getFrame().getContentPane().add(sc8, Scene8.class.getName());
                Game.transitionScene(this, Scene8.class.getName());
            });
        }
        try {
            Clock.stop();
        } catch (Throwable ignored) {}
    }
}
