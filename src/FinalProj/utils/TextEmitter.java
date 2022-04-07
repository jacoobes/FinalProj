package FinalProj.utils;

import FinalProj.utils.events.TaskFinishedEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

public class TextEmitter implements ActionListener {
    private final Publisher<Boolean> publ = new Publisher<>();
    private JTextArea text;
    private final String message;
    private final AtomicInteger i = new AtomicInteger(0);

    public TextEmitter(String m)
    {
        message = m;
    }
    public TextEmitter addSub(Subs<Boolean> sub)
    {
        publ.addSubscriber(sub);
        return this;
    }
    public TextEmitter setJText(JTextArea j)
    {
        this.text = j;
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String innerText = text.getText();
        innerText += message.charAt(i.getAndIncrement());
        text.setText(innerText);
        if (i.get() > message.length() - 1) {
            ((Timer)e.getSource()).stop();
           publ.notifySubs(new TaskFinishedEvent());
        }
    }
}
