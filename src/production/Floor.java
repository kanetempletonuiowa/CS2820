package production;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Floor {
    
    private ArrayList<FloorEntity> entities;
    private Cell[][] grid;
    private int length,width;
    
    public static final int SHELVES_PER_ROW= 10;
    
    private FloorPicker picker;
    private FloorPacker packer;
    private Shelf shelves[];
        
    public Floor(int l, int w) {
        length=l;
        width=w;
        grid = new Cell[l][w];
        for (int i=0; i<l; i++)
            for (int j=0; j<w; j++) {
                grid[i][j]=new Cell(i,j);
            }
        entities=new ArrayList<>();
    }
    
    public void initFloor() {
        //will clean this up later
        shelves = new Shelf[SHELVES_PER_ROW*Constants.SHELF_START.length];
        int k=0;
        for (int i=0; i<Constants.SHELF_START.length; i++) {
            for (int j=Constants.SHELF_START[i][0]; j<Constants.SHELF_START[i][0]+SHELVES_PER_ROW; j++)
                shelves[k++]= new Shelf(j,Constants.SHELF_START[i][1]);
        }
        picker = new FloorPicker(1,Constants.PICKER_POS);
        packer = new FloorPacker(1,Constants.PACKER_POS);
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
        grid[xFrom][yFrom].setEntity((FloorEntity)null);
        grid[xTo][yTo].setEntity(e);
        e.setCoordinates(xTo, yTo);
    }
    
    private boolean outOfBounds(int check) {
        return (check<0 || check>=Production.FLOOR_SIZE);
    }
    
    public FloorEntity entityAt(int x, int y) {
        return grid[x][y].getEntity();
    }
    
    public Cell getCell(int x, int y) {
        return grid[x][y];
    }
    
    public List<Cell> getPath(Cell start, Cell end) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public int length(){return length;}
    public int width(){return width;}
    public ArrayList<FloorEntity> getEntities(){return entities;}

    public int getWarehouseWidth() {
        return Production.FLOOR_SIZE;
    }

    public int getWarehouseDepth() {
        return Production.FLOOR_SIZE;
    }

    public Cell getPicker() {
        return Production.controls().cell(picker.getX(), picker.getY());
    }

    public Cell getPacker() {
        return Production.controls().cell(packer.getX(), packer.getY());
    }

    public Cell getShippingDock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cell getReceivingDock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cell getCharger() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Cell> getBeltArea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cell getCell(Cell P) {
        return P;
    }

    public int getNumShelfAreas() {
        return Constants.SHELF_START.length;
    }

    public ShelfArea getShelfArea(int which) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cell randomInShelfArea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
