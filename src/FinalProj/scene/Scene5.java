package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.components.SemiTransparentTextField;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.SOUTH;

public class Scene5 extends SceneAlpha {
    private final ChoiceButton next = new ChoiceButton(">");
    public Scene5(ResourceLoader resourceLoader)
    {
        super(resourceLoader, new ImageIcon(resourceLoader.getPicture("happy").resize(1.5f).getImage()));

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
              Oh, %s, I've been waiting for that question
              my entire life....
              
              Yes, yes, of course I'll marry you!
              I want to spend the rest of my life with you.
              """, resourceLoader.getName());

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
            var scene6 = new Scene6(resourceLoader);
            resourceLoader.getFrame().getContentPane().add(scene6, Scene6.class.getName());
            //transition
            Game.transitionScene(this, Scene6.class.getName());
            scene6.requestFocus();
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
        resourceLoader.getFrame().jf.pack();
    }
    @Override
    public void update(Event<Boolean> event) {
        next.toggleVis();
    }
}
