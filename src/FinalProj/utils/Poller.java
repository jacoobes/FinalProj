package FinalProj.utils;

import FinalProj.utils.events.TaskFinishedEvent;
import basicgraphics.Clock;

import javax.swing.*;

public class Poller {
    private final Publisher<Boolean> p = new Publisher<>();
    private final Timer poller;
    private OrderedTask tasks;
    public Poller(int interval) {
        poller = new Timer(interval, e -> {
                if (tasks.isFinished())
                {
                    p.notifySubs(new TaskFinishedEvent());
                    Clock.stop();
                }
        });
    }
    public void start () { poller.start(); }
    public void stop () { poller.stop(); }
    public void setTasks(OrderedTask t) { tasks = t;}
    public void addSub(Subs<Boolean> s)
    {
        p.addSubscriber(s);
    }

}
