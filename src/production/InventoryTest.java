package production;

import java.io.FileNotFoundException;

import junit.framework.TestCase;

public class InventoryTest extends TestCase {

	public void testInStockAndStockItem() throws FileNotFoundException {
		Master m = new Master();
		Item i = m.getInventory().getRandomItem();
		assertFalse(m.getInventory().inStock(i));
		
		m.getInventory().stockItem(i);
		assertTrue(m.getInventory().inStock(i));
	}
	
	public void testStockShelves() throws FileNotFoundException {
		Master m = new Master();
		// Again we call stockShelves when initializing a master so i will clear the stock
		m.getInventory().Stock.clear();
		assertTrue(m.getInventory().Stock.isEmpty());
		m.getInventory().stockShelves();
		assertFalse(m.getInventory().Stock.isEmpty());
	}
	
	public void testGetShelf() throws FileNotFoundException {
		Master m = new Master();
		Item i = m.getInventory().getRandomItem();
		assertTrue(i.location == null);
		
		m.masterFloor.shelves[0].itemsOnShelf.add(i);
		i.location = m.masterFloor.shelves[0];
		assertEquals(i.location, m.getInventory().getShelf(i));
		
		Item i2 = m.getInventory().getRandomItem();
		m.getInventory().Stock.add(i2);
		//System.out.println(m.getInventory().inStock(i2));
		m.masterFloor.shelves[0].itemsOnShelf.add(i2);
		//System.out.println(m.masterFloor.shelves[0].itemsOnShelf.contains(i2));
		assertTrue(m.getInventory().getShelf(i2) != null);
		m.getInventory().Stock.clear();
		
	}
}
