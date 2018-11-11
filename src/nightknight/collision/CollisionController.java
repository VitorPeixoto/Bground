package nightknight.collision;

/**
 *
 * @author Vitor
 */
public class CollisionController extends QuadTree {
    
    private CollisionController() {}
    
    public static CollisionController getInstance() {
        return CollisionControllerHolder.INSTANCE;
    }
    
    private static class CollisionControllerHolder {

        private static final CollisionController INSTANCE = new CollisionController();
    }
}
