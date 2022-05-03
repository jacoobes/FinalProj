package FinalProj.scene;

import FinalProj.utils.ResourceLoader;
import FinalProj.utils.events.Event;

import javax.swing.*;

public class Scene3 extends SceneAlpha {
    public Scene3(ResourceLoader rl)
    {
        super(rl, new ImageIcon(rl.getPicture("happy").resize(1.5f).getImage()));
        if(!sp.isPlaying("happy"))
        {
            try
            {
                sp.loop("happy",-1);
            } catch(Throwable e)
            {
                System.out.println(e);
            }
        }
    }
    @Override
    public void update(Event<Boolean> event) {}
}
