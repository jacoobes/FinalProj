package FinalProj.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuadChoicePanel extends SemiTransparentPanel {
    private ChoiceButton[] cbs;
    public QuadChoicePanel(ChoiceButton[] cb)
    {
        super();
        cbs = cb;
        setLayout(new GridLayout(1,2));
        Arrays.stream(cb).forEach(b -> {
            b.toggleVis();
            b.setOpaque(false);
            b.setContentAreaFilled(false);
        });
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1,true));
        var bPanelLeft = new JPanel(new GridLayout(2,1));
        var bPanelRight = new JPanel(new GridLayout(2,1));
        bPanelLeft.setOpaque(false);
        bPanelRight.setOpaque(false);

        bPanelLeft.add(cb[0]);
        bPanelLeft.add(cb[1]);
        bPanelLeft.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        bPanelRight.add(cb[2]);
        bPanelRight.add(cb[3]);
        bPanelRight.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        add(bPanelLeft);
        add(bPanelRight);
    }

    public ArrayList<ChoiceButton> getButtons() {
        ArrayList<ChoiceButton> choices = new ArrayList<>();
        Collections.addAll(choices, cbs);
        return choices;
    }

}
