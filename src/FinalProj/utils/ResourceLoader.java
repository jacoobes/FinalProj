package FinalProj.utils;

import basicgraphics.BasicFrame;
import basicgraphics.images.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Main loading class to manage all outgoing and incoming resources from scenes
 */
public class ResourceLoader{

    // these constants are choices for scenes. the notation for variables =
    // regex -> S\dC\d S = Scene, C = Choice
    public static final String S2C0 = "We first met here.";
    public static final String S2C1 = "It was too congested inside.";
    public static final String S2C2 = "I would take you anywhere.";
    public static final String S2C3 = "Your favorite flowers are here.";

    public static final String S6C0 = "I will care for you always.";
    public static final String S6C1 = "Good, now you're my housewife.";
    public static final String S6C2 = "I will protect you from anything.";
    public static final String S6C3 = "I will love you forever.";

    public static final String S8C0 = "Say : excuse me?";
    public static final String S8C1 = "Say nothing.";
    public static final String S8C2 = "Say : Ignore him, dear.";
    public static final String S8C3 = "Look at him.";

    //Adds a choice made by the user to the current profile
    private final Subs<String> buttonDialogue = event -> {
        String choice = event.getState();
        System.out.printf("Added a current choice to player %s\n", choice);
        this.getMyProfile(true).addChoice(choice);
    };
    //Sets the name of the currentProfileUser.
    private final Subs<String> nameSub = event -> {
        String name = event.getState();
        System.out.printf("Set the player's name to %s\n", name);
        this.myProfile.name = name;
    };


    private YamlParser.Data myProfile;
    private YamlParser.Data backupData;

    //Loads the current profile.
    private final Subs<YamlParser.Data> currentProfile = event -> {
        YamlParser.Data myp = event.getState();
        System.out.println("Loaded : "+myp);
        myProfile = event.getState();
        try
        {
            backupData = event.getState().clone();
        } catch (Exception e) {
            System.out.println("Warning : could not backup data!" );
        }
    };
    public final YamlParser yamlizer = new YamlParser("src/FinalProj/resources/database");
    private final HashMap<String, Picture> ImageMap = new HashMap<>();
    private Font bitStr;
    private BasicFrame dad;
    private final SoundPlayer sp = new SoundPlayer();
    public ResourceLoader() {
        myProfile = null;
        backupData = null;
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
        var titleScreen = new Picture("titleScreen.png");
        ImageMap.put("title", titleScreen);
        var textScene = new Picture("textScene.png");
        ImageMap.put("textScene", textScene);
        var favico = new Picture("favico.png");
        ImageMap.put("favico", favico);
        var tutorial = new Picture("tutorial.png");
        ImageMap.put("tutorial", tutorial);
        var blackground = new BufferedImage(1200,800, BufferedImage.TYPE_BYTE_GRAY);
        ImageMap.put("blackground", new Picture(blackground));
        var happy = new Picture("happy.png");
        ImageMap.put("happy", happy);
        var sunset = new Picture("sunset.png");
        ImageMap.put("sunset", sunset);
        var cabin = new Picture("cabin.png");
        ImageMap.put("cabin", cabin);
        try {
          sp.newSound("happy", "happyscenes.wav");
          sp.newSound("brown", "Super Deep Brown Noise (1 Hour).wav");
          sp.newSound("type", "typewriter-1.wav");
          sp.newSound("breath", "breath.wav");
          sp.setVolume("breath", 1f);
          sp.setVolume("type", .2f);
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
    public Subs<String> getButtonDialogue() { return buttonDialogue; }

    public String getName()
    {
        return this.myProfile.name;
    }

    /**
     *
     * @param save - which profile to access, the current unsaved profile or the backup
     * @return - YamlParser.Data
     */
    public YamlParser.Data getMyProfile(boolean save)
    {
        if(save)
        {
            return myProfile;
        }
        return backupData;
    }


}
