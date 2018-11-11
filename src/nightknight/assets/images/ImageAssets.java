package nightknight.assets.images;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Vitor
 */
public class ImageAssets {
    private static HashMap<String, Image> images = new HashMap<>();
    
    public static Image getImage(String name) {
        if(name == null) return null;
        if(!images.containsKey(name)) {
            try {
                Image img = new Image(ImageAssets.class.getResource(name).getPath(), false, Image.FILTER_NEAREST);
                images.put(name, img);
            } catch (SlickException ex) {
                Logger.getLogger(ImageAssets.class.getName()).log(Level.SEVERE, null, ex);
            } catch(NullPointerException npe) {
                Logger.getLogger(ImageAssets.class.getName()).log(Level.SEVERE, null, npe);
                System.out.println("Resource Name: "+name);
            }
        }
        return images.get(name);
    }

    public static Image getImage(String folder, String name) {
        if(name == null) return null;
        return getImage(folder+name);
    }
}
