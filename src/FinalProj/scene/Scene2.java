package FinalProj.scene;

import FinalProj.Game;
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

public class Scene2 extends SceneAlpha {
    private static final Publisher<String> pub = new Publisher<>();
    private final ChoiceButton next = new ChoiceButton(">");
    private final SpriteComponent sc = new SpriteComponent();
    private final MC joe = new MC(sc);
    private static final HashMap<Integer, Integer> resolveMap = new HashMap<>();
    private final SemiTransparentTextField speechBubble = new SemiTransparentTextField(getGameFont(20f));
    static
    {
        resolveMap.put(0,10);
        resolveMap.put(1,5);
        resolveMap.put(2,30);
        resolveMap.put(3,30);
    }


    private final Subs<String> continueScene = event -> {
        var textEmitter = new TextEmitter("").addSub(this).setJText(speechBubble);
        switch (event.getState())
        {
            case "We first met here."-> {
                    textEmitter.setText("This was where we first met of course!");
            }
            case "It was too congested inside." -> {
                textEmitter.setText("We needed to go outside, it was too congested!");
            }
            case "I would take you anywhere." -> {
                textEmitter.setText("My love, I'd take you anywhere to make you happy.");
            }
            case "Your favorite flowers are here." -> {
                textEmitter.setText("Your favorite lilies of course!");
            }
        }
        new Timer(75, textEmitter).start();
    };

    public Scene2(ResourceLoader rl) {
        super(rl, new ImageIcon(rl.getPicture("happy").resize(1.5f).getImage()));
        sc.setPreferredSize(new Dimension(300, 600));
        Clock.addTask(joe.animate());
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

        var choicePanel = new QuadChoicePanel(new ChoiceButton[] {
           new ChoiceButton("We first met here.").addFont(getGameFont(20f)),
           new ChoiceButton("It was too congested inside.").addFont(getGameFont(20f)),
           new ChoiceButton("I would take you anywhere.").addFont(getGameFont(20f)),
           new ChoiceButton("Your favorite flowers are here.").addFont(getGameFont(20f))
        });
        var choiceButtons = choicePanel.getButtons();

        pub.addSubscriber(rl.getButtonDialogue());

        var textPanel = new JPanel(new GridBagLayout());
        textPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        textPanel.add(speechBubble, gbc);
        textPanel.add(next, gbc);

        speechBubble.setForeground(Color.darkGray);

        textPanel.setVisible(false);
        sc.setVisible(false);


        next.addActionListener(e -> {
            var sc3 = new Scene3(rl);
            rl.getFrame().getContentPane().add(sc3,Scene3.class.getName());
            Game.transitionScene(this, Scene3.class.getName());
        });

        for(int i = 0; i < choiceButtons.size(); i++)
        {
            int finalI = i;
            choiceButtons.get(i).addActionListener(e -> {
                choicePanel.setVisible(false);

                sc.setVisible(true);
                textPanel.setVisible(true);
                speechBubble.setVisible(true);

                rl.getMyProfile(true).addResolve(resolveMap.get(finalI));
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
        getMainBGround().add(choicePanel,gbcPanel);
        rl.getFrame().jf.pack();
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
