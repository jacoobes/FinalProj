package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.TextBox;
import FinalProj.utils.Publisher;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.Subs;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.SOUTH;

public class Scene2 extends BasicContainer implements Subs<Boolean> {
    ResourceLoader rl;
    private final JButton next = new JButton(">");
    public Scene2(ResourceLoader rl)
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
            BasicContainer scene3 = new Scene3(rl);
            rl.getFrame().getContentPane().add(scene3, "Scene3");
            //transition
            Game.transitionScene(this, "Scene3");
            //request focus
            scene3.requestFocus();
        });

        var narrate = new Timer(75, txtEmit);
        panel.add(textBox);
        panel.add(next, gbc);
        narrate.start();
        String[][] splashLayout = {
                {"Scene2"},
        };
        setStringLayout(splashLayout);
        add("Scene2", panel);

    }

    @Override
    public void update(Event<Boolean> event, Publisher<Boolean> p) {
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
