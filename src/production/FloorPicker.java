// Kane Templeton
// FloorPicker.java

package production;


public class FloorPicker implements Picker, Tickable {
    
    private int posX,posY;
    
    public FloorPicker(int x, int y) {
        posX=x;
        posY=y;
        Production.controls().addEntity(this);
    }

    @Override
    public void notify(Robot r, Shelf s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pickItems(CustomerOrder order, Shelf s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tick() {
        //pick items
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
        return Constants.PICKER_ID;
    }

    @Override
    public void setCoordinates(int x, int y) {
        posX=x;
        posY=y;
    }

}
