package FinalProj.utils;

import FinalProj.utils.events.Event;
import basicgraphics.BasicFrame;
import basicgraphics.images.Picture;
import basicgraphics.sounds.ReusableClip;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ResourceLoader implements Subs<String> {
    private YamlParser.Data data;
    private final HashMap<String, Picture> ImageMap = new HashMap<>();
    private Font bitStr;
    private BasicFrame dad;
    private final SoundPlayer sp = new SoundPlayer();
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
        var blackground = new BufferedImage(1200,800, BufferedImage.TYPE_BYTE_GRAY);
        ImageMap.put("blackground", new Picture(blackground));

        try {
          sp.newSound("brown", "Super Deep Brown Noise (1 Hour).wav");
          sp.newSound("type", "typewriter-1.wav");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void setBf(BasicFrame bf) { this.dad = bf; }
    public BasicFrame getFrame () { return dad; }
    public Font getBitStrFont() {
        return bitStr;
    }
    public Picture getPicture(String key) {
        return ImageMap.get(key);
    }
    public SoundPlayer getSoundPlayer() { return sp; }
    @Override
    public void update(Event<String> event) {
        String name = event.getState();
        System.out.printf("Set the player's name to %s\n", name);
        this.data.profile = name;
    }

    public void addGuilt(int guilt) {
        this.data.guilt += guilt;
    }

    public String getName()
    {
        return this.data.profile;
    }
}
