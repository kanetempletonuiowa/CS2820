package production;


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
    
    public int getX();
    public int getY();
    public int getID();
    public void setCoordinates(int x, int y);
    public void setVisible(boolean vis);
    public boolean isVisible();

}
