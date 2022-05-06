package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.ChoiceButton;
import FinalProj.utils.Publisher;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.SceneTracker;
import FinalProj.utils.YamlParser;
import FinalProj.utils.events.CurrentProfileSelectEvent;
import FinalProj.utils.events.Event;
import edu.lsu.cct.piraha.Ex;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ProfileSelection extends SceneAlpha {
    private final Publisher<YamlParser.Data> publisher = new Publisher<>();

    public ProfileSelection(ResourceLoader rl) {
        super(rl, new ImageIcon(rl.getPicture("title").resize(1.5f).getImage()));

        publisher.addSubscriber(rl.profileSub());

        YamlParser.Data[] profiles = rl.getYamlData();
        JPanel[] jps = new JPanel[3];
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(40, 0, 40, 0);
        for (int i = 0; i < profiles.length; i++)
        {
            YamlParser.Data d = profiles[i];
            jps[i] = new JPanel(new FlowLayout());
            jps[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            jps[i].setBackground(new Color(245, 245, 220));

            JLabel jl = new JLabel(resolveProfileName(profiles[i]));
            jl.setFont(getGameFont(20f));
            jl.setForeground(Color.BLACK);
            jps[i].add(jl);
            ChoiceButton del = new ChoiceButton("Delete");
            del.addActionListener(e -> {
                if(d.name == null)
                {
                     JOptionPane.showMessageDialog(
                            this,
                            "You cannot delete a new profile!"
                    );
                     return;
                }
                int res = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this profile?",
                        null,
                        JOptionPane.YES_NO_OPTION
                );
                if(res == 0)
                {
                    try
                    {
                      var data = new YamlParser.Data();
                      data._fp = d._fp;
                      resourceLoader.yamlizer.dump(data);
                    } catch (Exception ex) {}
                    JOptionPane.showMessageDialog(this,
                            """
                                    Restart Guilt to get the changes.
                                    """);
                }

            });
            del.setFont(getGameFont(20f));
            del.setVisible(true);
            jps[i].add(del);
            ChoiceButton cb = new ChoiceButton("Select");
            cb.addActionListener(e -> {
                if (d.name == null)
                {
                    int res = JOptionPane.showConfirmDialog(
                            this,
                            "No profile found! Create a new one?",
                            null,
                            JOptionPane.YES_NO_OPTION
                    );
                    if (res == 0)
                    {
                        System.out.println(d);
                        publisher.notifySubs(new CurrentProfileSelectEvent(d));
                        Game.transitionScene(this, NameSelect.class.getName());
                    }
                } else
                {
                        try
                        {
                            rl.profileSub().update(new CurrentProfileSelectEvent(d));

                             @SuppressWarnings("unchecked")
                            Constructor<? extends SceneAlpha> cons = (Constructor<? extends SceneAlpha>)
                                    Class
                                        .forName("FinalProj.scene." + d.savedScene)
                                        .getConstructor(ResourceLoader.class);
                            var scene = cons.newInstance(rl);
                            SceneTracker.movePointer(
                                    Integer.parseInt(d.savedScene.substring("Scene".length())) -1
                            );
                            System.out.println("Teleporting to " + d.savedScene);
                            rl.getFrame().getContentPane().add(scene, d.savedScene);
                        } catch (
                                InstantiationException
                                    | IllegalAccessException
                                    | ClassNotFoundException
                                    | NoSuchMethodException
                                    | InvocationTargetException ex
                        )
                        {
                            ex.printStackTrace();
                        }
                        Game.transitionScene(this, d.savedScene);
                        if (d.name == null)
                        {
                            publisher.notifySubs(new CurrentProfileSelectEvent(d));
                        }
                    }
            });
            cb.setFont(getGameFont(20f));
            cb.setVisible(true);
            jps[i].add(cb);

            getMainBGround().add(jps[i], gbc);
        }
        rl.getFrame().jf.pack();
    }

    public String resolveProfileName(YamlParser.Data d)  {
        return d.name == null ? "New Profile" : d.name;
    }

    @Override
    public void update(Event<Boolean> event) {
    }
}
