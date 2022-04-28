package FinalProj.scene;

import FinalProj.utils.ResourceLoader;
import FinalProj.utils.events.Event;
import basicgraphics.BasicContainer;

import javax.swing.*;

public class Scene6 {


    public static class Ignore extends SceneAlpha {

        protected Ignore(ResourceLoader rl) {
            super(rl, new ImageIcon(rl.getPicture("blackground").getImage()));
        }

        @Override
        public void update(Event<Boolean> event) {

        }
    }

    public static class LookBack extends SceneAlpha {
        public LookBack(ResourceLoader rl) {
            super(rl, new ImageIcon(rl.getPicture("blackground").getImage()));
        }

        @Override
        public void update(Event<Boolean> event) {

        }
    }
}
