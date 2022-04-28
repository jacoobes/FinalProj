package FinalProj.utils;

import basicgraphics.BasicFrame;
import basicgraphics.images.Picture;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ResourceLoader{
    private YamlParser.Data myProfile;

    private final Subs<String> nameSub = event -> {
        String name = event.getState();
        System.out.printf("Set the player's name to %s\n", name);
        this.myProfile.name = name;
    };
    private final Subs<YamlParser.Data> currentProfile = event -> {
        YamlParser.Data myp = event.getState();
        System.out.println("Loaded : "+myp);
        myProfile = event.getState();
    };
    public final YamlParser yamlizer = new YamlParser("src/FinalProj/resources/database");
    private final HashMap<String, Picture> ImageMap = new HashMap<>();
    private Font bitStr;
    private BasicFrame dad;
    private final SoundPlayer sp = new SoundPlayer();
    public ResourceLoader() {
        myProfile = null;
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
          sp.setVolume("type", .3f);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public YamlParser.Data[] getYamlData() {
        return yamlizer.getProfiles();
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

    public Subs<String> getNameSub() {
        return nameSub;
    }
    public Subs<YamlParser.Data> profileSub() {
        return currentProfile;
    }
    public void addGuilt(int guilt) {
        this.myProfile.guilt += guilt;
    }
    public String getName()
    {
        return this.myProfile.name;
    }
    public YamlParser.Data getMyProfile() {
        return myProfile;
    }
}
