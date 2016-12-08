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
        renderRobotCharge(g);
        
        
        //processing stuff
        bs.show();
        g.dispose();
    }
    

    private void renderBelt(Graphics g) {
        Production.getMaster().getMasterFloor().getBelt().render(g);
    }
    
    private void renderFloor(Graphics g) {
        
        Floor f = Production.getMaster().getMasterFloor();
        
        for (int i=0; i<f.length(); i++) {
            for (int j=0;j<f.width();j++) {
                
                g.setColor(Color.black);
                int size = Production.SQUARE_SIZE;
                g.drawRect(i*size, j*size, size, size);
                g.setColor(Color.gray);
                g.fillRect(i*size, j*size, size, size);
            }
        }

    }
    
    private void renderActiveEntities(Graphics g) {
        ArrayList<FloorEntity> L = Production.getMaster().getMasterFloor().getEntities();
        L.stream().forEach((e) -> {e.render(g);});
    }
    
    private void renderRobotCharge(Graphics g) {
        Robot R = Production.getMaster().getMasterFloor().getRobot();
        int x = R.getX();
        int y = R.getY();
        int size = Production.SQUARE_SIZE;
        if (R.charge<=0)
            g.setColor(Color.BLACK);
        else if (R.charge<=10)
            g.setColor(new Color(255,0,0));
        else if (R.charge<=20)
            g.setColor(new Color(255,94,0));
        else if (R.charge<=30)
            g.setColor(new Color(255,171,0));
        else if (R.charge<=40)
            g.setColor(new Color(255,213,0));
        else if (R.charge<=50)
            g.setColor(new Color(255,239,0));
        else if (R.charge<=60)
            g.setColor(new Color(240,255,0));
        else if (R.charge<=70)
            g.setColor(new Color(180,255,0));
        else if (R.charge<=80)
            g.setColor(new Color(120,255,0));
        else if (R.charge<=90)
            g.setColor(new Color(60,255,0));
        else
            g.setColor(new Color(0,255,15));
        g.fillRect(x*size, y*size+(5*size/6), size, size/6);
    }
    

}
