package production;

import java.util.ArrayList;
import java.util.PriorityQueue;
/*
    Master
    @author: Kane Templeton
    @author Scott Hoefer (added some instance variables and changed the constructor)
    main class, runs the simulation
*/

//TESTTESTTESTTEST

public class Master {
	
	private boolean running;
    private int tick;
    protected PriorityQueue<Event> eventQueue;
    public Orders orders;
    public MockFloor floor;
    public RobotScheduler robotSched;
    public Inventory inventory;
    public Belt belt;
    public SimRandom R = new SimRandom();
        
    public Master() {
        this.eventQueue = new PriorityQueue<>();
        this.tick = 0;
        this.orders = new Orders();
        this.floor = new MockFloor(R);
        this.robotSched = new RobotScheduler(this.floor);
        this.inventory = new Inventory(this.floor, R);
        this.belt = new Belt(this.floor);
        
    }
        
        
	/*
        start()
        @author: Kane Templeton
        start the simulation
    */
	public void start() {
            output("Starting simulation...");
            running=true;
            run();
	}
	
    /*
        stop()
        @author: Kane Templeton
        end the simulation
    */
	public void stop() {
            output("END SIMULATION.");
            running=false;
	}
	
	/**
	 * @author Kane Templeton
	 * @author scott hoefer
	 * runs the sim 
	 */
	private void run() {
        double clock=System.currentTimeMillis();
        while (!eventQueue.isEmpty() && !this.orders.currentOrders.isEmpty()) {
            //create a reasonable time structure, time increments approx. every second
            if (System.currentTimeMillis()-clock >= 1000) {
                tick++;
                this.robotSched.moveRobots();
                this.belt.tick();
                clock=System.currentTimeMillis();
            }
            //check if it is time to fire the next event
            if (!eventQueue.isEmpty()) {
            	if (eventQueue.peek().getFireTime() == tick) {
            		this.output("EVENT: " + eventQueue.peek().toString());
            		Event e = this.eventQueue.poll();
            		this.orders.currentOrders.add((CustomerOrder) e);
            		e.fire();
            	}
            //end sim when out of events
            } else { this.stop(); }
        }
	}
        
        /*
            addEvent(Event e)
            @author: Kane Templeton
            add an event to the event queue
        */
        public void addEvent(Event e) {
            eventQueue.add(e);
        }
        
        /*
            getEventQueue()
            @author: Kane Templeton
            return the Master event queue
        */
        public PriorityQueue getEventQueue() {
            return eventQueue;
        }
        
        /*
            getMasterClockTime()
            @author: Kane Templeton
            return the current Master clock time
        */
        public int getMasterTick() {return this.tick;}
        
        /*
            output(String msg)
            @author: Kane Templeton
            output a message to the console 
            and include the current clock time
        */
        public void output(String msg) {
            System.out.println("[time = "+getMasterTick()+"] "+msg);
        }
        
	
}