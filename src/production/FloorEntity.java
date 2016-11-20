// Kane Templeton
// FloorEntity.java

package production;

import java.awt.Color;
import java.awt.Graphics;


public class FloorEntity {
    
    private Tickable entity;
    private int xCoordinate,yCoordinate;
    
    public FloorEntity(Tickable t, int x, int y) {
        entity=t;
        xCoordinate=x;
        yCoordinate=y;
    }
    
    public Tickable getTickable() {
        return entity;
    }
    public int getX() {
        return xCoordinate;
    }
    public int getY() {
        return yCoordinate;
    }
    public void setCoordinates(int x, int y) {
        xCoordinate=x;
        yCoordinate=y;
    }
    
    public void render(Graphics g) {
        g.setColor(Color.red);
            int size = Production.SQUARE_SIZE;
            g.drawRect(xCoordinate*size, yCoordinate*size, size, size);
            g.setColor(Color.black);
            g.fillRect(xCoordinate*size+1, yCoordinate*size+1, size-1, size-1);
    }
}