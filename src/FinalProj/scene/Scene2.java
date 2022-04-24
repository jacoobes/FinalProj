package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.TextBox;
import FinalProj.utils.Publisher;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.Subs;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.SOUTH;

public class Scene2 extends SceneAlpha {
    private final JButton next = new JButton(">");
    public Scene2(ResourceLoader rl)
    {
        super(rl, new ImageIcon(rl.getPicture("textScene").resize(1.5f).getImage()));
        var tutFont = getGameFont(20f);
        JTextArea textBox = new TextBox(tutFont);
        textBox.setVisible(true);

        var text = """
            Don't leave me. We made a promise, remember?
            Help me.... I don't want to die here.
            """;
        var txtEmit = new TextEmitter(text)
                .setJText(textBox)
                .addSub(this);

        var gbc = new GridBagConstraints();
        gbc.gridy = SOUTH;
        // make sure to uncomment in game
        //next.setVisible(false);
        next.setFont(tutFont);
        next.addActionListener(e -> {
            BasicContainer scene3 = new Scene3(rl);
            rl.getFrame().getContentPane().add(scene3, "Scene3");
            //transition
            Game.transitionScene(this, "Scene3");
            //request focus
            scene3.requestFocus();
        });

        var narrate = new Timer(75, txtEmit);
        getMainBGround().add(textBox);
        getMainBGround().add(next, gbc);
        narrate.start();
    }

    @Override
    public void update(Event<Boolean> event) {
        if(event.getState())
        {
            var showButton = new Timer(2000, e -> {
                System.out.println("Scene2 has finished");
                next.setVisible(true);
                next.requestFocus();
            });
            showButton.setRepeats(false);
            showButton.start();

        }
    }
}
