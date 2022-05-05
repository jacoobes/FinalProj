package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.TextBox;
import FinalProj.utils.*;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.SOUTH;

public class Scene12 extends SceneAlpha {
    private final JButton next = new JButton(">");
    public Scene12(ResourceLoader rl)
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
            sp.stop("type");
            BasicContainer scene3 = new Scene13(rl);
            rl.getFrame().getContentPane().add(scene3, Scene13.class.getName());
            //transition
            Game.transitionScene(this, Scene13.class.getName());
            //request focus
            scene3.requestFocus();
        });
        try {
            sp.loop("type",-1);
        } catch (Throwable e)
        {
            System.out.println(e);
        }
        var narrate = new Timer(75, txtEmit);
        getMainBGround().add(textBox);
        getMainBGround().add(next, gbc);
        narrate.start();
        rl.getFrame().jf.pack();
    }

    @Override
    public void update(Event<Boolean> event) {
        if(event.getState())
        {
            sp.stop("type");
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
