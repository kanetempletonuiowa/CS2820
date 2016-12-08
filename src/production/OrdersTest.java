package production;

import java.io.FileNotFoundException;

//import junit.framework.TestCase;

//Had to comment out Junit tests to get this to work on my computer -Kane

public class OrdersTest{// extends TestCase {

	public void testInitialOrders() throws FileNotFoundException {
		Master m = new Master();
		// We have our sim set up to automatically initialize some orders, so i clear this first
		m.masterOrders.queuedOrders.clear();
		m.masterOrders.initialOrders(5);
		//assertEquals(m.masterOrders.queuedOrders.size(), 5);
	}

}
