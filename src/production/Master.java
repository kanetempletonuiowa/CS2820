package production;

import java.io.FileNotFoundException;
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
        
	
        public CustomerOrder currentOrder; //current order being processed
        
        public Orders masterOrders;
        public Floor masterFloor;
        
        public Belt masterBelt;
        
        private RobotScheduler scheduler;
        private Inventory inventory;
        private static final int TICK_SPEED_MS=100;
        
        public Master() throws FileNotFoundException {
            Production.controls().setMaster(this);
            
            masterOrders = new Orders();
            scheduler = new RobotScheduler();
            inventory = new Inventory();
            
            addInitialEntities();
                        
            masterOrders.initialOrders(1);
            firstOrder();
            clockTime=0;
        }
        
        private void firstOrder() {
            currentOrder=masterOrders.nextOrder();
            Shelf S = inventory.getShelf(currentOrder.nextItem());
            Path P = new Path(Production.controls().getRobot(),S.pickupSpace(),Constants.GRAB_SHELF);
            scheduler.setRobotPath(P);
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
	
        
        public void completeOrder() {
            currentOrder.status=Constants.COMPLETE;
        }
        
        /*  run()
            @author: Kane Templeton
            contains the running loop for the simulation
        */
	private void run() {
            double clock=System.currentTimeMillis();
            double stopDelay=5;
            while (running) {
                if (System.currentTimeMillis()-clock >= TICK_SPEED_MS) {
                    clockTime++;
                    clock=System.currentTimeMillis();
                    for (FloorEntity e:masterFloor.getEntities()) //tick active tickable entities
                        e.getTickable().tick();
                    
                    if (masterOrders.ordersToComplete()) {
                        if (currentOrder.status.equals(Constants.COMPLETE)) {
                            currentOrder = masterOrders.nextOrder();
                            Shelf S = inventory.getShelf(currentOrder.nextItem());
                            Path P = new Path(Production.controls().getRobot(),S.pickupSpace(),Constants.GRAB_SHELF);
                            scheduler.setRobotPath(P);
                        }
                    }
                    else {
                        stop();
                    }
                    
                    /*if (currentOrder.statusUpdate().equals(Constants.COMPLETE)) {
                        output("Order #"+currentOrder.number+" has been shipped to "+currentOrder.address);
                        if (!masterOrders.hasNext()) {
                            if (stopDelay==0)
                                stop();
                            else stopDelay--;
                        }
                        else 
                            currentOrder = masterOrders.nextOrder();
                        
                    } 
                    else {
                        //process pending orders
                        if (masterFloor.getRobot().walkPath==null) {
                            Shelf S = inventory.getShelf(currentOrder.nextItem());
                            scheduler.setRobotPath(S.pickupSpace());
                        }
        
                    }*/
                                        
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