package production;

import java.util.ArrayList;
import java.util.Stack;


public class NewFloor {
    
    private ArrayList<FloorEntity> entities;
    private FloorCoordinate[][] grid;
    private int length,width;
    
    private Stack<FloorCoordinate> updateRequired;
    
    public NewFloor(int l, int w) {
        length=l;
        width=w;
        grid = new FloorCoordinate[l][w];
        updateRequired = new Stack<>();
        for (int i=0; i<l; i++)
            for (int j=0; j<w; j++) {
                grid[i][j]=new FloorCoordinate(i,j);
                updateRequired.push(grid[i][j]);
            }
        entities=new ArrayList<>();
        
    }
    
    public void addNewEntity(Tickable t, int x, int y) {
        FloorEntity e = new FloorEntity(t,x,y);
        entities.add(e);
        grid[x][y].setEntity(e);
        updateRequired.push(grid[x][y]);
    }
    
    public Stack requiredUpdates(){return updateRequired;}
    
    /*
        move(xFrom,yFrom,xTo,yTo)
        @author: Kane Templeton
        move entity at coordinates (xFrom,yFrom)
        to new coordinates (xTo,yTo)
    */
    public void move(int xFrom, int yFrom, int xTo, int yTo) {
        FloorEntity e = grid[xFrom][yFrom].getEntity();
        if (e==null)
            return;
        grid[xFrom][yFrom].setEntity(null);
        grid[xTo][yTo].setEntity(e);
        e.setCoordinates(xTo, yTo);
        updateRequired.push(grid[xTo][yTo]);
    }
    
    public FloorEntity entityAt(int x, int y) {
        return grid[x][y].getEntity();
    }
    
    
    public int length(){return length;}
    public int width(){return width;}

}
