package FinalProj.components;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class TutorialParagraph extends JTextArea {
    public TutorialParagraph(Font f)
    {
        super(7,50);
        setFont(f);
        setEditable(false);
        setVisible(false);
        setForeground(Color.LIGHT_GRAY);
        setBorder(new BevelBorder(5, Color.DARK_GRAY, Color.black));
        setOpaque(false);
    }
}

