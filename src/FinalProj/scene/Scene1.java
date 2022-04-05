package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.OnClick;
import FinalProj.utils.OrderedTask;
import FinalProj.utils.Poller;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.Subs;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;
import basicgraphics.Clock;
import basicgraphics.Task;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Vector;

public class Scene1 extends BasicContainer implements Subs<Boolean> {
    ResourceLoader rl;
    Poller poller = new Poller(100);
    public Scene1(ResourceLoader rl) {
        super();
        this.rl = rl;
        final ImageIcon backgroundImage = new ImageIcon(rl.getPicture("textScene").resize(1.5f).getImage());
        var panel = Game.mainPanel(backgroundImage);

        panel.setLayout(getLayout());


        var textBox = new JLabel();
            textBox.setFont(rl.getBitStrFont().deriveFont(20f));
            textBox.setForeground(Color.LIGHT_GRAY);
        var narration = new LinkedList<Task>();
            narration.add(Game.textTask("Help me", textBox));
            narration.add(Game.textTask("Please...", textBox));
            narration.add(Game.textTask(rl.getName(), textBox));
        var tasks = new OrderedTask(narration);
        Clock.addTask(tasks);


        panel.add(textBox);

        var onClick = new OnClick(() -> {
            poller.setTasks(tasks);
            poller.addSub(this);
            poller.start();
            Clock.start(1500);
        });

        addMouseListener(onClick);

        String[][] splashLayout = {
                {"Scene1"},
        };
        setStringLayout(splashLayout);
        add("Scene1", panel);
    }

    @Override
    public void update(Event<Boolean> event) {
        if(event.getState())
        {
            System.out.println("Scene1 has finished");
            poller.stop();
            BasicContainer scene2 = new Scene2(rl);
            rl.getFrame().getContentPane().add(scene2, "Scene2");
            //transition
            Game.transitionScene(this, "Scene2");
            //request focus
            scene2.requestFocus();
        }
    }

}
