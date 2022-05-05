package FinalProj.components;

import javax.swing.*;
import java.awt.*;

public class SemiTransparentTextField extends TextBox {

    public SemiTransparentTextField(Font f) {
        super(f);
        init();
    }


    protected void init() {
        setForeground(Color.darkGray);
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.75f));
        super.paint(g2d);
        g2d.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g2d);
        g2d.dispose();
    }

}