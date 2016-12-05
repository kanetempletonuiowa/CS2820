// Kane Templeton
// FloorPicker.java

package production;


public class FloorPacker implements Tickable {
    
    private int posX,posY;
    
    public FloorPacker(int x, int y) {
        posX=x;
        posY=y;
        Production.controls().addEntity(this);
    }

    @Override
    public void tick() {
        //pack items
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
        return Constants.PACKER_ID;
    }

    @Override
    public void setCoordinates(int x, int y) {
        posX=x;
        posY=y;
    }

}
