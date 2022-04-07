package FinalProj.components;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnSpace extends KeyAdapter  {
    private final Component comp;
    private final Runnable doable;
    public OnSpace(Component c, Runnable execute)
    {
        comp = c;
        doable = execute;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            comp.requestFocus();
            SwingUtilities.invokeLater(doable);
        }
    }

}
