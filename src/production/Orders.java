package production;

import java.util.ArrayList;
import java.util.Random;
import production.Master;
import production.Item;

/**
 * 
 * @author scott hoefer
 */
public class Orders extends Master implements Picker {
	ArrayList<CustomerOrder> currentOrders;
	Random rand = new Random();
	int orderNum;
	
	/**
	 * 
	 * @author scott hoefer
	 *
	 */
	// constructor 
	public Orders() {
		this.currentOrders = new ArrayList<CustomerOrder>();
		this.orderNum = 0;
	}
	
	/**
	 * 
	 * @author scott hoefer
	 * @param int num which is the item number and String addy which is the customers address
	 * 
	 */
	public void generateOrder(Item i, String addy) {
		enqueueOrder(new CustomerOrder(i, addy, orderNum));
		orderNum++;
	}
	
	/**
	 * 
	 * @author scott hoefer
	 * @param CustomerOrder object
	 * 
	 */
	public void enqueueOrder(CustomerOrder order) {
		currentOrders.add(order);
		//Master.addEvent(order); <- TODO: add event to queue
	}
	
	/**
	 * 
	 * @author scott hoefer
	 * @param int x, which is the number of initial orders
	 *
	 */
	public void initialOrders(int x) {
		for (int i=0; i<x; i++) {
			generateOrder(super.inventory.stock.get(rand.nextInt(super.inventory.stock.size()-1)), "Address");
			
		}
	}

	@Override
	public void notify(Robot r, Shelf s) {
		// TODO Auto-generated method stub
		
	}

	public void pickItems(CustomerOrder order, Shelf s) {
		Bin b = super.belt.getBin();
		b.order = this.currentOrders.get(0);
		b.setFinished();
		super.output("The picker has placed the items in the bin and moved it onto the best");
		this.belt.tick();
	}
}
