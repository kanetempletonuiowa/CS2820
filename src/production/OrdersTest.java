package production;

import java.io.FileNotFoundException;

import junit.framework.TestCase;

public class OrdersTest extends TestCase {

	public void testInitialOrders() throws FileNotFoundException {
		Master m = new Master();
		// We have our sim set up to automatically initialize some orders, so i clear this first
		m.masterOrders.queuedOrders.clear();
		m.masterOrders.initialOrders(5);
		assertEquals(m.masterOrders.queuedOrders.size(), 5);
	}

}
