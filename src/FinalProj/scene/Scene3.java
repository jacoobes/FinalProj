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

public class Scene3 extends SceneAlpha {
    private final JButton next = new JButton(">");
    public Scene3(ResourceLoader rl)
    {
        super(rl, new ImageIcon(rl.getPicture("textScene").resize(1.5f).getImage()));
        var tutFont = getGameFont(20f);

        JTextArea textBox = new TextBox(tutFont);
        textBox.setVisible(true);
        var text = String.format("""
            A shout from a hill.
            You look at the dark figurine crying for help.
            You take a step back, looking the opposite direction.
            
            "Please... %s"
            The shouting gets closer, and you run.
            """,rl.getName());
        var txtEmit = new TextEmitter(text)
                .setJText(textBox)
                .addSub(this);

        var gbc = new GridBagConstraints();
        gbc.gridy = SOUTH;

        next.setFont(tutFont);
        next.addActionListener(e -> {
            BasicContainer scene4 = new Scene4(rl);
            rl.getFrame().getContentPane().add(scene4, "Scene4");
            //transition
            Game.transitionScene(this, "Scene4");
            //request focus
            scene4.requestFocus();
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
                System.out.println("Scene3 has finished");
                next.setVisible(true);
                next.requestFocus();
            });
            showButton.setRepeats(false);
            showButton.start();

        }
    }
}
