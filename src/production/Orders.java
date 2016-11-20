package production;

import java.util.ArrayList;
import java.util.Random;


public class Orders {
	ArrayList<CustomerOrder> currentOrders;
	Random rand;
	
	// constructor just initiates the currentOrders ArrayList
	public Orders() {
		this.currentOrders = new ArrayList<>();
                rand = new Random();
	}
	
	public void generateOrder(int num, int q, String addy) {
		enqueueOrder(new CustomerOrder(new Item(num, q), addy));
	}
	
	public void enqueueOrder(CustomerOrder order) {
		currentOrders.add(order);
		//Master.addEvent(order); <- TODO: add event to queue
	}
	
	public void initialOrders(int x) {
		for (int i=0; i<x; i++) {
                    generateOrder(rand.nextInt(6), rand.nextInt(4), "address");
		}
	}
}
