package production;

import java.util.ArrayList;

public class Parcel {
  /**
   * 
   * @author Ted Herman
   * 
   * A Parcel has an address and a list of Items
   *
   */
  String address;
  ArrayList<Item> items;
  boolean finished;
  public Parcel(CustomerOrder o) { 
	this.address = o.address;
	this.items = o.itemsInOrder;
	finished = false;
    }
  public boolean isFinished() { return finished; }
  public void setFinished() { finished = true; }
  public String toString() { return "Parcel"; }
  }




// addy, list of items 
