package production;


/**
 * 
 * @author Ted Herman
 */
public class Shelf {
  private boolean resting; // true if not being carried by a robot
  Point home;              // home location of shelf
  /**
   * Constructor needs home Point where shelf is born and 
   * returns after moving around
   */ 
  public Shelf(Point home) {
	this.home = new Point(home.x,home.y);
	resting = true;        // initially sits on Floor
    }
  /**
   * @return true if this Shelf is resting on the floor
   */
  public boolean onFloor() { return resting; }
  /**
   * pickup() is called by RobotScheduler, when a Robot comes
   * to the shelf and "picks it up" for moving; putdown() is 
   * similarly called when a Robot delivers the shelf back to
   * its home location.
   */
  public void pickup() {
	resting = false;
    }
  public void putdown() {
	resting = true;
    }
  public String toString() { return "Shelf"; }
  }
