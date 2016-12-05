// Kane Templeton
// Production.java

package production;


public class Production {
    
    private static Master master;
    
    public static void main(String[] args) {
        master = new Master();
        master.orders.initialOrders(2);
        master.start();
    }
    
    public static Master getMaster() {
        return master;
    }
    
    public static void output(String msg) {
        master.output(msg);
    }
    
    public static void addEvent(Event e) {
        master.addEvent(e);
    }

}
