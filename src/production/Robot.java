package production;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class Robot extends Master {
	//@author: Alex Wang
	/**
	 * author: Scott Hoefer (11/2316 )
	 */
	int number;
	Point currentLocation;
	int size=2;
	double charge=100.0;
	String task= "idle";
	List<Point> route = new LinkedList();
	Point home;
	Cell robotsCurrentCell;
	Shelf theGoods= null;
	Floor f;

	
	//@author: Alex Wang
	//Assigns the robot a unique reference number, as well as it's starting point
	public Robot(int number, Point p, Floor f){
		this.number=number;
		this.currentLocation = p;
		this.home = p;
		this.f = f;
	}
	
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
	
	public void tick() {
		// re-charge if low after the current task
		if (this.charge <= 5.0 && this.task == "idle") this.task = "toCharge";
		if (task != "idle") {
			// a route size of one means we are now at the our destination
			if (route.size() == 1) {
				if (this.task == "toShelf") {
					this.route.remove(0);
					this.task = "toPicker";
					// this will handle picking up the shelf
					this.getShelf();
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
	public void getShelf(){
		this.theGoods.pickup();
		this.route = f.getPath(this.currentLocation, f.getPicker());
		System.out.println("ROBOT " + this.number + "has retrieved the shelf. Moving to picker.");
	}
	
	/**
	 * @author scott hoefer
	 */
	public void setShelf() {
		this.theGoods.putdown();
		// send our lil robot friend home to rest, but he can be interrupted on the way with another task (hence the idle setting)
		this.route = f.getPath(this.currentLocation, this.home);
		this.task = "idle";
		super.orders.pickItems(super.orders.currentOrders.get(0), this.theGoods);
		this.theGoods = null;
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
		return this.currentLocation.x;
	}
	
	//@author: Alex Wang
	public int getY(){
		return this.currentLocation.y;	}

}

