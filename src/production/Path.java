// Kane Templeton
// Path.java

package production;

import java.util.LinkedList;

//store List created in Floor.getPath into its own Path object
//so we don't need to recalculate the path


public class Path {
    
    private LinkedList<Cell> path;
    private Cell start,end;
    private int pathType;
    
    public Path(Tickable T, Cell end, int type) {
        Cell c = Production.controls().cell(T.getX(), T.getY());
        path=Production.getMaster().getMasterFloor().getPath(c, end);
        this.start=c;
        this.end=end;
        pathType = type;
    }
    
    public Path(Tickable T, Cell end) {
        Cell c = Production.controls().cell(T.getX(), T.getY());
        path=Production.getMaster().getMasterFloor().getPath(c, end);
        this.start=c;
        this.end=end;
        pathType = Constants.STANDARD;
    }
    
    public Path(Cell start, Cell end) {
        path=Production.getMaster().getMasterFloor().getPath(start, end);
        this.start=start;
        this.end=end;
        pathType = Constants.STANDARD;
    }
    
    
    public Path(Cell start, Cell end, int type) {
        path=Production.getMaster().getMasterFloor().getPath(start, end);
        this.start=start;
        this.end=end;
        pathType = type;
    }

    public void complete() {
        Robot R = Production.controls().getRobot();
        switch(pathType) {
            case Constants.GRAB_SHELF:
                Shelf S = (Shelf)Production.controls().cell(R).under().getEntity().getTickable();
                R.setShelf(S);
                Production.getMaster().getMasterFloor().removeEntity(S);
                Path P = new Path(R,Production.getMaster().getMasterFloor().pickerLocation(),Constants.DELIVER_SHELF);
                Production.getMaster().getRobotScheduler().setRobotPath(P);
                break;
                
               
        }
    }
    
    public Cell next() {
        return path.removeFirst();
    }
    public Cell start() {return start;}
    public Cell end() {return end;}
    public int length() {return path.size();}
    public boolean hasNext() {return !path.isEmpty();}
    public int getType(){return pathType;}

}
