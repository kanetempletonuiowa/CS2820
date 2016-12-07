// Kane Templeton
// FloorPicker.java

package production;


public class FloorPicker implements Picker, Tickable {
    
    private int posX,posY;
    private Bin workingBin;
    private boolean visible;
    
    public FloorPicker(int x, int y) {
        posX=x;
        posY=y;
        workingBin=new Bin();
        visible=true;
        Production.controls().addEntity(this);
    }
    public boolean isVisible() {return visible;}
    public void setVisible(boolean vis) {visible=vis;}
    
    public void pickItems(Robot R, CustomerOrder order) {
        Shelf S = R.getShelf();
        for (Item I:order.getOrderItems()) {
            if (S.containsItem(I)&&!order.binItems().contains(I)) {
                workingBin.addItem(I);
                S.removeItem(I);
                order.binItems().add(I);
                Production.getMaster().getInventory().removeItem(I, 1);
            }
        }
        if (order.doneBinning()) {
            Production.getMaster().getMasterFloor().getBelt().placeBin(posY, workingBin);
            //workingBin.empty();
        }
            
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
