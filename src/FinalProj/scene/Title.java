package FinalProj.scene;

import FinalProj.Game;
import FinalProj.utils.ResourceLoader;
import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;


public class Title extends BasicContainer {
    public Title(
            ResourceLoader r
    ) {
        super();
        final ImageIcon backgroundImage = new ImageIcon(r.getPicture("title").resize(1.5f).getImage());
        JLabel mainPanel = Game.mainPanel(backgroundImage);
        mainPanel.setLayout(getLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets.top = 400;

        var title = new JLabel("Guilt");
        title.setFont(r.getBitStrFont().deriveFont(100f));
        title.setForeground(Color.LIGHT_GRAY);
        mainPanel.add(title);

        var jButton = new JButton("Start");
        jButton.setFont(r.getBitStrFont().deriveFont(50f));

        jButton.addActionListener(e -> Game.transitionScene(this, "Tutorial"));

        mainPanel.add(jButton, gbc);

        String[][] splashLayout = {
                {"Start"},
        };

        setStringLayout(splashLayout);
        add("Start", mainPanel);
    }


}
