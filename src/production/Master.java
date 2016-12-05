package production;

import java.util.ArrayList;
import java.util.PriorityQueue;
/*
    Master
    @author: Kane Templeton
    main class, runs the simulation
*/

public class Master {
	
	private boolean running;
        private int clockTime;
        
	
        private ArrayList<CustomerOrder> activeOrders;
        
        public Orders masterOrders;
        public Floor masterFloor;
        
        public Belt masterBelt;
        
        private RobotScheduler scheduler;
        private Inventory inventory;
        
        
        public Master() {
            Production.controls().setMaster(this);
            masterOrders = new Orders();
            //initialize orders here
            
            //
            
            
            addInitialEntities();
            
            scheduler = new RobotScheduler();
            inventory = new Inventory();
            
            
            clockTime=0;
        }
        
        /*  addInitialEntities()
            @author: Kane Templeton
            adds initial floor entities to the floor
        */
        private void addInitialEntities() {
            masterFloor = new Floor(20,20);
            masterFloor.initFloor();
            
            
            masterBelt = new Belt();
            masterFloor.addNewEntity(masterBelt);
            
            
        }
        
        
	
        /*  start()
            @author: Kane Templeton
            start the simulation
        */
	public void start() {
            output("Starting simulation...");
            running=true;
            run();
	}
	
        
        /*  stop()
            @author: Kane Templeton
            end the simulation
        */
	public void stop() {
            output("END SIMULATION.");
            running=false;
	}
	
        
        /*  run()
            @author: Kane Templeton
            contains the running loop for the simulation
        */
	private void run() {
            double clock=System.currentTimeMillis();
            while (running) {
                //create a reasonable time structure, time incrememts approx. every second
                if (System.currentTimeMillis()-clock >= 1000) {
                    clockTime++;
                    clock=System.currentTimeMillis();
                    for (FloorEntity e:masterFloor.getEntities()) //tick active tickable entities
                        e.getTickable().tick();
                                        
                }
                
                //render everything
                Production.getVisualizer().render();
                
                
            }
	}
        
	
        
        
        /*  initializeEvents()
            @author: Kane Templeton
            add events to the event queue before the simulation begins
        */
        public void initializeEvents() {
            // initialize events here

        }

        
        

        
        /*  getMasterClockTime()
            @author: Kane Templeton
            return the current Master clock time
        */
        public int getMasterClockTime() {return clockTime;}
        
        
        /*  output(String msg)
            @author: Kane Templeton
            output a message to the console 
            and include the current clock time
        */
        public void output(String msg) {
            System.out.println("[time="+getMasterClockTime()+"]"+msg);
        }
        
        public Floor getMasterFloor() {return masterFloor;}
        public Orders getMasterOrders(){return masterOrders;}
        public RobotScheduler getRobotScheduler() {return scheduler;}
        public Inventory getInventory() {return inventory;}
        
	
}