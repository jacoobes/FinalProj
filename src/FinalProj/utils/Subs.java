package FinalProj.utils;


import FinalProj.utils.events.Event;

/**
 * an interface to create subscribers.
 * subscribers receive events from publishers.
 * @param <T>
 */
public interface Subs<T> {
    void update(Event<T> event);
}
