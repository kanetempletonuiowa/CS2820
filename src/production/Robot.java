package production;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class Robot implements Tickable  {
	//@author: Alex Wang
	/**
	 * author: Scott Hoefer (11/2316 )
	 */
	int number;
	Cell currentLocation;
	int size=2;
	double charge=100.0;
	String task= "idle";
	List<Cell> route = new LinkedList();
	Cell home;
	Cell robotsCurrentCell;
	Shelf carrying;
	Floor f;
        Path walkPath,nextPath;
        boolean visible;
        
        
        //@author: Alex Wang
	//Assigns the robot a unique reference number, as well as it's starting point
        public Robot(int n, int x, int y) {
            this.number=n;
            this.currentLocation=Production.controls().cell(x, y);
            this.home=Production.controls().cell(x, y);
            this.f = Production.getMaster().getMasterFloor();
            Production.controls().addEntity(this);
            walkPath=null;
            nextPath=null;
            carrying=null;
            visible=true;
        }
        public boolean isVisible() {return visible;}
        public void setVisible(boolean vis) {visible=vis;}
	
	
	//@author: Alex Wang
	//When sent a route from the floor, set this robot to "active".
	//toShelf - robot moves from robot station to shelves to get an item
	//toCharge - robot moves to charging station
	//toPicker - robot moves to picker, drops off shelf
	//returnShelf - robot returns shelf to location set by inventory 
	//charging - robot charging
	//
	//Once the robot is finished with its task, it will return "home" to the robot station and await another task
	
	/* 
	 * *******FLOOR DOES THIS NOW - Scott
	public void setRoute(ArrayList<int[]> newroute, String task){
		this.task=task;
		route = (ArrayList<int[]>)newroute.clone();
	}
	*/
	//@author: Alex Wang
	public void setTask(String s){
		task=s;
	}
	//@author: Alex Wang
	//Move robot towards destination. Change the route array list to reflect remaining route. Uses 0.5 charge per step.
	/**
	 * @author scott hoefer (11/23/16 changed it but same idea to move robot)
	 * 
	 * this will decide what the robots do on each tick e.g. Move closer to their destination
	 * 
	 */
	
        public void setPath(Path P) {
            walkPath=P;
        }
        public void setNextPath(Path P) {
            if (walkPath==null) {
                walkPath=P;
                nextPath=null;
            }
            else {
                nextPath=P;
            }
        }
        public void updatePath() {
            if (walkPath==null)
                return;
            if (nextPath==null)
                walkPath=null;
            else {
                walkPath=nextPath;
                nextPath=null;
            }
        }
        
	public void tick() {
            if (walkPath!=null) {
                Cell c = walkPath.next();
                Production.controls().moveEntity(this, c.getX(), c.getY());
                if (!walkPath.hasNext()) { //end of path
                    walkPath.complete();
                    updatePath();
                }
                
            }
		// re-charge if low after the current task
		if (this.charge <= 5.0 && this.task == "idle") this.task = "toCharge";
		if (task != "idle") {
			// a route size of one means we are now at the our destination
			if (route.size() == 1) {
				if (this.task == "toShelf") {
					this.route.remove(0);
					this.task = "toPicker";
					// this will handle picking up the shelf
					this.grabShelf();
				} else if ( this.task == "toPicker") {
					this.route.remove(0);
					// this will drop the shelf at the picker and wait to bring it back to the shelf area
					this.setShelf();
				} else if (this.task == "returnShelf") {
					this.route.remove(0);
					// will set the self down and return home or start another task
					this.setShelf();
				} else if (this.task == "toCharge") {
					this.route.remove(0);
					this.task = "charging";
					this.charge += 33;
				}
			} else if (this.route.size() > 1) {
				this.currentLocation = route.get(0);
				route.remove(0);
				charge-=0.5;
			} else if (this.task == "charging") { 
				this.charge += 33.0; 
				if (this.charge >= 100 ) {
					this.charge = 100;
					// this lil bugger doesn't have a task anymore, so set it idle and send it home
					this.task = "idle";
					this.route = f.getPath(this.currentLocation, this.home);
				}
				}
		} 
	}
	
	
	/**
	 * @author scott hoefer
	 */
	public void grabShelf(){
		this.carrying.pickup();
		this.route = f.getPath(this.currentLocation, f.getPickerLoc());
		System.out.println("ROBOT " + this.number + "has retrieved the shelf. Moving to picker.");
	}
	
	/**
	 * @author scott hoefer
	 */
	public void setShelf() {
		this.carrying.putdown();
		// send our lil robot friend home to rest, but he can be interrupted on the way with another task (hence the idle setting)
		this.route = f.getPath(this.currentLocation, this.home);
		this.task = "idle";
		Production.getMaster().getMasterOrders().pickItems(Production.getMaster().getMasterOrders().currentOrders.get(0), this.carrying);
		this.carrying = null;
	}
	
	//@author: Alex Wang
	public String getStatus(){
		return task;
	}
	
	//@author: Alex Wang
	public int getNumber(){
		return number;
	}
	
	//@author: Alex Wang
	public int getX(){
		return this.currentLocation.getX();
	}
	
	//@author: Alex Wang
	public int getY(){
		return this.currentLocation.getY();	
        }
        public void setCoordinates(int x, int y) {
            this.currentLocation=Production.controls().cell(x, y);
        }
        public int getID() {
            return Constants.ROBOT_ID;
        }
        public void setShelf(Shelf S) {
            
            carrying=S;
        }
        public Shelf getShelf(){return carrying;}
        
}

