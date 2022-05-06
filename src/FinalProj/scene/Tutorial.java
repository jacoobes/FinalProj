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


public class Tutorial extends BasicContainer implements Subs<Boolean> {
    private final JButton startGame = new JButton("Start");
    ResourceLoader rl;
    public Tutorial(ResourceLoader rl)
    {
        super();
        this.rl = rl;
        var tutFont = rl.getBitStrFont().deriveFont(20f);
        var panel = Game.mainPanel(new ImageIcon(rl.getPicture("tutorial").resize(1.5f).getImage()));
        panel.setLayout(getLayout());

        setPreferredSize(panel.getPreferredSize());
        JTextArea tutorialParagraph = new TextBox(tutFont);

        JButton startTut = new JButton("Tutorial");
        startTut.setFont(tutFont);

        var gbc = new GridBagConstraints();
        gbc.gridy = SOUTH;
        startGame.setVisible(false);
        startGame.setFont(tutFont);

        String text = """
        For cinematic scenes, wait for the button to appear.
        For choice boxes, click one of the buttons.
        Every choice will affect the ending of the game.
        There are a possible of 3 endings. Choose wisely!""";

        var txtEmitter = new TextEmitter(text)
            .setJText(tutorialParagraph)
            .addSub(this);

        Timer t = new Timer(50, txtEmitter );
        startTut.addActionListener(e -> {
            startTut.setVisible(false);
            tutorialParagraph.setVisible(true);
            t.start();
        });

        panel.add(tutorialParagraph);
        panel.add(startTut);
        panel.add(startGame, gbc);

        String[][] splashLayout = {
                {"Tutorial"},
        };

        setStringLayout(splashLayout);
        add("Tutorial", panel);
    }

    @Override
    public void update(Event<Boolean> event) {
        if(event.getState())
        {
            System.out.println("Finished Tutorial");
            startGame.setVisible(true);
            startGame.addActionListener( e -> {
                Scene1 sc = new Scene1(rl);
                rl.getFrame().getContentPane().add(sc, Scene1.class.getName());
                Game.transitionScene(this, Scene1.class.getName());
                rl.getFrame().jf.pack();
            });
        }
    }
}
