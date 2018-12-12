package nightknight.model;

import nightknight.BlockGenerator;
import nightknight.assets.data.BlockType;
import nightknight.collision.CollisionController;
import nightknight.constants.Debug;
import nightknight.constants.Sizes;
import nightknight.interfaces.Changeable;
import nightknight.interfaces.Renderable;
import nightknight.interfaces.Shiftable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Chunk implements Renderable, Changeable, Shiftable {
    private int x, y;
    private int firstColumn, lastColumn, firstRow, lastRow;
    private Block blocks[][] = new Block[Sizes.CHUNK_SIZE][Sizes.CHUNK_SIZE];
    
    public Chunk(int x, int y) {
        this.x = x;
        this.y = y;
        firstColumn = firstRow = 0;
        lastColumn = lastRow = Sizes.CHUNK_SIZE;
    }

    public void load() {
        int blockInitialX = (x*Sizes.CHUNK_SIZE);
        int blockInitialY = (y*Sizes.CHUNK_SIZE);
        
        for(int linha = 0; linha < Sizes.CHUNK_SIZE; linha++) {
            for(int coluna = 0; coluna < Sizes.CHUNK_SIZE; coluna++) {
                Block block = new Block(blockInitialX+coluna, blockInitialY+linha, BlockGenerator.generateBlock(blockInitialY+linha));
                if(blockInitialY+linha == BlockGenerator.getSurface()) block.setSighted(true);
                if(block.getBlockType() != BlockType.AIR) {
                    CollisionController.getInstance().addRectangleObject(block);
                }
                blocks[linha][coluna] = block;
            }
        }
    }
    
    public void unload() {
        for(int linha = 0; linha < Sizes.CHUNK_SIZE; linha++) {
            for(int coluna = 0; coluna < Sizes.CHUNK_SIZE; coluna++) {
                CollisionController.getInstance().remove(blocks[linha][coluna]);
            }
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.pushTransform();
            for(int linha = firstRow; linha < lastRow; linha++) {
                for(int coluna = firstColumn; coluna < lastColumn; coluna++) {
                    blocks[linha][coluna].render(g);
                }
            }
            if(Debug.DEBUG) debug(g);
        g.popTransform();
    }
    
    public void debug(Graphics g) {
        float x = blocks[0][0].getX()-(Sizes.TILE_SIZE/2);
        float y = blocks[0][0].getY()-(Sizes.TILE_SIZE/2);
        g.setColor(Color.yellow);
        
        g.drawRect(x+1, y+1, Sizes.CHUNK_SIZE*Sizes.TILE_SIZE-2, Sizes.CHUNK_SIZE*Sizes.TILE_SIZE-2);
    }
    
    public String getKey() {
        return ""+x+y;
    }

    @Override
    public void atualiza() {
        firstColumn = (shift.width - (x*Sizes.CHUNK_SIZE))-(Sizes.COLUMNS_PER_SCREEN/2);
        lastColumn  = (Sizes.COLUMNS_PER_SCREEN)+firstColumn+1;
        firstColumn = (firstColumn < 0 ? 0 : firstColumn);
        lastColumn  = (lastColumn > Sizes.CHUNK_SIZE ? Sizes.CHUNK_SIZE : lastColumn);

        firstRow = (shift.height - (y*Sizes.CHUNK_SIZE))-(Sizes.LINES_PER_SCREEN/2)-1;
        lastRow  = (Sizes.LINES_PER_SCREEN)+firstRow+1;
        firstRow = (firstRow < 0 ? 0 : firstRow);
        lastRow  = (lastRow > Sizes.CHUNK_SIZE ? Sizes.CHUNK_SIZE : lastRow);
    }
}
