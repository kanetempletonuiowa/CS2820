package production;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Floor {
    
    private ArrayList<FloorEntity> entities;
    private Cell[][] grid;
    private int length,width;
    
    public static final int SHELVES_PER_ROW= 10;
    
    private FloorPicker picker;
    private FloorPacker packer;
    Shelf[] shelves;
    
    private Robot floorBot;
        
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
            for (int j=Constants.SHELF_START[i][0]; j<Constants.SHELF_START[i][0]+SHELVES_PER_ROW; j++) {
                shelves[k]= new Shelf(j,Constants.SHELF_START[i][1]);
                shelves[k].setIndex(k);
                k++;
            }
        }
        picker = new FloorPicker(1,Constants.PICKER_POS);
        packer = new FloorPacker(1,Constants.PACKER_POS);
        floorBot = new Robot(1,19,0);
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
    public void removeEntity(Tickable T) {
        Cell C = grid[T.getX()][T.getY()];
        FloorEntity e = C.getEntity();
        C.setEntity((FloorEntity)null);
        entities.remove(e);
    }
    
    public Cell pickerLocation() {
        return Production.controls().cell(2,Constants.PICKER_POS);
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
    
    /*  getPath(start, end)
        @author: Kane Templeton
        returns a list of cells that defines a walking path from start to end
        note: for this to work, floor must not have any 'dead ends'
    */
    public LinkedList<Cell> getPath(Cell start, Cell end) {
        int x = start.getX();
        int y = start.getY();
        int endX = end.getX();
        int endY = end.getY();
        LinkedList<Cell> path = new LinkedList();
        path.addLast(start);
        while (x!=endX||y!=endY) {
            if (x>endX) { //must walk left
                Cell c = Production.controls().cell(x-1, y);
                if (c.walkable()) {
                    path.addLast(c);
                    x--;
                }
                else if (y==endY) {
                    c= Production.controls().cell(x, y);
                    while (!c.left().walkable()) {
                        y++;
                        c = Production.controls().cell(x, y);
                        path.addLast(c);
                    }
                    x--;
                    c=Production.controls().cell(x, y);
                    path.addLast(c);
                }
            }
            else if (x<endX) { //must walk right
                Cell c = Production.controls().cell(x+1, y);
                if (c.walkable()) {
                    path.addLast(c);
                    x++;
                }
                else if (y==endY) {
                    c= Production.controls().cell(x, y);
                    while (!c.right().walkable()) {
                        y++;
                        c = Production.controls().cell(x, y);
                        path.addLast(c);
                    }
                    y--;
                    c=Production.controls().cell(x, y);
                    path.addLast(c);
                }
            }
            if (y>endY) { //must walk up
                Cell c = Production.controls().cell(x, y-1);
                if (c.walkable()) {
                    path.addLast(c);
                    y--;
                }
                else if (x==endX) {
                    c= Production.controls().cell(x, y);
                    while (!c.above().walkable()) {
                        x++;
                        c = Production.controls().cell(x, y);
                        path.addLast(c);
                    }
                    y--;
                    c=Production.controls().cell(x, y);
                    path.addLast(c);
                }
            }
            else if (y<endY) { //must walk down
                Cell c = Production.controls().cell(x, y+1);
                if (c.walkable()) {
                    path.addLast(c);
                    y++;
                }
                else if (x==endX) {
                    c= Production.controls().cell(x, y);
                    while (!c.under().walkable()) {
                        x++;
                        c = Production.controls().cell(x, y);
                        path.addLast(c);
                    }
                    y++;
                    c=Production.controls().cell(x, y);
                    path.addLast(c);
                }
            }
        }
        
        
        return path;
    }
    
    
    public int length(){return length;}
    public int width(){return width;}
    public ArrayList<FloorEntity> getEntities(){return entities;}
    public Robot getRobot() {return floorBot;}

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
    public int numberOfShelves() {
        return Constants.SHELF_START.length*SHELVES_PER_ROW;
    }


    public Cell randomInShelfArea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
