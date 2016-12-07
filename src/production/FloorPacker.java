// Kane Templeton
// FloorPicker.java

package production;


public class FloorPacker implements Tickable {
    
    private int posX,posY;
    private boolean visible;
    
    public FloorPacker(int x, int y) {
        posX=x;
        posY=y;
        Production.controls().addEntity(this);
        visible=true;
    }
    
    public boolean isVisible() {return visible;}
    public void setVisible(boolean vis) {visible=vis;}

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
