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
    public static final int CHARGER_ID=7;
    
    
     /* getImage(id)
        image associated with id
        used for visualizer
     */
    public static BufferedImage getImage(int id) {
        switch (id) {
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
            case CHARGER_ID:
                return IMG_Charger;
        }
        return null;
    }
    
    
    
    
    //Images
    private static BufferedImage IMG_Belt;
    private static BufferedImage IMG_Bin;
    private static BufferedImage IMG_Robot;
    private static BufferedImage IMG_Shelf;
    private static BufferedImage IMG_Picker;
    private static BufferedImage IMG_Packer;
    private static BufferedImage IMG_Charger;
    
    /*  initImages()
        load image data from files
    */
    public static void initImages() throws IOException {
        IMG_Belt = ImageIO.read(new FileInputStream("img/Belt.png"));
        IMG_Bin = ImageIO.read(new FileInputStream("img/Bin.png"));
        IMG_Robot = ImageIO.read(new FileInputStream("img/Robot.png"));
        IMG_Shelf = ImageIO.read(new FileInputStream("img/Shelf.png"));
        IMG_Picker = ImageIO.read(new FileInputStream("img/Picker.png"));
        IMG_Packer = ImageIO.read(new FileInputStream("img/Packer.png"));
        IMG_Charger = ImageIO.read(new FileInputStream("img/Charger.png"));
    }
    
    
    
    
    
    //Floor stuff
    public static final int ROBOT_X[] = {19,18};
    public static final int ROBOT_Y[] = {0,0};
    public static final int SHELF_START[][] = {{5,5},{5,10}};
    public static final int PACKER_POS = 5;
    public static final int PICKER_POS = 15;
    public static final int CHARGER_X = 12;
    public static final int CHARGER_Y = 15;
    
    //order status
    public static final String PENDING = "pending";
    public static final String COMPLETE = "complete";
    
    //path types
    public static final int STANDARD=0;
    public static final int GRAB_SHELF=1;
    public static final int DELIVER_SHELF=2;
    public static final int RETURN_SHELF=3;
    public static final int CHARGE=4;

}
