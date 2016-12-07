package production;

import java.util.ArrayList;
import java.util.LinkedList;

public class Parcel {
    
    private ArrayList<Item> items;
    private String address;
    
    public Parcel(Bin B) {
        items = new ArrayList();
        for (Item I:B.containedItems())
            items.add(I);
        B.empty();
        address=Production.getMaster().currentOrder.address;
    }
    
    public ArrayList<Item> getItems() {return items;}
    public String getAddress() {return address;}
    
}
