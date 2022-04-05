package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.TutorialParagraph;
import FinalProj.utils.OrderedTask;
import FinalProj.utils.ResourceLoader;
import basicgraphics.BasicContainer;
import basicgraphics.Clock;
import basicgraphics.Task;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Vector;

import static java.awt.GridBagConstraints.FIRST_LINE_START;

public class Tutorial extends BasicContainer {
    public Tutorial(ResourceLoader rl)
    {
        super();
        var tutFont = rl.getBitStrFont().deriveFont(20f);
        var panel = Game.mainPanel(new ImageIcon(rl.getPicture("tutorial").resize(1.5f).getImage()));
        panel.setLayout(getLayout());

        JTextArea tutorialParagraph = new TutorialParagraph(tutFont);
        System.out.println(panel.getLayout());
        JButton startTut = new JButton("Tutorial");
        startTut.setFont(tutFont);

        var narration = new LinkedList<Task>();
        String text = """
                For cinematic scenes, click once and let it narrate.
                For choice boxes, click on one of the four options.
                Every choice will affect the ending of the game""";
        narration.add(Game.textTask(text, tutorialParagraph));
        var task = new OrderedTask(narration);
        Clock.addTask(task);
        startTut.addActionListener(e -> {
            startTut.setVisible(false);
            tutorialParagraph.setVisible(true);
            Clock.start(250);

        });
        panel.add(tutorialParagraph);
        panel.add(startTut);

        String[][] splashLayout = {
                {"Tutorial"},
        };

        setStringLayout(splashLayout);
        add("Tutorial", panel);
    }
}
