package production;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import production.Item;
import production.Master;

/**
 * 
 * @author scott hoefer
 *
 */
public class CustomerOrder  {
	LinkedList<Item> itemsInOrder = new LinkedList<Item>();
	String address;
	String status;
	int number;
	// fireTime is the tick at which this order will be "placed"
	int fireTime;
	Random rand = new Random();
	
	// constructor
	CustomerOrder(Item i, String address, int orderNumber) {
		this.address = address;
		this.status = "pending";
		this.number = orderNumber;
		itemsInOrder.add(i);
		this.fireTime = rand.nextInt(60);
	}
	
	/**
	 * 
	 * @author scott hoefer
	 * @param An instance of the Item class
	 */
	public void addItemsToOrder(Item i) {
		itemsInOrder.add(i);
	}
	
	/**
	 * 
	 * @author scott hoefer
	 * Returns the current status of this order
	 */
	public String statusUpdate() {
		return this.status;
	}
	
	/**
	 * 
	 * @author scott hoefer
	 * 
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * @author scott hoefer 
	 */
	public LinkedList<Item> getOrderItems() {
		return this.itemsInOrder;
	}
        
        public Item removeNextItem() {
            return itemsInOrder.removeFirst();
        }
        public Item nextItem() {
            return itemsInOrder.getFirst();
        }
	
	/**
	 * @author scott hoefer
	 */
	public int compareTo(CustomerOrder other) {
		if (this.equals(other)) return 0;
		else return 1;
	}

	/**
	 * @author scott hoefer 
	 */
	public void fire() {
		Production.controls().output("Order Received...");
		Shelf s = itemsInOrder.get(0).getPlace();
		Production.getMaster().getRobotScheduler().requestShelf(s);
		Production.controls().output("Sending robot to retrieve shelf...");
		
	}

	public int getFireTime() {
		return this.fireTime;
	}

	public void setFireTime(int t) {
		this.fireTime = t;
	}
	
	/**
	 * @author scott hoefer 
	 */
	public String toString() {
		return "Customer Order # " + this.number;
	}
}
