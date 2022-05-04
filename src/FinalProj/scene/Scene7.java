package FinalProj.scene;

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
import java.awt.event.ActionListener;

import static FinalProj.utils.ResourceLoader.*;
import static java.awt.GridBagConstraints.SOUTH;

public class Scene7 extends SceneAlpha {
    private final Timer timer = new Timer(75, null);
    private final ChoiceButton next = new ChoiceButton(">");
    private final SemiTransparentTextField textBox;
    private int buttonClicks;

    public Scene7(ResourceLoader resourceLoader) {
        super(resourceLoader, new ImageIcon(resourceLoader.getPicture("sunset").resize(1.5f).getImage()));
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
    //    Clock.addTask(mainCharac.animate());
        var choices = resourceLoader.getMyProfile(true).choices;
        var response = switch (choices.get(1))
                {
                    case S6C0 -> String.format("%s, I will care for you forever also.", resourceLoader.getName());
                    case S6C1 -> "Oh... okay.";
                    case S6C2 -> "My love, I would protect you from anything as well.";
                    case S6C3 -> "I swear to love you until the day I die." ;
                    default -> throw new IllegalArgumentException("Could not load choice 2");
                };
        textBox = new SemiTransparentTextField(getGameFont(20f));
        textBox.setForeground(Color.darkGray);
        textBox.setVisible(true);
        var textEmitter = new TextEmitter(String.format("""
                %s
                I am looking forward to our new life...
                
                Quick! Let us head home before the sun sets.
                """, response)).addSub(this).setJText(textBox);

        next.addActionListener(e -> {
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

        var gbc = new GridBagConstraints();
        gbc.gridy = SOUTH;
        getMainBGround().add(textBox);
        getMainBGround().add(next,gbc);

    }

    @Override
    public void update(Event<Boolean> event) {
        next.toggleVis();
        buttonClicks++;
        if(buttonClicks > 1)
        {
            next.removeActionListener(next.getActionListeners()[0]);
        }
        if(buttonClicks == 2)
        {
            next.addActionListener(e -> {
                textBox.setText("");
                next.toggleVis();
                var textEmitter3 = new TextEmitter("""
                    As you walk home, a man stands in the way,
                    obstructing the path. He stares at the sky.
                    """)
                        .addSub(this)
                        .setJText(textBox);
                new Timer(75,textEmitter3).start();
            });
        }
        if (buttonClicks == 3)
        {
            next.addActionListener(e -> {
                textBox.setText("");
                next.toggleVis();
                var textEmitter4 = new TextEmitter("""
                    As you walk home, a man stands in the way,
                    obstructing the path. He stares at the sky.
                    """)
                        .addSub(this)
                        .setJText(textBox);
                new Timer(75,textEmitter4).start();
            });
        }

    //    Clock.stop();
    }
}
