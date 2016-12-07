package production;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import production.Master;
import production.Item;

/**
 * 
 * @author scott hoefer
 */

public class Orders {
	ArrayList<String> addresses;
	LinkedList<CustomerOrder> currentOrders;
	Random rand = new Random();
	int orderNum;
	
	/**
	 * 
	 * @author scott hoefer
	 * @throws FileNotFoundException 
	 *
	 */
	// constructor 
	public Orders() throws FileNotFoundException {
		this.currentOrders = new LinkedList<CustomerOrder>();
		this.orderNum = 0;
		this.addresses = new ArrayList<String>();
		initAddresses();
	}
	
	public void initAddresses() throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedReader(new FileReader("addresses.docx")));
		System.out.println(sc.hasNext());
		while (sc.hasNext()) {
			System.out.println("here orders 1");
			String s = sc.nextLine();
			this.addresses.add(s);
		}
		System.out.println(this.addresses.size());
		sc.close();
	}
	
	/**
	 * 
	 * @author scott hoefer
	 * @param int num which is the item number and String addy which is the customers address
	 * @throws FileNotFoundException 
	 * 
	 */
	public void generateOrder(Item i, String addy) throws FileNotFoundException {
		enqueueOrder(new CustomerOrder(i, addy, orderNum));
		orderNum++;
	}
	
	/**
	 * 
	 * @author scott hoefer
	 * @param CustomerOrder object
	 * 
	 * This will randomly add 0-5 items to the order, then add the order to the list of current orders
	 * @throws FileNotFoundException 
	 */
	public void enqueueOrder(CustomerOrder order) throws FileNotFoundException {
		int x = rand.nextInt(6);  // this will add 0-5 items to the order
		for (int i=0; i<x; i++) {
			Item item;
			item = Production.getMaster().getInventory().getRandomItem();
			order.addItemsToOrder(item);
		}
		currentOrders.addLast(order);
	}
	
	/**
	 * 
	 * @author scott hoefer
	 * @param int x, which is the number of initial orders
	 * @throws FileNotFoundException 
	 *
	 */
	public void initialOrders(int x) throws FileNotFoundException {
		for (int i=0; i<x; i++) {
			int y = rand.nextInt(this.addresses.size());
			generateOrder(Production.getMaster().getInventory().getRandomItem(), this.addresses.get(y));
		}
	}
	
	/**
	 * @author scott hoefer
	 * 
	 * NOT USED IN FINAL IMPLEMENTATION
	 **/
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
	

