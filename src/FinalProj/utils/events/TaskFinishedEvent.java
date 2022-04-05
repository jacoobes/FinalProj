package FinalProj.utils.events;

public class TaskFinishedEvent implements Event<Boolean> {
    @Override
    public Boolean getState() {
        return true;
    }
}
