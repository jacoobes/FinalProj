package FinalProj.utils;

import FinalProj.utils.events.Event;

import java.util.ArrayList;

public class Publisher<T> {
    private final ArrayList<Subs<T>> subscribers = new ArrayList<>();

    public void notifySubs(Event<T> e)
    {
        for ( Subs<T> s : subscribers)
        {
            s.update(e);
        }
    }
    public void addSubscriber(Subs<T> s)
    {
        subscribers.add(s);
    }
}
