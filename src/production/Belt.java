package production;

import java.awt.Graphics;

class BeltSpace implements Tickable {
    
    /*  class BeltSpace
        @author: Kane Templeton
        Represents an individual space of the belt.
        Belt is made of a strip of these spaces on the floor
    */
    
    private int spaceX,spaceY;
    private Bin bin; //bin of items stored at the belt space
    
    public BeltSpace(int x, int y) {
        spaceX=x;
        spaceY=y;
        bin=null;
    }
    
    public void setBin(Bin b){bin=b;}
    public Bin getBin(){return bin;}

    /*  tick()
        @author: Kane Templeton
        transfer bin to the next belt space
    */
    public void tick() {
        Production.getMaster().masterBelt.advanceBin(spaceY);
    }

    /*  getX(),getY(),setCoordinates(x,y)
        @author: Kane Templeton
        not necessary for the belt to function, but they
        were necessary to implement the Tickable interface
    */
    public int getX() {return spaceX;}
    public int getY() {return spaceY;}
    public void setCoordinates(int x, int y) {
        spaceX=x;
        spaceY=y;
    }
    
    /*  getID()
        @author: Kane Templeton
        tells the visualizer which image to use
        uses the belt image if there is no bin on the space
        otherwise uses the bin image
    */
    public int getID() {
        if (bin==null)
            return Constants.BELT_ID;
        return Constants.BIN_ID;
    }
    
    /*  hasBin()
        @author: Kane Templeton
        returns true if the belt space contains a bin of items
    */
    public boolean hasBin() {
        return bin!=null;
    }
    
}

public class Belt implements Tickable {
    
    /*  class Belt
        @author: Kane Templeton
        Moves bins of items
    */
    
    private FloorEntity[] spaces;
    
    public Belt() {
        spaces = new FloorEntity[Production.FLOOR_SIZE];
        System.out.println(spaces.length);
        int x=0;
        int y=Production.FLOOR_SIZE-1;
        for (int i=0; i<spaces.length; i++) {
            BeltSpace s = new BeltSpace(x,y--);
            spaces[i]=new FloorEntity(s);
        }
        //test some bins
        BeltSpace s = belt(0);
        s.setBin(new Bin());
        belt(3).setBin(new Bin());
    }
    
    /*  advanceBin(id)
        @author: Kane Templeton
        advance bin to the next space on belt
    */
    public void advanceBin(int id) {
        if (id<0) 
            return;
        if (id>=spaces.length-1) {
            BeltSpace finalSpace = belt(spaces.length-1);
            Bin b = finalSpace.getBin(); //<- use this
            //TAKE ITEMS OUT OF THE BIN TO SHIP
            finalSpace.setBin(null);
            return;
        }
        
        BeltSpace spaceFrom = belt(id);
        BeltSpace spaceTo = belt(id+1);
        if (spaceFrom.hasBin()) {
            spaceTo.setBin(spaceFrom.getBin());
            spaceFrom.setBin(null);
        }
    }
    
    /*  render(g)
        @author: Kane Templeton
        render each space in the belt
    */
    public void render(Graphics g) {
        for (FloorEntity e:spaces)
            e.render(g);
    }
    
    
    public int getX(){return 0;}
    public int getY(){return Production.FLOOR_SIZE-1;}
    public void setCoordinates(int x, int y){} //not necessary
    public int getID(){return spaces[0].getEntityType();} //not necessary
    
    /*  space(y)
        @author: Kane Templeton
        returns the Belt Space at spacecs[y]
        y=0 is the beginning of the belt
    */
    private BeltSpace belt(int y) {
        return (BeltSpace)spaces[y].getTickable();
    }
    
    
    
    /*
        tick()
        @author: Kane Templeton
        belt ticks all of its spaces
    */
    public void tick() {
        for (FloorEntity e:spaces)
            e.getTickable().tick();
    }

}
