package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.components.SemiTransparentTextField;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static FinalProj.utils.ResourceLoader.*;
import static java.awt.GridBagConstraints.SOUTH;


public class Scene3 extends SceneAlpha {
    private final ChoiceButton next = new ChoiceButton(">");

    private static final String C2 = "It was too congested inside.";
    private static final String C3 = "I would take you anywhere.";
    private static final String C4 = "Your favorite flowers are here.";

    public Scene3(ResourceLoader rl)
    {
        super(rl, new ImageIcon(rl.getPicture("happy").resize(1.5f).getImage()));
        try
        {
          sp.loop("type",-1);
        } catch (Throwable e) { System.out.println(e); }
        if(!sp.isPlaying("happy"))
        {
            try
            {
                sp.loop("happy",-1);
                sp.loop("type",-1);
            } catch(Throwable e)
            {
                System.out.println(e);
            }
        }
        var tutFont = getGameFont(20f);
        var textBox = new SemiTransparentTextField(tutFont);
        textBox.setForeground(Color.darkGray);
        textBox.setVisible(true);
        next.addActionListener(e -> {
            var s4 = new Scene4(rl);
            rl.getFrame().getContentPane().add(s4,Scene4.class.getName());
            Game.transitionScene(this, Scene4.class.getName());
        });
        ArrayList<String> curDialogue = resourceLoader.getMyProfile(true).choices;
        var response = switch (curDialogue.get(0)) {
            case S2C0 -> "I never knew you were so thoughtful.";
            case S2C1 -> "Oh, I see, well, it's still beautiful";
            case S2C2 -> "Awe, you're so sweet.. This is such a wonderful place.";
            case S2C3 -> "Wow, I cant believe you thought of that!";
            default -> throw new IllegalArgumentException("X");
        };

        var txtEmit = new TextEmitter(response + " " +
                "\nThe sky is wonderful, \nand oh, the grass, evergreen. \nOh, " +
                rl.getName() +
                ", Thank you so much!\n"
                + "I'm so glad I met you."
                )
                .setJText(textBox)
                .addSub(this);
        new Timer(75, txtEmit).start();
        getMainBGround().add(textBox);

        var gbc = new GridBagConstraints();
        gbc.gridy = SOUTH;
        getMainBGround().add(next, gbc);
        rl.getFrame().jf.pack();
    }
    @Override
    public void update(Event<Boolean> event) {
        if(event.getState())
        {
            sp.stop("type");
            next.toggleVis();
        }

    }
}
