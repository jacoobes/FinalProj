package FinalProj;

import FinalProj.scene.NameSelect;
import FinalProj.scene.Title;
import FinalProj.scene.Tutorial;
import FinalProj.utils.ResourceLoader;
import basicgraphics.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Game {
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
        content.add(bc1, "Title");

        final BasicContainer nameContainer = new NameSelect(resourceLoader);
        content.add(nameContainer, "Name Select");

        final BasicContainer bc3 = new Tutorial(resourceLoader);
        content.add(bc3, "Tutorial");

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
