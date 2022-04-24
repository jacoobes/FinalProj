package FinalProj.scene;

import FinalProj.components.ChoiceButton;
import FinalProj.components.TextBox;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.*;


public class Scene5 extends SceneAlpha {
    private final ChoiceButton c1 = new ChoiceButton("Look back");
    private final ChoiceButton c2 = new ChoiceButton("Ignore it");
    Scene5(ResourceLoader rl) {
        super(rl, new ImageIcon(rl.getPicture("blackground").resize(1.5f).getImage()));
        JTextArea textBox = new TextBox(getGameFont(20f));
        textBox.setVisible(true);
        var text = """
               Screams pierce the sky.
               You continue to run in a panic.
               """;
        var txtEmit = new TextEmitter(text)
                .setJText(textBox)
                .addSub(this);
        var narrator = new Timer(75, txtEmit);
        narrator.start();
        getMainBGround().add(textBox);
        var gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 2;
        gbc.insets = new Insets(0,0,0,20);
        getMainBGround().add(c1,gbc);
        gbc.gridy = 0;
        gbc.gridx = 15;
        gbc.insets = new Insets(0,20,0,0);
        getMainBGround().add(c2, gbc);
    }

    @Override
    public void update(Event<Boolean> event) {
        c1.toggleVis();
        c2.toggleVis();
    }
}
