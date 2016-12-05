package production;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Ted Herman
 *
 */
public class ShelfArea {
  int width; // height will always be 2 -- just two shelves
  Cell corner;  // lower left corner of shelf area
  List<Cell> areacontents;
  SimRandom randomsource; // for deterministic randomness
  /**
   * @param corner - lower left corner of shelf area
   * @param width - how many squares wide shelf area is
   */
  ShelfArea(Cell corner, int width, SimRandom rand) {
	areacontents = new ArrayList<Cell>();
	randomsource = rand; 
	this.corner = new Cell(corner.getX(),corner.getY());
	this.width = width;
	for (int i=corner.getY()-1; i<corner.getY()+1; i++)
	 for (int j=corner.getX(); j<corner.getX()+width; j++) {
	   areacontents.add(new Cell(j,i));
       }
	populate();  // fill with shelves
    }
  /**
   * @return Point in lower left corner of
   * this ShelfArea.
   */
  Cell getCorner() { return corner; }
  /**
   * @return width of this ShelfArea
   */
  int getWidth() { return width; }
  /**
   * @return height of this ShelfArea
   */
  int getHeight() { return 2; }
  /**
   * @return a cell in this ShelfArea
   * at a specified Point (x,y)
   */
  Cell getCell(Cell P) {
	for (Cell e: areacontents) {
	  if (e.equals(P)) return e;
	  }
	return null;
    }
  /**
   * fill this shelfarea with Shelf objects in each Cell;
   * this will be called by the constructor
   */
  void populate() {
	for (Cell e: areacontents) {
	  e.setEntity(new Shelf(e.getX(),e.getY()));
	  }
    }
  /**
   * @return true if point P is within this ShelfArea
   * @param P point to test
   */
  boolean hasWithin(Cell P) {
	if (P.getX() < corner.getX()) return false;
	if (P.getX() >= corner.getX() + width) return false;
	if (P.getY() > corner.getY()) return false;
	if (P.getY() < corner.getY()-1) return false;
	return true;
    }
  /**
   * @return true if cell P has non-null content (robot or shelf)
   */
  boolean occupied(Cell P) {
	if (this.hasWithin(P) && P.getEntity()!= null) return true;
	return false;
    }
  /**
   * @param Object to place in a Point
   * @note if Object is null, then makes Point P empty
   */
  void setContent(Cell P, Tickable O) {
	if (!occupied(P) && hasWithin(P)) {
	  P.setEntity(O);	
	  }
    }
  /**
   * @return random Point within this shelfarea
   */
  Cell randomPoint() {
	int column = randomsource.nextInt(width);
	int row = randomsource.nextInt(2);
	Cell P = new Cell(corner.getX()+column,corner.getY()-row);
	assert hasWithin(P);
	return P;
    }
 }