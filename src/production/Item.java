package production;

import production.Master;
/**
 * 
 * @author Scott Hoefer
 *
 */
public class Item extends Master {
	protected int itemNum;
	protected String description;
	public Shelf location;
	
	/**
	 * 
	 * @author Scott Hoefer
	 *
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
