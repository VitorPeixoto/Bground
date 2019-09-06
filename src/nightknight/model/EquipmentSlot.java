package nightknight.model;

import nightknight.model.items.ArmorItem;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author peixoto
 */
public class EquipmentSlot extends Slot {
    private Image placeholder;
    
    public EquipmentSlot(float x, float y, float width, float height, Image placeholder) {
        super(x, y, width, height);
        this.placeholder = placeholder;
    }
    
    @Override
    public Item pushItem(Item item, boolean click) {
        if(this.isEmpty() && item instanceof ArmorItem) {
            this.item = item;
            return null;
        }
        return null;
    }
    
    @Override
    public void render(Graphics g) {
        g.pushTransform();
            g.translate(position.x, position.y);
            this.drawSlot(g, 0, 0, width, height, 0, 1);
            g.translate((width/2), (height/2));
            if(item != null) item.render(g);
            else placeholder.draw(-width/2, -height/2, width , height);
        g.popTransform();
    }
    
}
