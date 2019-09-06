package nightknight.model;

import java.util.List;
import nightknight.DropPool;
import nightknight.InventoryController;
import nightknight.assets.data.BlockType;
import nightknight.assets.images.ImageAssets;
import nightknight.collision.CollisionController;
import nightknight.collision.RectangleObject;
import nightknight.collision.Vector2f;
import nightknight.constants.Constants;
import nightknight.constants.Sizes;
import nightknight.interfaces.Changeable;
import nightknight.interfaces.KeyboardListener;
import nightknight.interfaces.Renderable;
import nightknight.interfaces.Shiftable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

/**
 *
 * @author Vitor
 */
public class Player extends RectangleObject implements KeyboardListener, Renderable, Changeable, Shiftable {
    private Vector2f movement;
    private double miningPower = 20;
    private Image image;
    private CollisionController tree = CollisionController.getInstance();

    public Player(int x, int y) {
        super(x, y, 1, 1, Sizes.TILE_SIZE);
        image = ImageAssets.getImage("steve.png");
        movement = new Vector2f(0, 0);
    }
    
    @Override
    public void atualiza() {
        if(KEYBOARD == null) return;
        if(KEYBOARD.getOrDefault(Input.KEY_W, false)) {
            movement.y = Constants.PLAYER_SPEED;
        }
        if(KEYBOARD.getOrDefault(Input.KEY_A, false)) {
            movement.x = -Constants.PLAYER_SPEED;
        }
        if(KEYBOARD.getOrDefault(Input.KEY_S, false)) {
            movement.y = -Constants.PLAYER_SPEED;
        }
        if(KEYBOARD.getOrDefault(Input.KEY_D, false)) {
            movement.x = Constants.PLAYER_SPEED;
        }
        if(handleCollision()) {
            position.add(movement);            
            //if(position.x >= 0 && position.x <= Sizes.MAP_LENGTH)
                Shiftable.shift.width  = (int) position.x;
            //if(position.y >= 0 && position.x <= Sizes.MAP_HEIGHT)            
                Shiftable.shift.height = (int) position.y;
        }
        movement.scale(0);
    }
    
    public RectangleObject getSight() {
        Vector2f position = this.position.subtracted(Sizes.PLAYER_SIGHT);
        return new RectangleObject((int)position.x, (int)position.y, (1 + Sizes.PLAYER_SIGHT*2), (1 + Sizes.PLAYER_SIGHT*2), Sizes.TILE_SIZE);
    }
    
    public boolean handleCollision() {
        boolean canMove = true;
        List<RectangleObject> collisions = tree.collisions(this);
        for(RectangleObject object : collisions) {
            if(object instanceof Block) {
                Block t = (Block)object;
                if(t.getBlockType().equals(BlockType.BEDROCK)) {
                    canMove = false;
                }
                else if(!t.getBlockType().equals(BlockType.AIR)) {
                    canMove = t.mine(miningPower);
                    if(canMove) tree.remove(t);
                }
            }
            else if(object instanceof Drop) {
                Drop d = (Drop)object;
                InventoryController.getInstance().pushItem(d.getItem());
                DropPool.getInstance().removeDrop(d);
            }
        }
        
        collisions = tree.collisions(this.getSight());
        for(RectangleObject object : collisions) {
            if(object instanceof Block) {
                Block t = (Block)object;
                t.setSighted(true);
            }
        }
        
        /*if(position.added(movement).x < 0 || position.added(movement).x > Sizes.MAP_LENGTH) canMove = false;
        if(Sizes.MAP_HEIGHT - position.added(movement).y < 0 || position.added(movement).y > Sizes.MAP_HEIGHT) canMove = false;*/
        return canMove;
    }
    
    @Override
    public void render(Graphics g) {
        g.pushTransform();
        g.scale(scale, scale);
            image.draw(-0.5f, 0.5f, 1, -1);
        g.popTransform();
    }

    @Override
    public float getX() {
        return ((int)this.position.added(movement).x)*scale;
    }

    @Override
    public float getY() {
        return ((int)this.position.added(movement).y)*scale;
    }
}
