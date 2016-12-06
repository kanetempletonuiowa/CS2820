package production;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import production.Master;
import production.Item;

/**
 * 
 * @author scott hoefer
 */
public class Orders implements Picker {
	LinkedList<CustomerOrder> currentOrders;
	Random rand = new Random();
	int orderNum;
	
	/**
	 * 
	 * @author scott hoefer
	 *
	 */
	// constructor 
	public Orders() {
		this.currentOrders = new LinkedList<CustomerOrder>();
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
		currentOrders.addLast(order);
	}
	
	/**
	 * 
	 * @author scott hoefer
	 * @param int x, which is the number of initial orders
	 *
	 */
	public void initialOrders(int x) {
		for (int i=0; i<x; i++) {
                    generateOrder(Production.getMaster().getInventory().getRandomItem(), "Address");
		}
	}

	@Override
	public void notify(Robot r, Shelf s) {
		// TODO Auto-generated method stub
		
	}

	public void pickItems(CustomerOrder order, Shelf s) {
		/*Bin b = super.belt.getBin();
		b.order = this.currentOrders.get(0);
		b.setFinished();
		Production.output("The picker has placed the items in the bin and moved it onto the best");
		this.belt.tick();*/
	}
        
        public CustomerOrder nextOrder() {
            CustomerOrder O = currentOrders.removeFirst();
            Item I = O.nextItem();
            Production.getMaster().getInventory().addItem(I, 1);
            return O;
        }
        public boolean hasNext() {
            return currentOrders.size()>0;
        }
        public boolean ordersToComplete() {
            if (hasNext())
                return true;
            return !Production.getMaster().currentOrder.status.equals(Constants.COMPLETE);
        }
        
        
}
