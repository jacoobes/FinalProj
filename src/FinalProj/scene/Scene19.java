package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.components.SemiTransparentTextField;
import FinalProj.components.TextBox;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.Clock;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.io.IOException;


//happy ending
public class Scene19 extends SceneAlpha {
    private int buttonsClicked;
    private final ChoiceButton next = new ChoiceButton(">");
    private final JTextComponent textBox = new SemiTransparentTextField(getGameFont(20f));

    public Scene19(ResourceLoader rl)
    {
        super(rl, new ImageIcon(rl.getPicture("cabin").resize(1.5f).getImage()));
        try {
            sp.loop("happy",-1);
        } catch (Exception e) {}
        Clock.addTask(mainCharac.animate());
        textBox.setVisible(true);
        textBox.setForeground(Color.darkGray);
        sc.setVisible(false);
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

        var textEmitter = new TextEmitter(String.format("""
                %s, thank you for saving me.
                Thank you for everything.
                
                You were so brave... I could never repay you.
                """, resourceLoader.getName())).addSub(this).setJText(textBox);
        new Timer(75,textEmitter).start();

        getMainBGround().add(textPanel, new GridBagConstraints());
        getMainBGround().add(sc, new GridBagConstraints());
    }

    @Override
    public void update(Event<Boolean> event) {
        next.toggleVis();
        buttonsClicked++;
        if(buttonsClicked > 1)
        {
            next.removeActionListener(next.getActionListeners()[0]);
        }
        if(buttonsClicked == 1)
        {
            next.addActionListener(e -> {
                next.toggleVis();
                Clock.start(75);

                sc.setVisible(true);
                textBox.setText("");
                var textEmitter = new TextEmitter("""
                My love, I will protect you whenever...
                
                I made some venison for our troubles.
                
                You sit down with your new fiancee, grabbing her a fork.
                You bring napkins and plates, preparing for dinner.
                """).addSub(this).setJText(textBox);
                new Timer(75,textEmitter).start();
            });
        }

        if(buttonsClicked == 2)
        {
            next.addActionListener(e -> {

                try
                {
                    resourceLoader.yamlizer.dump(resourceLoader.getMyProfile(true));
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                next.toggleVis();
                sc.setVisible(false);
                next.setText("Main Menu");
                textBox.setText("");
                textBox.setFont(getGameFont(75f));
                textBox.setForeground(Color.black);
                var textEmitter = new TextEmitter("""
                Guilt
                """).addSub(this).setJText(textBox);
                new Timer(1000,textEmitter).start();
            });
        }

        if(buttonsClicked == 3)
        {
            next.addActionListener(e -> {
                sp.stop("happy");

                resourceLoader.getFrame().getContentPane().remove(1);
                ProfileSelection ps = new ProfileSelection(resourceLoader);
                resourceLoader.getFrame().getContentPane().add(ps, ProfileSelection.class.getName());
                Game.transitionScene(this, Title.class.getName());

            });
        }
        try
        {
            Clock.stop();
        } catch (Exception ignored) {};
    }
}
