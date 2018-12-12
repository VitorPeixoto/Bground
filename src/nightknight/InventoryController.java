package nightknight;

import java.util.ArrayList;
import nightknight.collision.Rectangle;
import nightknight.constants.Shortcuts;
import nightknight.constants.Sizes;
import nightknight.eventos.MouseEvent;
import nightknight.interfaces.Changeable;
import nightknight.interfaces.KeyboardListener;
import nightknight.interfaces.MouseListener;
import nightknight.interfaces.Renderable;
import nightknight.model.Item;
import nightknight.model.QuickSlot;
import nightknight.model.Slot;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

/**
 *
 * @author Vitor
 */
public class InventoryController extends Rectangle implements Renderable, Changeable, KeyboardListener {
    private boolean hidden;
    private final int borderThickness = 3, shadowThickness = 5, gap = 2;
    private ArrayList<Slot> items;
    private ArrayList<QuickSlot> hotbar;
    
    private Item selectedItem;
    private Point location;
    
    private InventoryController() {
        this.hidden = true;        
        
        /* Total - hotbar */
        int normalSlotAmount = Sizes.INVENTORY_SLOT_AMOUNT-Sizes.INVENTORY_SLOT_COLUMN;
        items = new ArrayList<>(normalSlotAmount);
        hotbar = new ArrayList<>(Sizes.INVENTORY_SLOT_COLUMN);
        
        int rows = (normalSlotAmount/ Sizes.INVENTORY_SLOT_COLUMN);
        
        width = ((Sizes.INVENTORY_SLOT_COLUMN*(Sizes.INVENTORY_SLOT_SIZE+Sizes.INVENTORY_SLOT_GAP) + (Sizes.INVENTORY_MARGIN_X*2)));
        
        /* 2: 1 hotbar + 1 margin */
        height = ((rows+2)*(Sizes.INVENTORY_SLOT_SIZE+Sizes.INVENTORY_SLOT_GAP)+(Sizes.INVENTORY_MARGIN_Y*2));
        
        position.x = (Sizes.SCREEN_WIDTH/2) - (width/2);
        position.y = (Sizes.SCREEN_HEIGHT/2) - (height/2);
        
        float x = this.position.x+Sizes.INVENTORY_MARGIN_X;
        float y = this.position.y+Sizes.INVENTORY_MARGIN_Y;
        
        for(int row = 0; row < (rows+1); row++) {
            for(int col = 0; col < Sizes.INVENTORY_SLOT_COLUMN; col++) {
                if(row < rows)
                    items.add(new Slot(x+(col*(Sizes.INVENTORY_SLOT_SIZE+gap)), y+(row*(Sizes.INVENTORY_SLOT_SIZE+gap)), Sizes.INVENTORY_SLOT_SIZE, Sizes.INVENTORY_SLOT_SIZE));
                else
                    hotbar.add(new QuickSlot(x+(col*(Sizes.INVENTORY_SLOT_SIZE+gap)), y+((row+1)*(Sizes.INVENTORY_SLOT_SIZE+gap)), Sizes.INVENTORY_SLOT_SIZE, Sizes.INVENTORY_SLOT_SIZE, Shortcuts.TOOLBAR_SHORTCUTS[col]));
            }
        }
    }
    
    @Override
    public void atualiza() {        
        hotbar.forEach((QuickSlot qs) -> {            
            if(KEYBOARD.getOrDefault(qs.getShortcutCode(), false)) {
                hotbar.forEach((QuickSlot qs2) -> (qs2.setSelected(false)));
                qs.setSelected(true);
            }
        });
        if(MouseListener.pressEvents.size() > 0) {
            MouseEvent event = MouseListener.pressEvents.poll();
            items.forEach((Slot s) -> {
                handleSlotClick(s, event);
            });
            hotbar.forEach((Slot s) -> {
                handleSlotClick(s, event);
            });
        }
        if(selectedItem != null && MouseListener.moveEvents.size() > 0) {
            location = MouseListener.moveEvents.poll();
        }
    }
    
    public void handleSlotClick(Slot s, MouseEvent event) {
        if(!s.contains(event.getLocation())) return;
        
        if(event.getButton() == MouseEvent.LEFT_BUTTON) {
            if(selectedItem == null) {
                selectedItem = s.getItem();
                s.setItem(null);
            }
            else if(s.pushItem(selectedItem)) {
                selectedItem = null;
            } else {
                Item aux = selectedItem;
                selectedItem = s.getItem();
                s.setItem(aux);
            }
        }
        else if(event.getButton() == MouseEvent.RIGHT_BUTTON) {
            // Pega a metade
            if(selectedItem == null) {
                Item m = s.getItem();
                if(m == null) return;
                double half = m.getAmount()/2.0;
                m.setAmount((int) Math.floor(half));
                selectedItem = m.copy();
                if(m.getAmount() <= 0)
                    s.setItem(null);
                selectedItem.setAmount((int)Math.ceil(half));
            }
            else {
                Item m = selectedItem.copy();
                m.setAmount(1);
                // Incrementa em 1
                if(s.pushItem(m)) {
                    selectedItem.setAmount(selectedItem.getAmount()-1);
                    if(selectedItem.getAmount() <= 0)
                        selectedItem = null;
                }
            }
        }
    }
    
    @Override
    public void render(Graphics g) {
        //g.translate(Sizes.SCREEN_WIDTH/2, Sizes.SCREEN_HEIGHT/2);
        if(!this.hidden)
            renderInventory(g);
        renderHotbar(g);
        if(selectedItem != null) renderSelectedItem(g);
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    
    public void toggleHidden() {
        this.hidden = !this.hidden;
    }
    
    public void pushItem(Item item) {
        for(int i = 0; i < hotbar.size(); i++) {
            if(hotbar.get(i).pushItem(item))
                return;
        }
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).pushItem(item))
                return;
        }
    }
    
    public void renderSelectedItem(Graphics g) {
        g.pushTransform();
            g.translate(location.getX(), location.getY());
            selectedItem.render(g);
        g.popTransform();
    }
    
    public void renderHotbar(Graphics g) {
        g.pushTransform();
            g.translate(0, Sizes.SCREEN_HEIGHT-Sizes.INVENTORY_SLOT_SIZE);
            hotbar.forEach((QuickSlot qs) -> qs.render(g, true));
        g.popTransform();
    }
    
    public void renderInventory(Graphics g) {
        g.setColor(Color.black);
        drawRoundRect(g, position.x, position.y, width, height, 2, borderThickness);
        
        g.setColor(Color.gray);
        drawRoundRect(g, position.x+borderThickness, position.y+borderThickness, width-(2*borderThickness), height-(2*borderThickness), 2, shadowThickness);
        
        g.setColor(Color.white);
        drawRoundRect(g, position.x+borderThickness, position.y+borderThickness, width-(2*borderThickness)-shadowThickness, height-(2*borderThickness)-shadowThickness, 2, shadowThickness);
        
        g.setColor(Color.lightGray);
        g.fillRoundRect(position.x+(borderThickness+shadowThickness), position.y+(borderThickness+shadowThickness), width+1-(2*(borderThickness+shadowThickness)), height+1-(2*(borderThickness+shadowThickness)), 2);
        
        g.setColor(Color.black);
        g.drawString("Inventory", position.x+Sizes.INVENTORY_MARGIN_X, position.y+(Sizes.INVENTORY_MARGIN_Y/2));
        
        items.forEach((Slot s) -> s.render(g));
        hotbar.forEach((QuickSlot s) -> s.render(g, false));
    }
    
    public void drawRoundRect(Graphics g, float x, float y, float width, float height, int cornerRadius, int thicknes) {
        while(--thicknes >= 0) {
            g.drawRoundRect(x+thicknes, y+thicknes, width-(thicknes*2), height-(thicknes*2), cornerRadius);
        }
    }
    
    public void drawSlot(Graphics g, float x, float y, float width, float height, int cornerRadius, int border) {        
        g.setColor(Color.white);
        drawRoundRect(g, x, y, width, height, 0, border);
        
        g.setColor(Color.black);
        drawRoundRect(g, x, y, width-border, height-border, 0, border);
        
        g.setColor(Color.gray);
        g.fillRoundRect(x+border, y+border, width+1-(2*border), height+1-(2*border), 0);
    }
    
    public static InventoryController getInstance() {
        return InventoryControllerHolder.INSTANCE;
    }
    
    private static class InventoryControllerHolder {
        private static final InventoryController INSTANCE = new InventoryController();
    }
}
