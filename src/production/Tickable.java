package production;

import production.Event;

/*
    Tickable
    @author: Kane Templeton
    Represents anything that can add events to the queue
    and have continuous "tick" logic
*/

public interface Tickable {
    
    public void tick();
    public int getX();
    public int getY();
    public void setCoordinates(int x, int y);
    public int getID();

}
