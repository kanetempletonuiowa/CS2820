package production;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;


public class Visualizer {
    
    /*  class Visualizer
        @author: Kane Templeton
        display a visualization of the simulation
    */
    
    private final JFrame visualizer;
    private final Canvas canvas;
    private final int width,height;
    private final Renderer gfx;
    
    private Graphics graphics;
    private BufferStrategy strategy;
    
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
        
        gfx = new Renderer(this);
        
        visualizer.add(canvas);
        visualizer.pack();
    }
    
    public void render() {gfx.render();}
    
    public Canvas getCanvas(){return canvas;}
    public JFrame getJFrame(){return visualizer;}
    public Graphics getGraphics(){return graphics;}
    public BufferStrategy getBufferStrategy(){return strategy;}

}
