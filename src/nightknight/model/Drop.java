package nightknight.model;

import java.util.Random;
import nightknight.assets.data.ItemType;
import nightknight.collision.RectangleObject;
import nightknight.constants.Sizes;
import nightknight.interfaces.Renderable;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Vitor
 */
public class Drop extends RectangleObject implements Renderable {
    private ItemType type;
    private int min, max;
    private int multiplier;
    private float x, y;
    private float width, height;
    private float anim = 0;
    private short up = 1;
    
    private Animation animation;
    private static Random r = new Random();
    
    public Drop(ItemType type, int min, int max, int multiplier) {
        this.type = type;
        this.min = min;
        this.max = max;
        this.multiplier = multiplier;
        animation = new Animation(-10, 10, 0.4f, 0, Animation.AnimationType.INVERT);
        
        this.setScale(Sizes.TILE_SIZE);
    }
    
    public Drop(ItemType type) {
        this.type = type;
        this.min = this.max = this.multiplier = 1;
    }
    
    public Item getItem() {
        /* Calcula quantidade */
        /* Cria objeto do tipo Item */
        /* Retorna Item */
        int amount = min+r.nextInt(max-min+1);
        return new Item(type, amount);
    }
    
    public Drop copy() {
        return new Drop(type, min, max, multiplier);
    }

    @Override
    public void render(Graphics g) {
        animation.update();
        g.pushTransform();            
            g.translate((x+0.5f)*Sizes.TILE_SIZE, (Sizes.MAP_HEIGHT-y+0.5f)*Sizes.TILE_SIZE);
            this.type.getImage().draw(-(width/2), -(height/2)+animation.getValue(), width, height);
        g.popTransform();
    }
}
