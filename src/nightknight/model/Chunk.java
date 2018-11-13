package nightknight.model;

import nightknight.BlockGenerator;
import nightknight.assets.data.BlockType;
import nightknight.collision.CollisionController;
import nightknight.constants.Sizes;
import nightknight.interfaces.Renderable;
import static nightknight.interfaces.Shiftable.shift;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Chunk implements Renderable {
    private int x, y;
    private Block blocks[][] = new Block[Sizes.CHUNK_SIZE][Sizes.CHUNK_SIZE];
    
    public Chunk(int x, int y) {
        this.x = x;
        this.y = y;
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
        int fcol = (shift.width - (x*Sizes.CHUNK_SIZE))-(Sizes.COLUMNS_PER_SCREEN/2);
        int mcol = (Sizes.COLUMNS_PER_SCREEN)+fcol;
        fcol = (fcol < 0 ? 0 : fcol);
        mcol = (mcol > Sizes.CHUNK_SIZE ? Sizes.CHUNK_SIZE : mcol);
        //Tirar logica do render
        
        int mrow = (shift.height - (y*Sizes.CHUNK_SIZE))-(Sizes.LINES_PER_SCREEN/2);
        
        if(this.x == 0 && this.y == 3) {
            System.out.println(shift.height + " " + mrow + " " + Sizes.LINES_PER_SCREEN);
        }
        mrow = (mrow < 0 ? 0 : mrow);
        g.pushTransform();
            /*int fline = (int) (Sizes.MAP_HEIGHT - shift.height - (Sizes.LINES_PER_SCREEN/2));
            int fcol  = (int) (shift.width - (Sizes.COLUMNS_PER_SCREEN/2));
            
            fcol = (fcol < 0 ? 0 : fcol);
            fcol = (fcol > blocks[0].length ? blocks[0].length : fcol);
            
            fline = (fline < 0 ? 0 : fline);
            fline = (fline > tiles.size() ? tiles.size() : fline);*/
                        
        /*int fline = (shig)
                
        int fline = (int) (Sizes.MAP_HEIGHT - shift.height - (maxlinha/2));*/
            
            //fcol = (fcol > Sizes.CHUNK_SIZE ? Sizes.CHUNK_SIZE : fcol);
            
            /*fline = (fline < 0 ? 0 : fline);
            fline = (fline > tiles.size() ? tiles.size() : fline);
            
            g.translate(-(float)shift.getWidth()*Sizes.TILE_SIZE, -(Sizes.MAP_HEIGHT-(float)shift.getHeight())*Sizes.TILE_SIZE);
            for(int l = fline; l < tiles.size(); l++) {
                for(int c = fcol; c < tiles.get(0).size(); c++) {
                    tiles.get(l).get(c).render(g);
                }
            }*/
            
            //g.translate(-(float)shift.getWidth()*Sizes.TILE_SIZE, -(/*Sizes.MAP_HEIGHT*/-(float)shift.getHeight())*Sizes.TILE_SIZE);
            /*for(int l = fline; l < tiles.size(); l++) {
                for(int c = fcol; c < tiles.get(0).size(); c++) {
                    tiles.get(l).get(c).render(g);
                }
            }*/
            for(int linha = mrow; linha < Sizes.CHUNK_SIZE; linha++) {
                for(int coluna = fcol; coluna < mcol/*Sizes.CHUNK_SIZE*/; coluna++) {
                    blocks[linha][coluna].render(g);
                }
            }
            g.setColor(Color.yellow);
            g.drawRect(blocks[0][0].getX()+1, (Sizes.MAP_HEIGHT*Sizes.TILE_SIZE)-blocks[0][0].getY()+1, Sizes.CHUNK_SIZE*Sizes.TILE_SIZE-2, Sizes.CHUNK_SIZE*Sizes.TILE_SIZE-2);
            if(this.x == 0 && this.y == 0) {
                g.setColor(Color.cyan);
                g.fillRect(blocks[0][0].getX()+1, 0/*(Sizes.MAP_HEIGHT*Sizes.TILE_SIZE)-blocks[0][0].getY()+1*/, Sizes.CHUNK_SIZE*Sizes.TILE_SIZE-2, Sizes.CHUNK_SIZE*Sizes.TILE_SIZE-2);
            }
        g.popTransform();
    }
    
    public String getKey() {
        return ""+x+y;
    }
}
