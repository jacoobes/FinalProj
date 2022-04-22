package FinalProj.scene;

import FinalProj.Game;
import FinalProj.utils.ResourceLoader;
import basicgraphics.BasicContainer;

import javax.swing.*;

public class Scene5 extends BasicContainer {

    private final ResourceLoader resources;

    Scene5(ResourceLoader rl) {
        super();
        this.resources = rl;

        final ImageIcon backgroundImage = new ImageIcon(rl.getPicture("title").resize(1.5f).getImage());
        var mainPanel = Game.mainPanel(backgroundImage);
        mainPanel.setLayout(getLayout());

    }
}
