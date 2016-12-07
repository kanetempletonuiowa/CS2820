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
	LinkedList<CustomerOrder> queuedOrders;
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
		this.queuedOrders = new LinkedList<CustomerOrder>();
		this.orderNum = 0;
		this.addresses = new ArrayList<String>();
		initAddresses();
	}
	
	/**
	 * @author scott hoefer
	 * @throws FileNotFoundException
	 * 
	 * Reads in 100 fake addresses from a file and adds them to an ArrayList
	 * 
	 * This method i tested using print statements
	 */
	public void initAddresses() throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedReader(new FileReader("addresses.txt")));
		//System.out.println(sc.hasNext());
		while (sc.hasNextLine()) {
			//System.out.println("Orders - sc.hasNextLine evaluated to true");
			String s = sc.nextLine();
			//System.out.println(s);
			this.addresses.add(s);
		}
		//System.out.println(this.addresses.size());
		//System.out.println(this.addresses.get(0));
		//System.out.println(this.addresses.get(99));
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
		int x = rand.nextInt(5);  // this will add 0-5 items to the order
		for (int i=0; i<x; i++) {
			Item item;
			item = Production.getMaster().getInventory().getRandomItem();
			order.addItemsToOrder(item);
		}
		//System.out.println("adding order to list");
		queuedOrders.addLast(order);
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
			// addresses.get(100) is an empty string so limit it to indexes 0-99
			int y = rand.nextInt(100);
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
            if (queuedOrders.size()<1)
                return null;
            CustomerOrder O = queuedOrders.removeFirst();
            Item I = O.nextItem();
            Production.getMaster().getInventory().addItem(I, 1);
            Production.controls().output("STARTING ORDER #"+O.number);
            return O;
        }
        public boolean hasNext() {
            return queuedOrders.size()>0;
        }
        public boolean ordersToComplete() {
            if (hasNext())
                return true;
            if (Production.getMaster().currentOrder==null)
                return false;
            return !Production.getMaster().currentOrder.status.equals(Constants.COMPLETE);
        }
        
        
}
	

