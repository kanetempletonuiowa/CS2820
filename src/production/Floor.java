// Kane Templeton
// Floor.java

package production;

import java.awt.Point;
import java.util.List;


public interface Floor {
    
    int width();
    int depth();
    Point getPicker(); 
    Point getPacker(); 
    Point getShippingDock(); 
    Point getReceivingDock(); 
    Point getCharger();
    List<Point> getBeltArea();
    List<Point> getPath(Point start,Point end);
  /*  Cell getCell(int x, int y);
    Cell getCell(Point P);
    int getNumShelfAreas();
    ShelfArea getShelfArea(int which);*/
    // method used by Inventory to stock items on shelves
    Point randomInShelfArea(); 

}
