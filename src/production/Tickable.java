package production;

import production.Event;

/*
    Tickable
    @author: Kane Templeton
    Represents anything that can add events to the queue
    and have continuous "tick" logic
*/

public interface Tickable {
    /*
        tick()
        @author: Kane Templeton
        logic for every "tick" of the simulation's master clock
    */
    public void tick();

}
