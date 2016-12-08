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
        if (charging!=null) {
            charging.charge+=2.5;
            if (charging.charge>=100) {
                charging.charge=100;
                disconnect(charging);
            }
        }
    }

    
    public void connect(Robot R) {
        if (charging==null) 
            charging=R;
        
    }
    
    public void disconnect(Robot R) {
        charging=null;
        Shelf S = Production.getMaster().getInventory().getShelf(Production.getMaster().currentOrder.nextItem());                    
        Path P = new Path(R,S.pickupSpace(),Constants.GRAB_SHELF);
        Production.getMaster().getRobotScheduler().setRobotPath(P);
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
