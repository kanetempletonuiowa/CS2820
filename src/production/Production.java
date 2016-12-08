package production;

import java.io.IOException;

/*  MASTER TODO:
    - add path finding to floor
    - master orders working
    - inventory
    - shelves containing items
    - finish this todo list
*/



public class Production {
    
    private static ControlPanel panel = new ControlPanel();
    private static Visualizer visualizer;
    private static FileManager fileManager;

    public static final int SQUARE_SIZE=32;
    public static final int FLOOR_SIZE=20;
    
    
    public static void main(String[] args) throws IOException {
        //panel = new ControlPanel();
        fileManager = new FileManager();
        Master M = new Master();
        M.initializeEvents();
        Constants.initImages();
        showVisualizer(false);
        panel.getMaster().start();
        
    }
    
    public static void showVisualizer(boolean show) {
        if (show)
            visualizer = new Visualizer(FLOOR_SIZE*SQUARE_SIZE,FLOOR_SIZE*SQUARE_SIZE);
        else
            visualizer = null;
    }
    

    
    public static Master getMaster() {
        return panel.getMaster();
    }
    
    public static ControlPanel controls(){return panel;}
    public static Visualizer visualizer() {return visualizer;}
    public static FileManager fileManager() {return fileManager;}

}
