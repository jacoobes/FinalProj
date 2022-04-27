package FinalProj.components;

import javax.swing.*;
import java.awt.*;

public class ChoiceButton extends JButton  {
    public ChoiceButton(String text)
    {
        super(text);

        setVisible(false);
    }
    public void toggleVis() {
        setVisible(!isVisible());
    }
    public ChoiceButton addFont(Font f)
    {
        setFont(f);
        return this;
    }

}
