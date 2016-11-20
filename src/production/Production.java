package production;


public class Production {
    
    private static Master master;
    private static Visualizer visualizer;
    
    public static final int LENGTH=20;
    public static final int WIDTH=20;
    public static final int SQUARE_SIZE=32;
    
    
    public static void main(String[] args) {
        master = new Master();
        master.initializeEvents();
        initVisualizer();
        master.start();   
    }
    
    public static void initVisualizer() {
        visualizer = new Visualizer(LENGTH*SQUARE_SIZE,WIDTH*SQUARE_SIZE);
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
    
    public static Visualizer getVisualizer() {return visualizer;}

}
