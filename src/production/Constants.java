package production;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Constants {
    
    /*  class Constants
        @author: Kane Templeton
        lots of values that will
        need to be referenced
    */
    
    //IDs of entities
    public static final int MOCK_ID = 0;
    public static final int BELT_ID = 1;
    public static final int BIN_ID = 2;
    
    
     /* getImage(id)
        image associated with id
        used for visualizer
     */
    public static BufferedImage getImage(int id) {
        switch (id) {
            case MOCK_ID:
                return IMG_Mock;
            case BELT_ID:
                return IMG_Belt;
            case BIN_ID:
                return IMG_Bin;
        }
        return null;
    }
    
    
    
    
    //Images
    private static BufferedImage IMG_Mock;
    private static BufferedImage IMG_Belt;
    private static BufferedImage IMG_Bin;
    
    /*  initImages()
        load image data from files
    */
    public static void initImages() throws IOException {
        IMG_Mock = ImageIO.read(new FileInputStream("images/mock.png"));
        IMG_Belt = ImageIO.read(new FileInputStream("images/belt.png"));
        IMG_Bin = ImageIO.read(new FileInputStream("images/bin.png"));
    }

}
