package FinalProj.components;

import javax.swing.*;

public class ChoiceButton extends JButton  {
    public ChoiceButton(String text)
    {
        super(text);

        setVisible(false);
    }
    public void toggleVis() {
        setVisible(!isVisible());
    }

}
