package FinalProj.components;

import FinalProj.Game;
import FinalProj.scene.Scene1;
import FinalProj.scene.Tutorial;
import FinalProj.utils.Publisher;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.events.NameEvent;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class InputName extends JTextField {
    private final Publisher<String> subject = new Publisher<>();
    public InputName(int columns, ResourceLoader rl)
    {
        super(columns);
        setFont(rl.getBitStrFont().deriveFont(50f));
        setPreferredSize(new Dimension(75,75));
        subject.addSubscriber(rl.getNameSub());

        addActionListener(e -> {
            Component grandparent = getParent().getParent();
            //Enter key
            if (e.getID() == 1001)
            {
                if (!isValidName(getText()))
                {
                    JOptionPane.showMessageDialog(getParent(), "This name cannot be used");
                } else
                {
                    var confirmDialog = JOptionPane.showConfirmDialog(getParent(),
                            String.format("Are you sure you want to use the name %s?", getText()));
                    if (confirmDialog == 0)
                    {
                        //Notify resource loader a name has been selected
                        subject.notifySubs(new NameEvent(getText()));
                        // generate a new basic container with new scene
                        BasicContainer bc1 = new Tutorial(rl);
                        // add the basic container to the basic frame
                        rl.getFrame().getContentPane().add(bc1, Tutorial.class.getName());
                        //transition
                        rl.getFrame().jf.pack();
                        Game.transitionScene((BasicContainer) grandparent, Tutorial.class.getName());
                        bc1.requestFocus();
                    } else
                    {
                        setText("");
                    }
                }
            }
        });
    }
    private boolean isValidName(String s) {
        var pat = Pattern.compile("[A-Za-z]+ ?[A-Za-z]*");
        return pat.matcher(s).matches();
    }

}
