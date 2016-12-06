package production;

/*
    ControlPanel.java
    @author: Kane Templeton
    for important simulation functions

    call methods from this class to perform different actions
    use Production.controls().method(args) from any class in the simulation
*/


public class ControlPanel {
    
    private Master master;
    
    public ControlPanel() {
        master=null;
    }
    
    /*  output(msg)
        @author: Kane Templeton
        prints msg to the console along with the current master clock time
    */
    public void output(String msg) {
        master.output(msg);
    }
    
    /*  addEntity(E)
        @author: Kane Templeton
        adds an entity to the floor
    
        for creating floor entities, please see FloorEntity.java
    */
    public void addEntity(FloorEntity E) {
        master.getMasterFloor().addNewEntity(E);
    }
    public void addEntity(Tickable T) {
        master.getMasterFloor().addNewEntity(T);
    }
    
    /*  moveEntity(E,toX,toY)
        @author: Kane Templeton
        moves the specified floor entity to new coordinates (toX,toY)
    */
    public void moveEntity(FloorEntity E, int toX, int toY) {
        int x = E.getX();
        int y = E.getY();
        master.getMasterFloor().move(x, y, toX, toY);
    }
    
    /*  moveEntity(T, toX, toY)
        @author: Kane Templeton
        moves the specified Tickable object
        to new coordinates (toX,toY)
    
    */
    public void moveEntity(Tickable T, int toX, int toY) {
        int x = T.getX();
        int y = T.getY();
        master.getMasterFloor().move(x, y, toX, toY);
    }
    
    /*  moveEntity(fromX,fromY,toX,toY)
        @author: Kane Templeton
        moves the floor entity at coordinates (fromX,fromY)
        to new coordinates (toX,toY)
    */
    public void moveEntity(int fromX, int fromY, int toX, int toY) {
        master.getMasterFloor().move(fromX, fromY, toX, toY);
    }
    
    /*  cell(x,y)
        @author: Kane Templeton
        returns the floor cell at coordinates (x,y)
    */
    public Cell cell(int x, int y) {
        return master.getMasterFloor().getCell(x, y);
    }
    
    public Cell cell(Tickable T) {
        return master.getMasterFloor().getCell(T.getX(), T.getY());
    }
    
    public Robot getRobot() {
        return Production.getMaster().getMasterFloor().getRobot();
    }
    
    public Master getMaster(){return master;}
    public void setMaster(Master M) {
        master=M;
    }
    

}
