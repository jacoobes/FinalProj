package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.SOUTH;

public class Scene6 extends SceneAlpha {
    private final ChoiceButton cb = new ChoiceButton(">");

    public Scene6(ResourceLoader rl) {
         super(rl, new ImageIcon(rl.getPicture("blackground").getImage()));
         cb.setFont(getGameFont(20f));

            JTextPane textBox = new JTextPane();
            textBox.setOpaque(false);
            textBox.setForeground(Color.LIGHT_GRAY);
            textBox.setFont(getGameFont(20f));
            StyledDocument doc = textBox.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            var text = String.format("""
              %s! %s! It hurts
              ... ... Come back  
                    """, rl.getName(), rl.getName());

            var txtEmit = new TextEmitter(text)
                    .setJText(textBox)
                    .addSub(this);
            var narrator = new Timer(75, txtEmit);
            narrator.start();
            cb.addActionListener(e -> {
                rl.getMyProfile(true).addGuilt(50);
                Game.transitionScene(this, Scene6.class.getName());
            });
            var gbc = new GridBagConstraints();
            gbc.gridy = SOUTH;
            gbc.gridx = CENTER;
            getMainBGround().add(textBox);
            getMainBGround().add(cb, gbc);

        }

    @Override
    public void update(Event<Boolean> event) {
        if(event.getState())
        {
            cb.toggleVis();
        }
    }
}
