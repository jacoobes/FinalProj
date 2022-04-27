package FinalProj.scene;

import FinalProj.utils.ResourceLoader;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;

public class Scene6 extends SceneAlpha {
    public Scene6(ResourceLoader rl) {
        super(rl,new ImageIcon(rl.getPicture("blackground").resize(1.5f).getImage()));
    }

    @Override
    public void update(Event<Boolean> event) {

    }
}
