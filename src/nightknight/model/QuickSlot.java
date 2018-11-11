package nightknight.model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 *
 * @author Vitor
 */
public class QuickSlot extends Slot {
    private final int shortcutCode;
    private final String shortcut;
    private boolean selected;
    
    public QuickSlot(float x, float y, float width, float height, int shortcut) {
        super(x, y, width, height);
        shortcutCode = shortcut;
        this.shortcut = Input.getKeyName(shortcut);
        this.selected = false;
    }
    
    @Override
    protected void drawSlot(Graphics g, float x, float y, float width, float height, int cornerRadius, int border) {        
        
        if(selected) {
            g.setColor(bright);
            drawRoundRect(g, x-2, y-2, width+2, height+2, 0, border+2);
        }
        
        g.setColor(bright);
        drawRoundRect(g, x, y, width, height, 0, border);
        
        g.setColor(shadow);
        drawRoundRect(g, x, y, width-border, height-border, 0, border);
        
        g.setColor(fill);
        g.fillRoundRect(x+border, y+border, width+1-(2*border), height+1-(2*border), 0);
        
        drawShortcut(g);
    }
    
    public void render(Graphics g, boolean opaque) {
        if(opaque) {
            fill.a = 0.5f;
            g.pushTransform();
                g.translate(0, -position.y);
                render(g);
            g.popTransform();
        }
        else {
            fill.a = 1.0f;
            render(g);
        }
    }
    
    protected void drawShortcut(Graphics g) {
        g.setColor(Color.black);
        g.drawString(shortcut, 0, 0);
    }

    public String getShortcut() {
        return shortcut;
    }

    public int getShortcutCode() {
        return shortcutCode;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
