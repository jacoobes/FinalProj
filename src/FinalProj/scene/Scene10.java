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

public class Scene10 extends SceneAlpha {
    private final ChoiceButton c1 = new ChoiceButton("Look back").addFont(getGameFont(20f));
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
        var text = """
               Screams pierce the sky.
               You continue to run in a panic.
               """;
        var txtEmit = new TextEmitter(text)
                .setJText(textBox)
                .addSub(this);

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

    }
}
