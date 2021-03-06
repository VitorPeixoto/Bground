package nightknight;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import nightknight.constants.Sizes;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Vitor
 */
public class Main {
    public static boolean DEBUG = true;
    public static void main(String[] args) {
        /*System.setProperty("java.library.path", "libraries");
        //System.setProperty("org.lwjgl.librarypath", new File("libraries/natives").getAbsolutePath());
        System.setProperty("org.lwjgl.librarypath", "libraries/natives");*/
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();   
        //Dimension dimension = toolkit.getScreenSize();
        Dimension dimension = new Dimension(1600, 900);
        
        Sizes.SCREEN_WIDTH = dimension.width;
        Sizes.SCREEN_HEIGHT = dimension.height;

        Sizes.LINES_PER_SCREEN   = (int)Math.ceil((double)Sizes.SCREEN_HEIGHT/Sizes.TILE_SIZE);
        Sizes.COLUMNS_PER_SCREEN = (int)Math.ceil((double)Sizes.SCREEN_WIDTH/Sizes.TILE_SIZE);
        
        try {            
            //AppGameContainer app = new AppGameContainer(new Game((dimension.width-(30*32))/2, (dimension.height-(30*18))/2));
            AppGameContainer app = new AppGameContainer(new Game());
            
            app.setDisplayMode(dimension.width, dimension.height, true);
            //app.setDisplayMode(1280, 768, true);
            //GameController gc = GameController.getInstance();
            app.setVSync(true);
            app.setShowFPS(true);
            app.setAlwaysRender(true);
            app.start();
        } catch (SlickException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
}
