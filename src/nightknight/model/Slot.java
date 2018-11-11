package nightknight.model;

import nightknight.collision.RectangleObject;
import nightknight.constants.Sizes;
import nightknight.interfaces.Renderable;
import nightknight.model.items.AnyItem;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Vitor
 */
public class Slot<T extends Item & AnyItem> extends RectangleObject implements Renderable {
    protected T item;
    
    protected Color shadow, bright, fill;
    
    public Slot(float x, float y, float width, float height) {
        super(x, y, width, height);
        item = null;
        shadow = new Color(Color.black);
        bright = new Color(Color.white);
        fill   = new Color(Color.gray);
    }
    
    public boolean pushItem(T item) {
        if(this.isEmpty()) {
            this.item = item;
            return true;
        }
        if(this.item.getType().equals(item.getType()) && 
           (this.item.getAmount() + item.getAmount() <= this.item.getType().getMaxAmount())) {
            this.item.addAmount(item.getAmount());
            return true;
        }
        return false;
    }
    
    public T getItem() {
        return this.item;
    }
    
    public T removeItem() {
        T item = this.item;
        this.item = null;
        return item;
    }
    
    public boolean isEmpty() {
        return item == null;
    }

    @Override
    public void render(Graphics g) {
        g.pushTransform();
            g.translate(position.x, position.y);
            this.drawSlot(g, 0, 0, width, height, 0, 1);
            g.translate((width/2), (height/2));
            if(item != null) item.render(g);
        g.popTransform();        
    }
    
    protected void drawRoundRect(Graphics g, float x, float y, float width, float height, int cornerRadius, int thicknes) {
        while(--thicknes >= 0) {
            g.drawRoundRect(x+thicknes, y+thicknes, width-(thicknes*2), height-(thicknes*2), cornerRadius);
        }
    }
    
    protected void drawSlot(Graphics g, float x, float y, float width, float height, int cornerRadius, int border) {        
        g.setColor(bright);
        drawRoundRect(g, x, y, width, height, 0, border);
        
        g.setColor(shadow);
        drawRoundRect(g, x, y, width-border, height-border, 0, border);
        
        g.setColor(fill);
        g.fillRoundRect(x+border, y+border, width+1-(2*border), height+1-(2*border), 0);
    }

    public void setItem(T item) {
        this.item = item;
    }
}
