package production;

import java.awt.Color;
import java.awt.Graphics;

public class FloorCoordinate {
    
    private FloorEntity entity;
    private int x,y;
    
    public FloorCoordinate(int x, int y) {
        entity=null;
        this.x=x;
        this.y=y;
    }
    
    public void setEntity(FloorEntity t){
        entity=t;
    }
    public FloorEntity getEntity(){return entity;}
    
    public void render(Graphics g) {
        if (entity==null) {
            g.setColor(Color.red);
            int size = Production.SQUARE_SIZE;
            g.drawRect(x*size, y*size, size, size);
            g.setColor(Color.blue);
            g.fillRect(x*size+1, y*size+1, size-1, size-1);
        }
        else {
            entity.render(g);
        }
    }
    
    
}