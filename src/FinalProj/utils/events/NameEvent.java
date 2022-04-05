package FinalProj.utils.events;

public class NameEvent implements Event<String> {
    private final String state;
    public NameEvent(String name)
    {
        state = name;
    }
    @Override
    public String getState() {
        return state;
    }
}
