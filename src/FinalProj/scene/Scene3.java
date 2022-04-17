package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.TextBox;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.Subs;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.SOUTH;

public class Scene3 extends BasicContainer implements Subs<Boolean> {
    ResourceLoader rl;
    private final JButton next = new JButton(">");
    public Scene3(ResourceLoader rl)
    {
        super();
        this.rl = rl;
        final ImageIcon backgroundImage = new ImageIcon(rl.getPicture("textScene").resize(1.5f).getImage());
        var panel = Game.mainPanel(backgroundImage);
        var tutFont = rl.getBitStrFont().deriveFont(20f);
        panel.setLayout(getLayout());

        super.setPreferredSize(panel.getPreferredSize());
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
            BasicContainer scene3 = new Scene3(rl);
            rl.getFrame().getContentPane().add(scene3, "Scene3");
            //transition
            Game.transitionScene(this, "Scene3");
            //request focus
            scene3.requestFocus();
        });

        var narrate = new Timer(75, txtEmit);
        panel.add(textBox);
        //panel.add(next, gbc);
        narrate.start();
        String[][] splashLayout = {
                {"Scene3"},
        };
        setStringLayout(splashLayout);
        add("Scene3", panel);

    }

    @Override
    public void update(Event<Boolean> event) {
        if(event.getState())
        {
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
