package nightknight.model;

import nightknight.collision.RectangleObject;
import nightknight.interfaces.Renderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Vitor
 */
public class Slot extends RectangleObject implements Renderable {
    protected Item item;
    
    protected Color shadow, bright, fill;
    
    public Slot(float x, float y, float width, float height) {
        super(x, y, width, height);
        item = null;
        shadow = new Color(Color.black);
        bright = new Color(Color.white);
        fill   = new Color(Color.gray);
    }
    
    public Item pushItem(Item item, boolean click) {
        if(this.isEmpty()) {
            this.item = item;
            //return true;
            return item;
        }
        if(this.item.getType().equals(item.getType()) && 
           (this.item.getAmount() + item.getAmount() <= this.item.getType().getMaxAmount())) {
            this.item.addAmount(item.getAmount());
            //return true;
            return item;
        }
        //Troca o item selecionado pelo atual (somente no clique do jogador)
        if(click) {
            Item aux = this.item;
            this.item = item;
            return aux;
        }
        return null;
    }
    
    public Item getItem() {
        return this.item;
    }
    
    public Item removeItem() {
        Item item = this.item;
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

    public void setItem(Item item) {
        this.item = item;
    }
}
