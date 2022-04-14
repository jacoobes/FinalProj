package FinalProj.utils;

import FinalProj.utils.events.Event;
import basicgraphics.BasicFrame;
import basicgraphics.images.Picture;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ResourceLoader implements Subs<String> {
    private final HashMap<String, Picture> ImageMap = new HashMap<>();
    private Font bitStr;
    private String inputName;
    private BasicFrame dad;
    public ResourceLoader() {

        //Loading BitStream
        try {
            var location = new File("src/FinalProj/resources/fonts/Bitstream Vera Sans Mono Bold Nerd Font Complete Mono Windows Compatible.ttf");
            var f = Font.createFont(
                    Font.TRUETYPE_FONT,
                    location);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(f);
            bitStr = f;
        }  catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

       //Loading ImageMap
        var forest = new Picture("forest_background.png");
        ImageMap.put("forest", forest);
        var titleScreen = new Picture("titleScreen.png");
        ImageMap.put("title", titleScreen);
        var textScene = new Picture("textScene.png");
        ImageMap.put("textScene", textScene);
        var favico = new Picture("favico.png");
        ImageMap.put("favico", favico);
        var tutorial = new Picture("tutorial.png");
        ImageMap.put("tutorial", tutorial);
        var scene2 = new Picture("scene2.png");
        ImageMap.put("scene2",scene2);

    }
    public void setBf(BasicFrame bf) { this.dad = bf; }
    public BasicFrame getFrame () { return dad; }
    public Font getBitStrFont() {
        return bitStr;
    }
    public Picture getPicture(String key) {
        return ImageMap.get(key);
    }

    @Override
    public void update(Event<String> event) {
        String name = event.getState();
        System.out.printf("Set the player's name to %s\n", name);
        inputName = name;
    }


    public String getName()
    {
        return inputName;
    }
}
