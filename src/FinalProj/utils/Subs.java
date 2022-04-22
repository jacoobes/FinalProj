package FinalProj.utils;


import FinalProj.utils.events.Event;

public interface Subs<T> {
    void update(Event<T> event);
}
