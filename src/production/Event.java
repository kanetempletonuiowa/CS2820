package production;


/*
    Event
    @author: Kane Templeton
    @author Scott Hoefer (changed it from a class to interface)
    events for Master event queue
*/

public interface Event {
	
	void fire();
	int getFireTime();
	void setFireTime(int t);
    public void addEvent(Event e);

}