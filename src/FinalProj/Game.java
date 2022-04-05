package FinalProj;

import FinalProj.scene.NameSelect;
import FinalProj.scene.Title;
import FinalProj.scene.Tutorial;
import FinalProj.utils.ResourceLoader;
import basicgraphics.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    static ResourceLoader resourceLoader = new ResourceLoader();

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        final var basicFrame = new BasicFrame("Guilt");
        basicFrame.jf.setIconImage(resourceLoader.getPicture("favico").getImage());
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
    public static Task textTask(String message, JLabel jlabel)
    {
        AtomicInteger i = new AtomicInteger(0);
        return new Task(1) {
            @Override
            public void run() {
                jlabel.setText("");
                Timer timer = new Timer(100, e -> {
                    String labelText = jlabel.getText();
                    labelText += message.charAt(i.getAndIncrement());
                    jlabel.setText(labelText);
                    if (i.get() >= message.length()) {
                        setFinished();
                        ((Timer)e.getSource()).stop();
                    }
                });
                timer.start();
            }
        };
    }
    public static Task textTask(String message, JTextArea text)
    {
        AtomicInteger i = new AtomicInteger(0);
        text.setText("");
        return new Task(1) {
            @Override
            public void run() {
                Timer timer = new Timer(100, e -> {
                    String labelText = text.getText();
                    labelText += message.charAt(i.getAndIncrement());
                    text.setText(labelText);
                    if (i.get() >= message.length() - 1) {
                        setFinished();
                        ((Timer)e.getSource()).stop();
                    }
                });
                timer.start();
            }
        };
    }

}
