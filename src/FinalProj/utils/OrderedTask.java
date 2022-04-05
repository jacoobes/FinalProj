package FinalProj.utils;

import basicgraphics.Clock;
import basicgraphics.Task;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderedTask extends Task {
    private final Queue<Task> tasks;
    private Task curTask;
    public OrderedTask(LinkedList<Task> tasks)
    {
        this.tasks=tasks;
        curTask = tasks.get(0);
    }
    @Override
    public void run() {

        if (tasks.isEmpty())
        {
            setFinished();
            Clock.stop();
            return;
        }
        if (curTask.isFinished())
        {
            curTask = tasks.remove();
        }
        SwingUtilities.invokeLater( curTask );
        curTask.setFinished();
    }
}

