package production;

import java.util.*;
public class Belt implements Tickable {
	
  Floor F;
  List<Point> beltarea;
  Bin pickerBin;

  /**
   * @author Ted Herman
   * @param Floor object.
   * Floor is needed to find location of belt area, cells etc.
   */
  public Belt(Floor F) {
	this.F = F;
	beltarea = F.getBeltArea();
	pickerBin = null;
    }
  
  /**
   * the tick() method is where belt moving gets done;
   * it will have to move any Bin or Parcel within the Cell
   * of a Belt area to the next Cell, and this has to be done
   * on all Points of the beltarea in parallel (not coded yet here)
   * 
   * after moving the belt, tick() should check to see whether
   * or not a Bin has arrived at the Packer - then doPacker() 
   * should be called, which removes the Bin, creates a Parcel 
   * and puts that Parcel on the belt (in more advanced versions,
   * one Bin might make more than one Parcel, if Items are too 
   * big to fit entirely into one Parcel). After the Parcel is
   * in a Cell at the Packer, the belt will be stopped until some
   * later tick when the Packer finishes the Parcel.
   * 
   * even fancier ideas are to give the Packer a queue of Bins
   * and remove each Bin that arrives, taking some non-trivial
   * number of ticks to make Parcels, returning them to the 
   * beltarea when they are completed
   * 
   * and a really thorough Belt would simulate the shipping dock,
   * collecting a lot of parcels and grouping them into a truck
   *
   */
  public void tick() {
	// first take care of a finished bin
	if (pickerBin != null) {
      if (!pickerBin.isFinished()) return; // belt cannot move
      Cell c = F.getCell(F.getPicker());   // look into Picker cell
      if (c.getContents()!=null) return;   // wait for cell to empty
      c.setContents(pickerBin);
      pickerBin = null;
	  }
	// if belt is movable, loop to copy cells forward
	if (!isMovable()) return;
	Object prev = null;  // temporary variable used in copy forward
	for (Point p: beltarea) {
	  Cell c = F.getCell(p);
	  Object t = c.getContents(); // save what it has for next time
	  c.setContents(prev);        // write over what it was 
	  prev = t;
	  }
	if (prev != null) System.out.println("something dropped off belt");
    }
  
  /**
   * Local method to see whether belt can be moved
   */
  private boolean isMovable() {
	if (pickerBin != null) return false;  // wait for picker to finish bin
	for (Point p: beltarea) {
	  Cell c = F.getCell(p);
	  Object o = c.getContents();
	  if (o == null) continue;  // skip empty cell
	  if ((o instanceof Bin) && !((Bin)o).isFinished()) return false;
	  if ((o instanceof Parcel) && !((Parcel)o).isFinished()) return false;
	  }
	return true;  // nothing stops belt from moving
    }
  
  /**
   * Local method doPacker() simulates a Bin arriving to the 
   * Packer via the belt moving. 
   */
  private void doPacker() {
	Cell c = F.getCell(F.getPacker());
	Object o = c.getContents();  // get what the Cell has in it
	assert o instanceof Bin;     // it had better be a Bin
	Bin b = (Bin)o;              // use the Bin to
	CustomerOrder v = b.getOrder();      // get the finished Order
	Parcel n = new Parcel(v);
	c.setContents(n);  // replace Bin with Parcel on the belt
    }
  
  /**
   * @author Ted Herman
   * Called by Orders to check whether a new Bin can be safely started
   */
  public boolean binAvailable() {
	if (pickerBin != null) return false;
	Cell c = F.getCell(F.getPicker());
	if (c.getContents() != null) return false;
    return true;
    }
  /**
   * Called by Orders to simulate a Picker starting a new Bin
   */
  public Bin getBin() {
	assert pickerBin == null;
	pickerBin = new Bin();
	return pickerBin;
    }


  }
