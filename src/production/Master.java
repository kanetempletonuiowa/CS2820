package production;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
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
        private RobotScheduler scheduler;
        private Inventory inventory;
        
        public Master() throws FileNotFoundException {
            
            Production.controls().setMaster(this);
            
            masterOrders = new Orders();
            scheduler = new RobotScheduler();
            inventory = new Inventory();
            
            addInitialEntities();
                        
            masterOrders.initialOrders(100000);
            firstOrder();
            clockTime=0;
            Production.fileManager().appendLine("Simulation "+getSimulationNumber()+": "+(masterOrders.queuedOrders.size()+1)+" orders.");
            for (int k=0; k<40; k++)
                Production.fileManager().append("~");
            Production.fileManager().appendLine();
            
        }
        
        private void firstOrder() {
            currentOrder=masterOrders.nextOrder();
            for (Item I:currentOrder.getOrderItems())
                inventory.addItem(I, 1);
          /*  Shelf S = inventory.getShelf(currentOrder.nextItem());
            Path P = new Path(Production.controls().getRobot(),S.pickupSpace(),Constants.GRAB_SHELF);
            scheduler.setRobotPath(P);*/
        }
        
        /*  addInitialEntities()
            @author: Kane Templeton
            adds initial floor entities to the floor
        */
        private void addInitialEntities() {
            masterFloor = new Floor(20,20);
            masterFloor.initFloor();
            
            //masterBelt = new Belt();
            //masterFloor.addNewEntity(masterBelt);
            
            
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
            Production.fileManager().createBuild("output/simulations/"+getSimulationNumber()+".txt");
            output("END SIMULATION.");
            running=false;
	}
        
        private int getSimulationNumber() {
            File f = new File("output/simulations/");
            int i=0;
            while ((f = new File("output/simulations/"+i+".txt")).isFile()) i++;
            return i;
        }
	
        
        public void completeOrder() {
            currentOrder.status=Constants.COMPLETE;
            currentOrder = masterOrders.nextOrder();
        }
        
        /*  run()
            @author: Kane Templeton
            contains the running loop for the simulation
        */
	private void run() {
            double clock=System.currentTimeMillis();
            while (running) {
                if (System.currentTimeMillis()-clock >=0) {
                    clockTime++;
                    clock=System.currentTimeMillis();
                    for (FloorEntity e:masterFloor.getEntities())
                        e.getTickable().tick();
                    if (masterOrders.ordersToComplete()) {
                        if (currentOrder.status.equals(Constants.WAITING)) {
                            currentOrder.status = Constants.PENDING;
                            Shelf S = inventory.getShelf(currentOrder.nextItem());
                            Path P = new Path(Production.controls().getRobot(),S.pickupSpace(),Constants.GRAB_SHELF);
                            scheduler.setRobotPath(P);
                        }
                    }
                    else 
                        stop();
                }
                if (Production.visualizer()!=null)
                    Production.visualizer().render();
            }
	}
        
        
        public void ship(Parcel P) {
            output("SHIPPING ORDER #"+currentOrder.number+" TO "+P.getAddress());
            Production.fileManager().appendLine("[time="+clockTime+"] Order "+currentOrder.number+" shipped "+currentOrder.itemsInOrder.size()+" items to "+P.getAddress());
            output("\tItems:");
            Production.fileManager().append("\tItems Shipped: ");
            Iterator<Item> it = P.getItems().iterator();
            while (it.hasNext()) {
                Item I = it.next();
                it.remove();
                output("\t\t"+I.description);
                if (it.hasNext())
                    Production.fileManager().append(I.description+", ");
                else
                    Production.fileManager().append(I.description);
            }
            Production.fileManager().appendLine();
            completeOrder();
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
