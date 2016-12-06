package production;

import production.Master;
/**
 * 
 * @author Scott Hoefer
 *
 */
public class Item  {
	protected int itemNum;
	protected String description;
	public Shelf location;
	
	/**
	 * 
	 * @author Scott Hoefer
	 * @param n - an integer representing the Item number or item id or serial number or whatevs 
	 * @param d - The string description for the item
	 */
	public Item (int n, String d) {
		this.itemNum = n;
		this.description = d;
		this.location = null;
	}

	/**
	 * 
	 * @author Scott Hoefer
	 *
	 */
	public void setPlace(Shelf s) {
		this.location = s;
		
	}

	/**
	 * 
	 * @author Scott Hoefer
	 *
	 */
	public Shelf getPlace() {
		return this.location;
	}
	
	/**
	 * 
	 * @author Scott Hoefer
	 *
	 */
	public boolean equals(Item other) {
		return (this.itemNum == other.itemNum);
	}
}
