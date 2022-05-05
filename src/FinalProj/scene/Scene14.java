package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.components.TextBox;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;
import javax.swing.*;
import java.awt.*;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.SOUTH;

public class Scene14 extends SceneAlpha {
    private final ChoiceButton next = new ChoiceButton(">").addFont(getGameFont(20f));
    public Scene14(ResourceLoader rl)
    {
        super(rl,new ImageIcon(rl.getPicture("blackground").getImage()) );
        var tutFont = getGameFont(20f);

        super.setPreferredSize(getMainBGround().getPreferredSize());
        JTextArea textBox = new TextBox(tutFont);
        textBox.setVisible(true);
        var text ="      I couldn't help her. I swear. \n"
                + "      It was coming for us!\n"
                + "      It's not my fault....\n";
        var txtEmit = new TextEmitter(text)
                .setJText(textBox)
                .addSub(this);

        var gbc = new GridBagConstraints();
        gbc.gridy = SOUTH;
        gbc.gridx = CENTER;
        next.setFont(tutFont);
        next.setVisible(false);
        next.addActionListener(e -> {
            sp.stop("type");
            BasicContainer scene5 = new Scene15(rl);
            rl.getFrame().getContentPane().add(scene5, Scene15.class.getName());
            //transition
            Game.transitionScene(this, Scene15.class.getName());
            //request focus
            scene5.requestFocus();
        });

        var narrate = new Timer(75, txtEmit);
        var gbcTxtBox = new GridBagConstraints();
        gbcTxtBox.gridx = CENTER;
        getMainBGround().add(textBox, gbcTxtBox);
        getMainBGround().add(next, gbc);
        narrate.start();
        rl.getFrame().jf.pack();
        try {
            sp.loop("type",-1);
        } catch ( Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void update(Event<Boolean> event) {

        if(event.getState())
        {
            sp.stop("type");
            System.out.println("Scene 4 finished");
            next.setVisible(true);
        }
    }
}
