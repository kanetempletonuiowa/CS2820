package production;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Stack;
import javax.swing.JFrame;


public class Visualizer {
    
    private final JFrame visualizer;
    private final Canvas canvas;
    private final int width,height;
    
    public Visualizer(int w, int h) {
        width=w;
        height=h;
        visualizer = new JFrame();
        visualizer.setTitle("Warehouse Simulation");
        visualizer.setSize(width,height);
        visualizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        visualizer.setResizable(false);
        visualizer.setLocationRelativeTo(null);
        visualizer.setVisible(true);
        
        canvas = new Canvas();
        Dimension d = new Dimension(width,height);
        canvas.setPreferredSize(d);
        canvas.setMaximumSize(d);
        canvas.setMinimumSize(d);
        canvas.setFocusable(false);
        
        visualizer.add(canvas);
        visualizer.pack();
    }
    
    public void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs==null) {
            canvas.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        //draw stuff here
        renderFloor(g);

        //end draw stuff
        
        bs.show();
        g.dispose();
        
    }
    
    
    private void renderFloor(Graphics g) {
        NewFloor f = Production.getMaster().getMasterFloor();
        
        Stack<FloorCoordinate> s = f.requiredUpdates();
        System.out.println(s.size());
        while (!s.isEmpty()) 
            s.pop().render(g);
        
        MockEntity e = new MockEntity(3,3);
        Production.getMaster().getActiveEntities().add(e);
        f.addNewEntity(e, 3, 3);
        
        
        /*for (int i=0; i<f.length(); i++) {
            for (int j=0;j<f.width();j++) {
                
                g.setColor(Color.red);
                int size = Production.SQUARE_SIZE;
                g.drawRect(i*size, j*size, size, size);
                g.setColor(Color.blue);
                g.fillRect(i*size+1, j*size+1, size-1, size-1);
            }
        }*/
    }
    
    public Canvas getCanvas(){return canvas;}
    public JFrame getJFrame(){return visualizer;}

}
