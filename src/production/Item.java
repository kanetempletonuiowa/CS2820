package production;

import production.Master;

public class Item  {
	protected int itemNum;
	protected int quantity;
	protected String description;
	
	
	public Item (int n, int q) {
		this.itemNum = n;
		this.quantity = q;
		this.description = (String) MockInventory.getInventory().get(n);
	}
        
        public String getDescription(){return description;}
}
