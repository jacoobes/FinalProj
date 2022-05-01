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

public class Scene5 extends SceneAlpha {
    private final ChoiceButton c1 = new ChoiceButton("Look back").addFont(getGameFont(20f));
    private final ChoiceButton c2 = new ChoiceButton("Ignore it").addFont(getGameFont(20f));
    public Scene5(ResourceLoader rl) {
        super(rl, new ImageIcon(rl.getPicture("blackground").getImage()));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        JTextPane textBox = new JTextPane();
        textBox.setOpaque(false);
        textBox.setForeground(Color.LIGHT_GRAY);
        textBox.setFont(getGameFont(20f));
        StyledDocument doc = textBox.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        textBox.setVisible(true);
        textBox.setEditable(false);
        var text = """
               Screams pierce the sky.
               You continue to run in a panic.
               """;
        var txtEmit = new TextEmitter(text)
                .setJText(textBox)
                .addSub(this);

        c1.addActionListener(e -> {
            rl.getMyProfile(true).addGuilt(10);
            Game.transitionScene(this, Scene6.class.getName());
            BasicContainer scene6 = new Scene6(rl);
            rl.getFrame().getContentPane().add(scene6, Scene6.class.getName());
        });
        c2.addActionListener(e -> {
            rl.getMyProfile(true).addGuilt(20);
            BasicContainer scene6 = new Scene7(rl);
            rl.getFrame().getContentPane().add(scene6, Scene7.class.getName());
            Game.transitionScene(this, Scene7.class.getName());
        });
        var narrator = new Timer(75, txtEmit);
        narrator.start();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 5;
        gbc.gridx = GridBagConstraints.CENTER;
        getMainBGround().add(textBox,gbc);
        buttonPanel.add(c1);
        buttonPanel.add(c2);
        gbc.gridy = 3;
        getMainBGround().add(buttonPanel, gbc);
        rl.getFrame().jf.pack();
    }

    @Override
    public void update(Event<Boolean> event) {
        c1.toggleVis();
        c2.toggleVis();
    }
}
