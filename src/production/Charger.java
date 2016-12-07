// Kane Templeton
// Charger.java

package production;


public class Charger implements Tickable{
    
    private Robot charging;
    private int posX,posY;
    private boolean visible;
    
    public Charger(int x, int y) {
        posX=x;
        posY=y;
        charging=null;
        visible=true;
        Production.controls().addEntity(this);
    }
    
    public Cell chargeCell() {
        return Production.controls().cell(this);
    }

    public void tick() {
        if (charging!=null)
            charging.charge++;
    }

    @Override
    public int getX() {
        return posX;
    }

    @Override
    public int getY() {
        return posY;
    }

    @Override
    public int getID() {
        return Constants.CHARGER_ID;
    }

    @Override
    public void setCoordinates(int x, int y) {
        posX=x;
        posY=y;
    }

    @Override
    public void setVisible(boolean vis) {
        visible=vis;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

}
