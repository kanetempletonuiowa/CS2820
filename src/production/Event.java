package production;


/*
    Event
    @author: Kane Templeton
    events for Master event queue
*/

//MIGHT DELETE THIS!

public class Event implements Comparable<Event> {
	
	private int fireTime; //events will fire when Master clock reaches this time
	private Task doTask; //task to perform when firing event
        private String taskName; //name of event
	
	public Event(String name, int t, Task task) {
            taskName=name;
            fireTime=t;
            doTask=task;
	}
        
	
        /*
            getFireTime()
            setFireTime(int t)
            @author: Kane Templeton
            getters and setters for fireTime
        */
	public int getFireTime() {return fireTime;}
	public void setFireTime(int t){fireTime=t;}
        
        /*
            setTask(Task task)
            getTask()
            @author: Kane Templeton
            getters and setters for doTask
        */
        public void setTask(Task task) {doTask=task;}
        public Task getTask(){return doTask;}
        
        /*
            setName(String n)
            getName()
            @author: Kane Templeton
            getters and setters for taskName
        */
        public void setName(String n){taskName=n;}
        public String getName(){return taskName;}

        /*
            compareTo(Event e)
            @author: Kane Templeton
            used by priority queue to compare events by start time
        */
        @Override
        public int compareTo(Event e) {
           if (e.getFireTime()==this.getFireTime())
             return 0;
           if (e.getFireTime()>this.getFireTime())
             return -1;
           return 1;
        }
        

}