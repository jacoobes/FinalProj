package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class Scene10 extends SceneAlpha {
    private final ChoiceButton c1 = new ChoiceButton(">").addFont(getGameFont(20f));
    public Scene10(ResourceLoader rl)
    {
        super(rl, new ImageIcon(rl.getPicture("blackground").getImage()));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        var textBox = new JTextPane() {
            public void init()
            {
                setOpaque(false);
                setForeground(Color.LIGHT_GRAY);
                setFont(getGameFont(20f));
                StyledDocument doc = getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
                setVisible(true);
                setEditable(false);
            }
        };
        textBox.init();

        var text = String.format("""
               "It comes and goes.." he breathes.
               
               %s, %s, he has my hand. HELP.
               PLEASE..
               """,rl.getName(),rl.getName());
        var txtEmit = new TextEmitter(text)
                .setJText(textBox)
                .addSub(this);

        c1.addActionListener(e -> {
            var sc11 = new Scene11(rl, rl.getMyProfile(true).resolve == 60);
            rl.getFrame().getContentPane().add(sc11,Scene11.class.getName());
            Game.transitionScene(this, Scene11.class.getName());
        });
        var narrator = new Timer(75, txtEmit);
        narrator.start();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 5;
        gbc.gridx = GridBagConstraints.CENTER;
        getMainBGround().add(textBox,gbc);
        buttonPanel.add(c1);
        gbc.gridy = 3;
        getMainBGround().add(buttonPanel, gbc);
        rl.getFrame().jf.pack();
    }
    @Override
    public void update(Event<Boolean> event) {
        c1.toggleVis();

    }
}
