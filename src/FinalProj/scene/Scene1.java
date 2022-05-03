package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.SemiTransparentTextField;
import FinalProj.components.TextBox;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.SOUTH;

public class Scene1 extends SceneAlpha {
    private final JButton next = new JButton(">");
    public Scene1(ResourceLoader rl) {
        super(rl, new ImageIcon(rl.getPicture("happy").resize(1.5f).getImage()));
        try
        {
         sp.loop("happy",-1);
        } catch (Exception e)
        {
            System.out.println(e);
        }
        var tutFont = getGameFont(20f);
        var textBox = new SemiTransparentTextField(tutFont);
        textBox.setForeground(Color.darkGray);
        textBox.setVisible(true);
        var text = String.format("""
              Don't you love this place, darling?
              It has your favorite flowers, the lilies.   
                                                     
              This place is gorgeous %s, I love it!
              Why did you bring me here?                               
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
            sp.stop("type");
            BasicContainer scene2 = new Scene2(rl);
            rl.getFrame().getContentPane().add(scene2, Scene2.class.getName());
            //transition
            Game.transitionScene(this, Scene2.class.getName());
            scene2.requestFocus();
        });
        var narrate = new Timer(50, txtEmit);
        narrate.start();
        try {
            sp.setVolume("type",.10f);
            sp.loop("type",1);
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
            next.setVisible(true);
        }
    }
}
