package nightknight;

import java.util.concurrent.ConcurrentLinkedQueue;
import nightknight.collision.CollisionController;
import nightknight.interfaces.Renderable;
import nightknight.model.Drop;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Vitor
 */
public class DropPool implements Renderable {
    private ConcurrentLinkedQueue<Drop> drops;
    private final int maxDrops;
    private static final CollisionController collisionController = CollisionController.getInstance();
            
    private DropPool(int maxDrops) {
        this.maxDrops = maxDrops;
        drops = new ConcurrentLinkedQueue<>();
    }
    
    public void addDrop(Drop drop) {
        if(drops.size() == maxDrops)
            collisionController.remove(drops.remove());
        drops.add(drop);
        collisionController.addRectangleObject(drop);
    }
    
    public void removeDrop(Drop d) {
        drops.remove(d);
        collisionController.remove(d);
    }
    
    public static DropPool getInstance() {
        return DropPoolHolder.INSTANCE;
    }

    @Override
    public void render(Graphics g) {
        drops.forEach((Renderable drop) -> drop.render(g));
    }
    
    private static class DropPoolHolder {
        private static final int MAX_DROPS = 10;
        private static final DropPool INSTANCE = new DropPool(MAX_DROPS);
    }
}
