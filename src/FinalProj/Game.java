package FinalProj;

import FinalProj.scene.NameSelect;
import FinalProj.scene.ProfileSelection;
import FinalProj.scene.Title;
import FinalProj.scene.Tutorial;
import FinalProj.utils.ResourceLoader;
import FinalProj.utils.SceneTracker;
import basicgraphics.*;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Game {
    public static SceneTracker sceneTracker;
    static ResourceLoader resourceLoader = new ResourceLoader();
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        final var basicFrame = new BasicFrame("Guilt");
        basicFrame.jf.setIconImage(resourceLoader.getPicture("favico").getImage());
        basicFrame.jf.setPreferredSize(new Dimension(1200,799));
        final var content = basicFrame.getContentPane();

        var cards = new CardLayout();
        content.setLayout(cards);

        resourceLoader.setBf(basicFrame);
        final BasicContainer bc1 = new Title(resourceLoader);
        content.add(bc1, Title.class.getName());

        final BasicContainer profiles = new ProfileSelection(resourceLoader);
        content.add(profiles, ProfileSelection.class.getName());

        final BasicContainer nameContainer = new NameSelect(resourceLoader);
        content.add(nameContainer, NameSelect.class.getName());

        final BasicContainer bc3 = new Tutorial(resourceLoader);
        content.add(bc3, Tutorial.class.getName());

        basicFrame.show();


    }
    public static JLabel mainPanel(ImageIcon background)
    {
       return new JLabel(background) {
            @Override
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                Dimension lmPrefSize = getLayout().preferredLayoutSize(this);
                size.width = Math.max(size.width, lmPrefSize.width);
                size.height = Math.max(size.height, lmPrefSize.height);
                return size;
            }
        };
    }

    public static void transitionScene(BasicContainer bc, String name)
    {
        //parentContainer is basic frame
        var parentContainer = bc.getParent();
        var cardLayout = (CardLayout) parentContainer.getLayout();
        cardLayout.show(parentContainer, name);
    }


}
