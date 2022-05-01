package FinalProj.scene;

import FinalProj.components.ChoiceButton;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.events.Event;

import javax.swing.*;

public class Scene8 extends SceneAlpha {
    private final ChoiceButton cb = new ChoiceButton(">").addFont(getGameFont(20f));
    public Scene8(ResourceLoader rl)
    {
        super(rl, new ImageIcon(rl.getPicture("blackground").getImage()));

    }
    @Override
    public void update(Event<Boolean> event) {

    }
}
