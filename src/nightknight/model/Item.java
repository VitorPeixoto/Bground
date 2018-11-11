package nightknight.model;

import nightknight.assets.data.ItemType;
import nightknight.collision.RectangleObject;
import nightknight.constants.Sizes;
import nightknight.interfaces.Renderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Vitor
 */
public class Item extends RectangleObject implements Renderable {
    private ItemType type;
    private int amount;
    
    public Item(ItemType type, int amount) {
        this.type = type;
        this.amount = amount;
        
        this.width = this.height = Sizes.INVENTORY_SLOT_ITEM_SIZE;
    }
    
    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public void addAmount(int amount) {
        this.amount += amount;
    }

    public Item copy() {
        return new Item(type, amount);
    }
    
    @Override
    public void render(Graphics g) {
        g.pushTransform();
            g.translate(position.x, position.y);
            this.type.getImage().draw(-(width/2), -(height/2), width, height);
            g.setColor(Color.black);
            g.drawString(""+this.amount, (width/4), (height/4));
        g.popTransform();
    }
    
    public void renderOrigin(Graphics g) {
        g.pushTransform();
            //g.translate(x, y);
            this.type.getImage().draw(-(width/2), -(height/2), width, height);
            g.setColor(Color.black);
            g.drawString(""+this.amount, (width/4), (height/4));
        g.popTransform();
    }
}
