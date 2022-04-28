package FinalProj.scene;
import FinalProj.components.InputName;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.events.Event;
import javax.swing.*;
import java.awt.*;

public class NameSelect extends SceneAlpha {
    public NameSelect(ResourceLoader rl) {
        super(rl,  new ImageIcon(rl.getPicture("title").resize(1.5f).getImage()));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = GridBagConstraints.CENTER;
        gbc.gridy = GridBagConstraints.VERTICAL;
        gbc.insets.top = 0;

        var title = new JLabel("Select Name");
        title.setFont(getGameFont(60f));
        title.setForeground(Color.LIGHT_GRAY);
        getMainBGround().add(title, gbc);

        var field = new InputName(10, rl);

        gbc.gridx = GridBagConstraints.CENTER;
        gbc.gridy = GridBagConstraints.ABOVE_BASELINE;

        getMainBGround().add(field, gbc);

    }

    @Override
    public void update(Event<Boolean> event) {

    }
}
