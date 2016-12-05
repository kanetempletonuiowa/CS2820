package production;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;


public class Renderer {
    
    /*  class Renderer
        @author: Kane Templeton
        renders everything from the visualizer to the screen
    */
    
    private Visualizer vis;
    
    public Renderer(Visualizer v) {
        vis=v;
    }
    
    /*  render()
        @author: Kane Templeton
        where all the drawing happens
    */
    public void render() {
        
        //initialize stuff
        BufferStrategy bs = vis.getCanvas().getBufferStrategy();
        if (bs==null) {
            vis.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        
        //rendering
        renderFloor(g);
        renderBelt(g);
        renderActiveEntities(g);
        
        
        //processing stuff
        bs.show();
        g.dispose();
    }
    

    private void renderBelt(Graphics g) {
        Production.getMaster().masterBelt.render(g);
    }
    
    private void renderFloor(Graphics g) {
        
        Floor f = Production.getMaster().getMasterFloor();
        
        for (int i=0; i<f.length(); i++) {
            for (int j=0;j<f.width();j++) {
                
                g.setColor(Color.red);
                int size = Production.SQUARE_SIZE;
                g.drawRect(i*size, j*size, size, size);
                g.setColor(Color.blue);
                g.fillRect(i*size+1, j*size+1, size-1, size-1);
            }
        }

    }
    
    private void renderActiveEntities(Graphics g) {
        ArrayList<FloorEntity> L = Production.getMaster().getMasterFloor().getEntities();
        L.stream().forEach((e) -> {e.render(g);});
    }
    

}
