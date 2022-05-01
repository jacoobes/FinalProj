package FinalProj.scene;

import FinalProj.components.ChoiceButton;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.events.Event;

import javax.swing.*;

public class Scene7 extends SceneAlpha {
    private final ChoiceButton cb = new ChoiceButton(">");
    public Scene7(ResourceLoader rl) {
        super(rl, new ImageIcon(rl.getPicture("blackground").getImage()));
        cb.setFont(getGameFont(20f));
    }

    @Override
    public void update(Event<Boolean> event) {
        if(event.getState())
        {
            cb.toggleVis();
        }
    }
}