package FinalProj.utils;

import FinalProj.utils.events.Event;

import java.util.ArrayList;

/**
 * Publishers push event to subscribers when the notifySubs method is called.
 * @param <T>
 */
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
