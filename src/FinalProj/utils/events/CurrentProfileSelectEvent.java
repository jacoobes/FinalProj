package FinalProj.utils.events;

import FinalProj.utils.Subs;
import FinalProj.utils.YamlParser;

public class CurrentProfileSelectEvent implements Event<YamlParser.Data> {

    private final YamlParser.Data curProf;

    public CurrentProfileSelectEvent(YamlParser.Data d)
    {
        this.curProf = d;
    }


    @Override
    public YamlParser.Data getState() {
        return curProf;
    }
}
