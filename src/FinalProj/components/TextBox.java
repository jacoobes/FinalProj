package FinalProj.components;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class TextBox extends JTextArea {
    public TextBox(Font f)
    {
        super(7,50);
        setText("");
        setFont(f);
        requestFocus();
        setEditable(false);
        setVisible(false);
        setForeground(Color.LIGHT_GRAY);
        setBorder(new BevelBorder(5, Color.DARK_GRAY, Color.black));
        setOpaque(false);
    }
}

