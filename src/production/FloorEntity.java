package production;

import java.awt.Graphics;


public class FloorEntity {
    
    private Tickable entity;
    
    public FloorEntity(Tickable t) {
        entity=t;
    }
    
    public void setVisible(boolean v){entity.setVisible(v);}
    public boolean isVisible(){return entity.isVisible();}
    
    public Tickable getTickable() {
        return entity;
    }
    public int getX() {
        return entity.getX();
    }
    public int getY() {
        return entity.getY();
    }
    public void setCoordinates(int x, int y) {
        entity.setCoordinates(x, y);
    }
    
    public void render(Graphics g) {
        int size = Production.SQUARE_SIZE;
        if (isVisible())
            g.drawImage(Constants.getImage(getEntityType()), getX()*size, getY()*size, null);
    }
    
    public int getEntityType() {
        return entity.getID();
    }
}