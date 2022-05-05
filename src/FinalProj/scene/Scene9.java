package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.components.SemiTransparentTextField;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;

import javax.swing.*;
import java.awt.*;

import static FinalProj.utils.ResourceLoader.*;

public class Scene9  extends SceneAlpha  {
    private int buttonClicks;
    private final SemiTransparentTextField textBox;
    private final ChoiceButton next = new ChoiceButton(">");
    public Scene9(ResourceLoader resourceLoader)
    {
        super(resourceLoader, new ImageIcon(resourceLoader.getPicture("sunset").resize(1.5f).getImage()));

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

        next.addActionListener(e -> {
            //transition scene
        });
        if(!sp.isPlaying("breath"))
        {
            try
            {
                sp.setVolume("breath",.1f);
                sp.loop("breath",-1);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        var textEmitter = new TextEmitter("""
                ... aaa ........ a..hh
                h...hhhh..
                """).addSub(this).setJText(textBox);
        textPanel.init();

        try
        {
            sp.loop("type",-1);
        } catch (Exception ignored) {}

        new Timer(75, textEmitter).start();

        getMainBGround().add(textPanel);

    }
    @Override
    public void update(Event<Boolean> event) {
        try
        {
            sp.stop("type");
            sp.loop("breath",-1);
        } catch (Exception ignored) {}
        next.toggleVis();

        buttonClicks++;
        if(buttonClicks > 1)
        {
            next.removeActionListener(next.getActionListeners()[0]);
        }
        if(buttonClicks == 1)
        {
            next.addActionListener(e -> {
                next.toggleVis();
                try
                {
                    sp.loop("type",-1);
                    sp.loop("breath",-1);
                } catch (Exception ignored) {};
                var response = switch(resourceLoader.getMyProfile(true).choices.get(2))
                        {
                            case S8C0 -> "\"Flowers for repose, a beautiful sight.\"";
                            case S8C1, S8C2 -> "He starts giggling hysterically.";
                            case S8C3 -> "He stares back at you, eyes never blinking.";
                            default -> throw new IllegalArgumentException("oops typo");
                        };

                textBox.setText("");
                var textEmit = new TextEmitter(String.format("""
                        %s
                        
                        Your fiance whispers to you.
                        "%s, he appears to be ill, let us go."
                        """,response, resourceLoader.getName()))
                        .setJText(textBox)
                        .addSub(this);

                new Timer(75,textEmit).start();
            });
        }
        if(buttonClicks == 2)
        {
            next.addActionListener(e -> {
                try
                {
                    sp.loop("type",-1);
                    sp.loop("breath",-1);
                } catch (Exception ignored) {}
                next.toggleVis();
                textBox.setText("");
                var textEmit = new TextEmitter("""
                        You take her hand, now walking faster than before.
                        
                        Looking back, you notice his eyes looking into yours.
                        """)
                        .setJText(textBox)
                        .addSub(this);

                new Timer(50,textEmit).start();
            });
        }
        if(buttonClicks == 3)
        {
            next.addActionListener(e -> {
                try
                {
                    sp.setVolume("type",.5f);
                    sp.loop("type",-1);
                    sp.loop("breath",-1);
                } catch (Exception ignored) {}
                next.toggleVis();
                textBox.setText("");
                var textEmit = new TextEmitter(String.format("""
                        You start to walk with haste, hand in hand.
                        You do not look back, palms sweaty.
                        
                        "%s, he's walking. he's walking..."
                        """, resourceLoader.getName()))
                        .setJText(textBox)
                        .addSub(this);

                new Timer(50,textEmit).start();
            });
        }
        if(buttonClicks == 4)
        {
            next.addActionListener(e -> {
                try
                {
                    sp.loop("type",-1);
                    sp.loop("breath",-1);
                } catch (Exception ignored) {}
                next.toggleVis();
                textBox.setText("");
                var textEmit = new TextEmitter("""
                        Hurry, my love.
                        He is just walking our way, that is all.
                                                
                        You grip her hand, pacing even faster now.
                        """)
                        .setJText(textBox)
                        .addSub(this);

                new Timer(50,textEmit).start();
            });
        }
        if(buttonClicks == 5)
        {
            next.addActionListener(e -> {
                try
                {
                    sp.loop("type",-1);
                    sp.loop("breath",-1);
                } catch (Exception ignored) {}
                next.toggleVis();
                textBox.setText("");
                var textEmit = new TextEmitter("""
                        The man behind you picks
                        up his pace as well.
                        """)
                        .setJText(textBox)
                        .addSub(this);

                new Timer(50,textEmit).start();
            });
        }
        if(buttonClicks == 6)
        {
            next.addActionListener(e -> {
                try
                {
                    sp.loop("type",-1);
                    sp.loop("breath",-1);
                } catch (Exception ignored) {}
                next.toggleVis();
                textBox.setText("");
                var enoughResolve = resourceLoader
                        .getMyProfile(true)
                        .guilt == 5;
                var response = enoughResolve ? "Stay away! I will kill you!": "Hurry my dear..";
                if(!enoughResolve)
                {
                    resourceLoader.getMyProfile(true).addGuilt(5);
                }
                var textEmit = new TextEmitter(String.format("""
                        a...aaa........a....
                        
                        "%s"
                        """, response))
                        .setJText(textBox)
                        .addSub(this);
                new Timer(50,textEmit).start();
            });
        }
        if(buttonClicks == 7)
        {
            next.addActionListener(e -> {
                try
                {
                    sp.loop("type",-1);
                    sp.loop("breath",-1);
                } catch (Exception ignored) {}
                next.toggleVis();
                textBox.setText("");
                var textEmit = new TextEmitter("""
                        "Ambiguity in the face of certainty...."
                        "Why bother when myself tells me otherwise?"
                        
                        He begins to run, foot over foot, a quick change in pace.
                                        
                        "......... Don't touch us!"
                        """)
                        .setJText(textBox)
                        .addSub(this);
                new Timer(50,textEmit).start();
            });
        }
        if(buttonClicks == 8)
        {
            next.addActionListener(e -> {
                var sc10 = new Scene10(resourceLoader);
                resourceLoader.getFrame().getContentPane().add(sc10, Scene10.class.getName());
                Game.transitionScene(this, Scene10.class.getName());
            });
        }
    }
}
