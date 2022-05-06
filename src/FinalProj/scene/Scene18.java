package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.components.TextBox;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.TextEmitter;
import FinalProj.utils.events.Event;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.SOUTH;

//sad ending
public class Scene18 extends SceneAlpha {
    private int finalButton;
    private final JTextArea textBox = new TextBox(getGameFont(20f));
    private final ChoiceButton cb = new ChoiceButton(">").addFont(getGameFont(20f));
    public Scene18(ResourceLoader rl)
    {
        super(rl, new ImageIcon(rl.getPicture("blackground").getImage()));


        textBox.setForeground(Color.lightGray);
        textBox.setVisible(true);
        textBox.setOpaque(false);
        textBox.setForeground(Color.lightGray);
        int guilt = rl.getMyProfile(true).guilt;
        System.out.println(resourceLoader.getMyProfile(true));

        var text = guilt <= 20 ? """
              I loved her...
              I couldn't do anything.
              It's not my fault...
              Why was he there..
           
              """ : """
                I can't take the guilt.
                Piece of shit...
                Why was he there...
                ....
                
                ...
                """;

        var txtEmit = new TextEmitter(text)
                .setJText(textBox)
                .addSub(this);

        var narrator = new Timer(50, txtEmit);
        narrator.start();
        var gbc = new GridBagConstraints();
        gbc.gridy = SOUTH;
        gbc.gridx = CENTER;
        var gbcTxtBox = new GridBagConstraints();
        gbcTxtBox.gridx = CENTER;

        getMainBGround().add(textBox,gbcTxtBox);
        getMainBGround().add(cb,gbc);

    }
    @Override
    public void update(Event<Boolean> event) {
        sp.stop("type");
        cb.toggleVis();

        finalButton++;
        if(finalButton > 1)
        {
            cb.removeActionListener(cb.getActionListeners()[0]);
        }
        if(finalButton == 1)
        {
            cb.addActionListener(e -> {
                try
                {
                  sp.loop("type",-1);
                } catch (Exception ignored) {}
                cb.toggleVis();
                textBox.setText("");
                var textEmitter = new TextEmitter(
                        resourceLoader.getMyProfile(true)
                                .guilt <= 20 ? """
                            My love.. I'm so sorry...
                            I should have stuck up for you..
                            
                            ... My love...
                            """ : """
                            After returning to the cabin,
                            you spot the shotgun you used to hunt
                            wild deer.
                            
                            Picking it up, you aim it in your mouth..
                            
                            You close your eyes.
                            """

                )
                        .addSub(this)
                        .setJText(textBox);
                new Timer(50, textEmitter).start();
            });
        }

        if(finalButton == 2)
        {
            cb.addActionListener(e -> {

                try
                {
                    resourceLoader.yamlizer.dump(resourceLoader.getMyProfile(true));
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                cb.toggleVis();
                cb.setText("Main Menu");
                textBox.setText("");
                textBox.setFont(getGameFont(75f));
                var textEmitter = new TextEmitter(
                        "Guilt"
                )
                        .addSub(this)
                        .setJText(textBox);
                new Timer(1000, textEmitter).start();
            });
        }
        if(finalButton == 3)
        {

            cb.addActionListener(e -> {
                sp.stop("brown");
                resourceLoader.getFrame().getContentPane().remove(1);
                ProfileSelection ps = new ProfileSelection(resourceLoader);
                resourceLoader.getFrame().getContentPane().add(ps, ProfileSelection.class.getName());
                Game.transitionScene(this, Title.class.getName());
            });
        }
    }
}
