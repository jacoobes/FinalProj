package FinalProj.scene;

import FinalProj.MC;
import FinalProj.components.ChoiceButton;
import FinalProj.components.QuadChoicePanel;
import FinalProj.components.SemiTransparentTextField;
import FinalProj.utils.Publisher;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.Subs;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.Clock;
import basicgraphics.SpriteComponent;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Scene6 extends SceneAlpha {
    private final SpriteComponent sc = new SpriteComponent();
    private final MC joe = new MC(sc);
    private final ChoiceButton next = new ChoiceButton(">");
    private final Publisher<String> pub = new Publisher<>();
    private final SemiTransparentTextField speechBubble = new SemiTransparentTextField(getGameFont(20f));
    private final JPanel textPanel = new JPanel(new GridBagLayout());
    private static final HashMap<Integer,Integer> resolveMap = new HashMap<>();
    static
    {
        resolveMap.put(0, 20);
        resolveMap.put(1, 0);
        resolveMap.put(2, 30);
        resolveMap.put(3, 15);
    }
    private final Subs<String> continueScene = event -> {
        var textEmitter = new TextEmitter("").addSub(this).setJText(speechBubble);
          switch(event.getState())
          {
              case "I will care for you always." ->
                      textEmitter.setText("I will care and watch over you, no matter the condition.");
              case "Good, now you're my housewife." ->
                      textEmitter.setText("Now you can stay at home and cook food for us.");
              case "I will protect you from anything." ->
                      textEmitter.setText("No matter the issue, I will protect you with my life.");
              case "I will love you forever." ->
                      textEmitter.setText("My love only grows stronger, everlasting with you");
          }
        new Timer(75, textEmitter).start();
    };

    public Scene6(ResourceLoader resourceLoader) {
        super(resourceLoader, new ImageIcon(resourceLoader.getPicture("happy").resize(1.5f).getImage()));
        sc.setPreferredSize(new Dimension(300, 600));
        Clock.addTask(joe.animate());
        var choicePanel = new QuadChoicePanel(new ChoiceButton[] {
                new ChoiceButton("I will care for you always.").addFont(getGameFont(20f)),
                new ChoiceButton("Good, now you're my housewife.").addFont(getGameFont(20f)),
                new ChoiceButton("I will protect you from anything.").addFont(getGameFont(20f)),
                new ChoiceButton("I will love you forever.").addFont(getGameFont(20f))
        });
        pub.addSubscriber(resourceLoader.getButtonDialogue());
        var buttons  = choicePanel.getButtons();

        textPanel.setVisible(false);
        textPanel.setOpaque(false);
        sc.setVisible(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        textPanel.add(speechBubble, gbc);
        textPanel.add(next, gbc);
        speechBubble.setForeground(Color.darkGray);

        for(int i = 0 ; i < buttons.size(); i++)
        {
            int finalI = i;
            buttons.get(i).addActionListener(e -> {
                choicePanel.setVisible(false);

                sc.setVisible(true);
                textPanel.setVisible(true);
                speechBubble.setVisible(true);

                resourceLoader.getMyProfile(true).addResolve(resolveMap.get(finalI));
                pub.addSubscriber(continueScene);
                pub.notifySubs(() -> ((ChoiceButton) e.getSource()).getText());
                Clock.start(150);
            });
        }

        var gbcPanel = new GridBagConstraints();
        gbcPanel.insets = new Insets(300,0,0,0);
        gbcPanel.ipady = 150;

        getMainBGround().add(textPanel, new GridBagConstraints());
        getMainBGround().add(sc, new GridBagConstraints());
        getMainBGround().add(choicePanel, gbcPanel);
        resourceLoader.getFrame().jf.pack();
    }



    @Override
    public void update(Event<Boolean> event) {
        if(event.getState())
        {
            Clock.stop();
            next.toggleVis();
        }
    }
}
