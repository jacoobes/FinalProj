package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.components.TextBox;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.Arrays;

public class Scene5 extends SceneAlpha {
    private final JPanel buttonPanel = new JPanel();
    private final ChoiceButton c1 = new ChoiceButton("Look back").addFont(getGameFont(20f));
    private final ChoiceButton c2 = new ChoiceButton("Ignore it").addFont(getGameFont(20f));
    Scene5(ResourceLoader rl) {
        super(rl, new ImageIcon(rl.getPicture("blackground").resize(1.5f).getImage()));
        setLayout(getLayout());
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

        var text = """
               Screams pierce the sky.
               You continue to run in a panic.
               """;
        var txtEmit = new TextEmitter(text)
                .setJText(textBox)
                .addSub(this);
        BasicContainer scene6 = new Scene6(rl);
        rl.getFrame().getContentPane().add(scene6, "Scene6");
        c1.addActionListener(e -> {
            Game.transitionScene(this, "Scene6");
            rl.addGuilt(10);
        });
        c2.addActionListener(e -> {
            Game.transitionScene(this, "Scene6");
            rl.addGuilt(20);
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
