package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.components.TextBox;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.SOUTH;
//Branch class of Scene 5 where MC ignores it
public class Scene17 extends SceneAlpha {
    private final ChoiceButton cb = new ChoiceButton(">").addFont(getGameFont(20f));
    public Scene17(ResourceLoader rl) {
        super(rl, new ImageIcon(rl.getPicture("blackground").getImage()));
        JTextArea textBox = new TextBox(getGameFont(20f));
        if(!sp.isPlaying("type"))
        {
            try {
                sp.loop("brown",-1);
                sp.loop("type",-1);
            } catch (Throwable e)
            {
                System.out.println(e);
            }
        }
        textBox.setVisible(true);
        textBox.setOpaque(false);
        textBox.setForeground(Color.LIGHT_GRAY);

        var text = String.format("""
              %s! %s! It hurts
              ... ... Come back..
              """, rl.getName(), rl.getName());

        var txtEmit = new TextEmitter(text)
                .setJText(textBox)
                .addSub(this);

        cb.addActionListener(e -> {
            var sc18 = new Scene18(rl);
            rl.getFrame().getContentPane().add(sc18, Scene18.class.getName());
            Game.transitionScene(this, Scene18.class.getName());
        });
        var narrator = new Timer(50, txtEmit);
        narrator.start();
        var gbc = new GridBagConstraints();
        gbc.gridy = SOUTH;
        gbc.gridx = CENTER;
        var gbcTxtBox = new GridBagConstraints();
        gbcTxtBox.gridx = CENTER;
        getMainBGround().add(textBox,gbcTxtBox);
        getMainBGround().add(cb,gbc);
    }

    @Override
    public void update(Event<Boolean> event) {
        if(event.getState())
        {
            cb.toggleVis();
            sp.stop("type");
        }
    }
}