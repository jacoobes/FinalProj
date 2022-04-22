package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.TextBox;
import FinalProj.utils.Publisher;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.Subs;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import FinalProj.utils.events.TaskFinishedEvent;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.Vector;

import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.SOUTH;

//Black scene and transition to game
public class Scene4 extends BasicContainer implements Subs<Boolean> {
    private final JButton next = new JButton(">");
    public Scene4(ResourceLoader rl)
    {
        super();
        final ImageIcon backgroundImage = new ImageIcon(rl.getPicture("blackground").getImage());
        var panel = Game.mainPanel(backgroundImage);
        var tutFont = rl.getBitStrFont().deriveFont(20f);
        panel.setLayout(getLayout());

        super.setPreferredSize(panel.getPreferredSize());
        JTextArea textBox = new TextBox(tutFont);
        textBox.setVisible(true);
        var text ="      I couldn't help her. I swear. \n"
                + "      They were coming for us!\n"
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
            BasicContainer scene4 = new Scene4(rl);
            rl.getFrame().getContentPane().add(scene4, "Scene4");
            //transition
            Game.transitionScene(this, "Scene4");
            //request focus
            scene4.requestFocus();
        });

        var narrate = new Timer(75, txtEmit);
        var gbcTxtBox = new GridBagConstraints();
        gbcTxtBox.gridx = CENTER;
        panel.add(textBox, gbcTxtBox);
        panel.add(next, gbc);
        narrate.start();
        String[][] splashLayout = {
                {"Scene4"},
        };
        setStringLayout(splashLayout);
        add("Scene4", panel);
    }

    @Override
    public void update(Event<Boolean> event) {

        if(event.getState())
        {
            System.out.println("Scene 4 finished");
            next.setVisible(true);
        }
    }
}
