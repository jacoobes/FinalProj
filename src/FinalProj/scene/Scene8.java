package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.components.QuadChoicePanel;
import FinalProj.components.SemiTransparentTextField;
import FinalProj.utils.Publisher;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.Subs;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;
import basicgraphics.Clock;
import javax.swing.*;
import java.awt.*;

import static FinalProj.utils.ResourceLoader.*;


public class Scene8 extends SceneAlpha {
    private final ChoiceButton next = new ChoiceButton(">");
    private final Publisher<String> pub = new Publisher<>();
    private boolean shouldStop;
    private final JPanel textPanel = new JPanel(new GridBagLayout());
    private final SemiTransparentTextField speechBubble = new SemiTransparentTextField(getGameFont(20f));

    private final Subs<String> continueScene = event -> {
        var textEmitter = new TextEmitter("").addSub(this).setJText(speechBubble);
        switch(event.getState())
        {
            case S8C0 ->
                    textEmitter.setText("Pardon me?");
            case S8C1 ->
                    textEmitter.setText("...................");
            case S8C2 ->
                    textEmitter.setText("Let us go, my love. Sorry, sir.");
            case S8C3 ->
                    textEmitter.setText("You stare at his expressionless face.\n He continues looking at the sky.");
        }
        new Timer(75, textEmitter).start();
    };

    public Scene8(ResourceLoader rl) {
        super(rl, new ImageIcon(rl.getPicture("sunset").resize(1.5f).getImage()));
        sp.stop("happy");
        Clock.addTask(mainCharac.animate());
        try
        {
          sp.setVolume("breath",.1f);
          sp.loop("breath",-1);
        } catch (Exception ignored) {}
        var choicePanel = new QuadChoicePanel(new ChoiceButton[] {
                new ChoiceButton(S8C0).addFont(getGameFont(20f)),
                new ChoiceButton(S8C1).addFont(getGameFont(20f)),
                new ChoiceButton(S8C2).addFont(getGameFont(20f)),
                new ChoiceButton(S8C3).addFont(getGameFont(20f))
        });
        var choiceButtons = choicePanel.getButtons();


        next.addActionListener(e -> {
            Scene9 sc9 = new Scene9(resourceLoader);
            resourceLoader.getFrame().getContentPane().add(sc9,Scene9.class.getName());
            Game.transitionScene(this, Scene9.class.getName());
        });
        pub.addSubscriber(rl.getButtonDialogue());
        for(int i = 0; i < choiceButtons.size(); i++)
        {
            if(i == 3)
            {
                choiceButtons.get(i).addActionListener(e -> {
                    choicePanel.setVisible(false);
                    sc.setVisible(false);
                    speechBubble.setVisible(true);
                    textPanel.setVisible(true);
                    try {
                      sp.loop("type", 1);
                    } catch (Exception ignored) {}
                    pub.addSubscriber(continueScene);
                    pub.notifySubs(() -> ((ChoiceButton) e.getSource()).getText());
                });
            } else
            {
                choiceButtons.get(i).addActionListener(e-> {
                    Clock.start(150);
                    choicePanel.setVisible(false);
                    sc.setVisible(true);
                    shouldStop = true;
                    textPanel.setVisible(true);
                    speechBubble.setVisible(true);
                    pub.addSubscriber(continueScene);
                    pub.notifySubs(() -> ((ChoiceButton) e.getSource()).getText());
                });
            }

        }

        textPanel.setVisible(false);
        textPanel.setOpaque(false);
        sc.setVisible(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        textPanel.add(speechBubble,gbc);
        textPanel.add(next,gbc);
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
        sp.stop("type");
        next.toggleVis();
        if (shouldStop)
        {
            Clock.stop();
        }
    }
}
