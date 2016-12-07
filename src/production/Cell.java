package production;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    
    private FloorEntity entity;
    private boolean walkable;
    private int x,y;
    
    public Cell(int x, int y) {
        entity=null;
        this.x=x;
        this.y=y;
        walkable=true;
    }
    
    public void setEntity(Tickable t) {
        entity=new FloorEntity(t);
    }
    
    public void setEntity(FloorEntity t){
        entity=t;
    }
    
    public boolean walkable() {
        if (entity==null)
            return true;
        if (entity.getEntityType()==Constants.CHARGER_ID)
            return true;
        return !Production.getMaster().getMasterFloor().entityAt(x, y).isVisible();
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
    
    public Cell under() {
        return Production.controls().cell(x, y+1);
    }
    public Cell above() {
        return Production.controls().cell(x, y-1);
    }
    public Cell left() {
        return Production.controls().cell(x-1, y);
    }
    public Cell right() {
        return Production.controls().cell(x+1, y);
    }
    
    public int getX() {return x;}
    public int getY() {return y;}
    
    
}