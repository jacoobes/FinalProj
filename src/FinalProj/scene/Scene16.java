package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.components.TextBox;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.SOUTH;
//Branch Scene of Scene5 where MC looks back
public class Scene16 extends SceneAlpha {
    private final ChoiceButton cb = new ChoiceButton(">").addFont(getGameFont(20f));

    public Scene16(ResourceLoader rl) {
         super(rl, new ImageIcon(rl.getPicture("blackground").getImage()));
            JTextArea textBox = new TextBox(getGameFont(20f));
            textBox.setVisible(true);
            textBox.setOpaque(false);
            textBox.setForeground(Color.LIGHT_GRAY);

            var text = String.format("""
              %s! %s! It hurts..
              Come back.. I loved you!
              What happened to our promise?
              %s...
              """, rl.getName(), rl.getName(), rl.getName());

            var txtEmit = new TextEmitter(text)
                    .setJText(textBox)
                    .addSub(this);

            cb.addActionListener(e -> {
                rl.getMyProfile(true).addGuilt(50);
                BasicContainer sc8 = new Scene18(rl);
                rl.getFrame().getContentPane().add(sc8, Scene18.class.getName());
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
        }
    }
}
