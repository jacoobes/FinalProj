package FinalProj;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;
import basicgraphics.images.Picture;



public class MC extends Sprite {
    Picture[] frames = new Picture[4];
    private int curFrame;
    public MC(SpriteComponent sc)
    {
        super(sc);
        for (int i = 0; i < frames.length; i++) {
            frames[i] = new Picture(String.format("mc_talking-%s.png", i));
        }
        setX(750);
        setY(400);
        setPicture(frames[0]);
    }
    public Task animate() {
        return new Task() {
            @Override
            public void run() {
                if(curFrame == 4) curFrame = 0;
                setPicture(frames[curFrame]);
                curFrame++;
            }
        };

    }


}
