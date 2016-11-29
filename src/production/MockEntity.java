package production;


public class MockEntity implements Tickable {
    
    /*  class MockEntity
        @author: Kane Templeton
        mock floor entity for testing
    */
    
    private int xPos,yPos;
    
    public MockEntity(int x, int y) {
        xPos=x;
        yPos=y;
    }
    
    public int getX(){return xPos;}
    public int getY(){return yPos;}
    public void setCoordinates(int x, int y) {
        xPos=x;
        yPos=y;
    }


    @Override
    public void tick() {
        Production.getMaster().getMasterFloor().move(xPos, yPos, xPos+1, yPos+1);
    }
    
    public int getID() {
        return Constants.MOCK_ID;
    }

}
