// Kane Templeton
// MockEntity.java

package production;


public class MockEntity implements Tickable {
    
    private int xPos,yPos;
    
    public MockEntity(int x, int y) {
        xPos=x;
        yPos=y;
    }


    @Override
    public void tick() {
        Production.getMaster().getMasterFloor().move(xPos, yPos, xPos+1, yPos+1);
    }

}
