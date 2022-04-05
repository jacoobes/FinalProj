package FinalProj.scene;

import FinalProj.Game;
import FinalProj.components.InputName;
import FinalProj.utils.ResourceLoader;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;

public class NameSelect extends BasicContainer {
    public NameSelect(ResourceLoader rl) {
        super();
        final ImageIcon backgroundImage = new ImageIcon(rl.getPicture("title").resize(1.5f).getImage());
        var mainPanel = Game.mainPanel(backgroundImage);
        mainPanel.setLayout(getLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets.top = 0;

        var title = new JLabel("Select Name");
        title.setFont(rl.getBitStrFont().deriveFont(100f));
        title.setForeground(Color.LIGHT_GRAY);
        mainPanel.add(title, gbc);

        var field = new InputName(10, rl);

        gbc.gridx = -1;
        gbc.gridy = 1;

        mainPanel.add(field, gbc);
        String[][] splashLayout = {
                {"Select Name"},
        };

        setStringLayout(splashLayout);
        add("Select Name", mainPanel);
    }
}
