package production;

/**
 * 
 * @author Ted Herman
 * 
 * A Bin contains an Order that has all its items ready to go
 *
 */
public class Bin {
  CustomerOrder order;  // ready to go on the Belt
  boolean finished;  // initially false, becomes true when ready to go
  public Bin() { order = null; finished = false; }
  public boolean isFinished() { return finished; }
  public void setFinished() { finished = true; }
  public CustomerOrder getOrder() { return order; }
  public void setOrder(CustomerOrder o) { order = o; }
  public String toString() { return "Bin"; }
  }
