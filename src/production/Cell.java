package production;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    
    private FloorEntity entity;
    private boolean gfxUpdateRequired;
    private int x,y;
    
    public Cell(int x, int y) {
        entity=null;
        this.x=x;
        this.y=y;
        gfxUpdateRequired=true;
    }
    
    public void setEntity(Tickable t) {
        entity=new FloorEntity(t);
    }
    
    public void setEntity(FloorEntity t){
        entity=t;
    }
    public FloorEntity getEntity(){return entity;}
    public void setUpdateRequired(boolean b){gfxUpdateRequired=b;}
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
    
    public int getX() {return x;}
    public int getY() {return y;}
    
    
}