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
    public static final int SHELF_ID = 3;
    public static final int ROBOT_ID = 4;
    public static final int PICKER_ID = 5;
    public static final int PACKER_ID = 6;
    
    
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
            case ROBOT_ID:
                return IMG_Robot;
            case SHELF_ID:
                return IMG_Shelf;
            case PICKER_ID:
                return IMG_Picker;
            case PACKER_ID:
                return IMG_Packer;
        }
        return null;
    }
    
    
    
    
    //Images
    private static BufferedImage IMG_Mock;
    private static BufferedImage IMG_Belt;
    private static BufferedImage IMG_Bin;
    private static BufferedImage IMG_Robot;
    private static BufferedImage IMG_Shelf;
    private static BufferedImage IMG_Picker;
    private static BufferedImage IMG_Packer;
    
    /*  initImages()
        load image data from files
    */
    public static void initImages() throws IOException {
        IMG_Mock = ImageIO.read(new FileInputStream("images/mock.png"));
        IMG_Belt = ImageIO.read(new FileInputStream("images/belt.png"));
        IMG_Bin = ImageIO.read(new FileInputStream("images/bin.png"));
        IMG_Robot = ImageIO.read(new FileInputStream("images/robot.png"));
        IMG_Shelf = ImageIO.read(new FileInputStream("images/shelf.png"));
        IMG_Picker = ImageIO.read(new FileInputStream("images/picker.png"));
        IMG_Packer = ImageIO.read(new FileInputStream("images/packer.png"));
    }
    
    
    
    
    
    //Floor stuff
    public static final int ROBOT_X[] = {15,15};
    public static final int ROBOT_Y[] = {6,8};
    public static final int SHELF_START[][] = {{5,5},{5,7},{5,9}};
    public static final int PACKER_POS = 5;
    public static final int PICKER_POS = 15;

}
