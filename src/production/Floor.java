package production;

import java.util.ArrayList;


public class Floor {
    
    private ArrayList<FloorEntity> entities;
    private FloorCoordinate[][] grid;
    private int length,width;
        
    public Floor(int l, int w) {
        length=l;
        width=w;
        grid = new FloorCoordinate[l][w];
        for (int i=0; i<l; i++)
            for (int j=0; j<w; j++) {
                grid[i][j]=new FloorCoordinate(i,j);
            }
        entities=new ArrayList<>();
        
    }
    
    public void addNewEntity(Tickable t) {
        FloorEntity e = new FloorEntity(t);
        entities.add(e);
        grid[t.getX()][t.getY()].setEntity(e);
    }
    public void addNewEntity(FloorEntity E) {
        entities.add(E);
        grid[E.getX()][E.getY()].setEntity(E);
    }
    
    
    /*
        move(xFrom,yFrom,xTo,yTo)
        @author: Kane Templeton
        move entity at coordinates (xFrom,yFrom)
        to new coordinates (xTo,yTo)
    */
    public void move(int xFrom, int yFrom, int xTo, int yTo) {
        if (outOfBounds(xFrom)||outOfBounds(yFrom)||
                outOfBounds(xTo)||outOfBounds(yTo))
            return;
        FloorEntity e = grid[xFrom][yFrom].getEntity();
        if (e==null)
            return;
        grid[xFrom][yFrom].setEntity(null);
        grid[xTo][yTo].setEntity(e);
        e.setCoordinates(xTo, yTo);
    }
    
    private boolean outOfBounds(int check) {
        return (check<0 || check>=Production.FLOOR_SIZE);
    }
    
    public FloorEntity entityAt(int x, int y) {
        return grid[x][y].getEntity();
    }
    
    
    public int length(){return length;}
    public int width(){return width;}
    public ArrayList<FloorEntity> getEntities(){return entities;}

}
