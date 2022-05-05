package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.TextBox;
import FinalProj.utils.*;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;
import static java.awt.GridBagConstraints.SOUTH;

public class Scene11 extends SceneAlpha {
    private final JButton next = new JButton(">");

    public Scene11(ResourceLoader rl, boolean hasEnoughResolve) {

        super(rl, new ImageIcon(rl.getPicture("textScene").resize(1.5f).getImage()));
        try {
            sp.loop("brown",-1);
            sp.setVolume("brown", .3f);
        } catch (Throwable e) {
            System.out.println(e);
        }
        var tutFont = getGameFont(20f);
        JTextArea textBox = new TextBox(tutFont);
        textBox.setVisible(true);
        var text = hasEnoughResolve ? String.format("""
               Help me...  Please... %s
               My love, help me please....
               """, rl.getName())  : """
                You fight the breathing man.
                               
                "LET GO!"
                 """;

        var txtEmit = new TextEmitter(text)
                .setJText(textBox)
                .addSub(this);

        var gbc = new GridBagConstraints();
         gbc.gridy = SOUTH;

      // make sure to uncomment in game
     //   next.setVisible(false);
        next.setFont(tutFont);
        next.addActionListener(e -> {
            sp.stop("type");
            BasicContainer scene12 = new Scene12(rl);
            rl.getFrame().getContentPane().add(scene12, Scene12.class.getName());
            //transition
            Game.transitionScene(this, Scene12.class.getName());
            scene12.requestFocus();
        });
        var narrate = new Timer(50, txtEmit);
        narrate.start();
        try {
         sp.loop("type",4);
        } catch (Throwable e)
        {
            System.out.println(e);
        }

        getMainBGround().add(textBox);
        getMainBGround().add(next, gbc);
        rl.getFrame().jf.pack();
    }

    @Override
    public void update(Event<Boolean> event) {
        sp.stop("type");
        if(event.getState())
        {
            var showButton = new Timer(2000, e -> {
                System.out.println("Scene11 has finished");
                next.setVisible(true);
            });
            showButton.setRepeats(false);
            showButton.start();
        }
    }

}
