package nightknight.model;

import nightknight.DropPool;
import nightknight.assets.data.BlockType;
import nightknight.assets.images.ImageAssets;
import nightknight.collision.RectangleObject;
import nightknight.constants.Sizes;
import nightknight.interfaces.Renderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author Vitor
 */
public class Block extends RectangleObject implements Renderable {
    private BlockType type;
    private Image image;
    private boolean mined, sighted;
    private int hardness;
    
    public Block(int x, int y, BlockType type) {
        super(x, y, 1, 1, Sizes.TILE_SIZE);
        this.type = type;
        this.image = type.getImage();
        mined = sighted = false;
        hardness = type.getHardness();
    }

    public boolean isMined() {
        return mined;
    }

    public boolean mine(double miningPower) {
        hardness -= miningPower;
        if(hardness <= 0) {
            mined = true;
            Drop d = this.type.getDrop().copy();
            d.setBounds(position.x, position.y, 10, 10);
            DropPool.getInstance().addDrop(d);
        }
        return mined;
    }
    
    public void setMined(boolean mined) {
        this.mined = mined;
    }
    
    public BlockType getBlockType() {
        return this.type;
    }
    
    @Override
    public void render(Graphics g) {
        if(image == null || !sighted) return;
        
        g.pushTransform();
            g.scale(Sizes.TILE_SIZE, Sizes.TILE_SIZE);
            g.translate(position.x, (Sizes.MAP_HEIGHT-position.y));
            image.draw(0, 0, 1, 1, mined ? new Color(1, 1, 1, 0.3f) : Color.white);
            if(!mined) this.drawDamageImage();
        g.popTransform();            
    }

    public void drawDamageImage() {
        if(this.hardness == this.type.getHardness()) return;
        int damage = (int)(((float)(this.type.getHardness()-this.hardness)/(this.type.getHardness()))*10);
        ImageAssets.getImage("blocks/", "destroy_"+damage+".png").draw(0, 0, 1, 1);
    }

    public void setBlockType(BlockType blocks) {
        this.type = blocks;
    }

    public boolean isSighted() {
        return sighted;
    }

    public void setSighted(boolean sighted) {
        this.sighted = sighted;
    }
    
}
