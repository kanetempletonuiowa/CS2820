package production;

import java.util.ArrayList;
import java.util.LinkedList;

public class Parcel {
  /**
   * 
   * @author Ted Herman
   * 
   * A Parcel has an address and a list of Items
   *
   */
  String address;
LinkedList<Item> items;
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

