package production;

import java.util.ArrayList;
import production.Item;
import production.Master;

public class CustomerOrder implements Comparable<CustomerOrder> {
	ArrayList<Item> itemsInOrder;
	String address;
	String status;
	
	public CustomerOrder(Item i, String address) {
            this.itemsInOrder = new ArrayList<>();
            this.address = address;
            this.status = "pending";
            this.itemsInOrder.add(i);
	}
	
	public void addItemsToOrder(Item i) {
		itemsInOrder.add(i);
	}
	
	public String statusUpdate() {
		return this.status;
	}
	
	public int compareTo(CustomerOrder other) {
		if (this.equals(other)) return 0;
		else return 1;
	}
}
