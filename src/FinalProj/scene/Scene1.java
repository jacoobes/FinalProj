package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.TextBox;
import FinalProj.utils.*;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;
import static java.awt.GridBagConstraints.SOUTH;

public class Scene1 extends SceneAlpha {
    private final JButton next = new JButton(">");
    private final SoundPlayer sp = super.resourceLoader.getSoundPlayer();
    public Scene1(ResourceLoader rl) {

        super(rl, new ImageIcon(rl.getPicture("textScene").resize(1.5f).getImage()));
        try {
            sp.loop("brown",-1);
            sp.loop("die",10);
        } catch (Throwable e) {
            System.out.println(e);
        }
        var tutFont = getGameFont(20f);
        JTextArea textBox = new TextBox(tutFont);
        textBox.setVisible(true);
        var text = String.format("""
               Help me...  Please... %s
               I never meant it. I promise.
               Help me out will you?
               """, rl.getName());
        var txtEmit = new TextEmitter(text)
                .setJText(textBox)
                .addSub(this);

        var gbc = new GridBagConstraints();
         gbc.gridy = SOUTH;

      // make sure to uncomment in game
     //   next.setVisible(false);
        next.setFont(tutFont);
        next.addActionListener(e -> {
            BasicContainer scene2 = new Scene2(rl);
            rl.getFrame().getContentPane().add(scene2, "Scene2");
            //transition
            Game.transitionScene(this, "Scene2");
            scene2.requestFocus();
        });
        var narrate = new Timer(50, txtEmit);
        narrate.start();
        getMainBGround().add(textBox);
        getMainBGround().add(next, gbc);
    }

    @Override
    public void update(Event<Boolean> event) {
        if(event.getState())
        {
            var showButton = new Timer(2000, e -> {
                System.out.println("Scene1 has finished");
                next.setVisible(true);
            });
            showButton.setRepeats(false);
            showButton.start();
        }
    }

}
