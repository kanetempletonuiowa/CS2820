// Kane Templeton
// Shelf.java

package production;

import java.util.ArrayList;

public class Shelf implements Tickable {
    
    private int homeX,homeY;
    private int curX,curY;
    private boolean onFloor;
    ArrayList<Item> itemsOnShelf;
    
    public Shelf(int x, int y) {
        homeX=x;
        homeY=y;
        curX=x;
        curY=y;
        onFloor=true;
        itemsOnShelf = new ArrayList<Item>();
        Production.controls().addEntity(this);
    }
    
    public boolean onFloor(){return onFloor;}
    public void pickup(){onFloor=false;}
    public void putdown(){onFloor=true;}
    public Cell getHome() {
        return Production.controls().cell(homeX, homeY);
    }
    public Cell pickupSpace() {
        return getHome().above();
    }
    
    public int getX(){return curX;}
    public int getY(){return curY;}
    public void setCoordinates(int x, int y) {
        curX=x;
        curY=y;
    }
    public int getID() {
        return Constants.SHELF_ID;
    }
    public void tick(){}

}
