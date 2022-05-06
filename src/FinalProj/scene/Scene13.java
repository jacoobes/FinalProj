package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.TextBox;
import FinalProj.utils.*;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.SOUTH;

public class Scene13 extends SceneAlpha {
    private final JButton next = new JButton(">");
    public Scene13(ResourceLoader rl)
    {
        super(rl, new ImageIcon(rl.getPicture("textScene").resize(1.5f).getImage()));
        var tutFont = getGameFont(20f);

        JTextArea textBox = new TextBox(tutFont);
        textBox.setVisible(true);
        boolean hasEnoughResolve = rl.getMyProfile(true).resolve >= 60;
        var text = !hasEnoughResolve ? String.format("""
            You look at the dark figurine crying for help.
            You step back, looking the opposite direction.
            
            "Please... %s"
            """,rl.getName()): """
                LET GO! You find a stick on the ground,
                taking it and puncturing its eyes.
                """ ;
        var txtEmit = new TextEmitter(text)
                .setJText(textBox)
                .addSub(this);

        var gbc = new GridBagConstraints();
        gbc.gridy = SOUTH;

        next.setFont(tutFont);
        next.addActionListener(e -> {
            sp.stop("type");
            BasicContainer scene4 = new Scene14(rl);
            rl.getFrame().getContentPane().add(scene4, Scene14.class.getName());
            //transition
            Game.transitionScene(this, Scene14.class.getName());
            //request focus
            scene4.requestFocus();
        });

        var narrate = new Timer(75, txtEmit);
        try {
            sp.loop("type",-1);
        } catch ( Exception e)
        {
            System.out.println(e);
        }
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
                System.out.println("Scene3 has finished");
                next.setVisible(true);
                next.requestFocus();
            });
            showButton.setRepeats(false);
            showButton.start();

        }
    }
}
