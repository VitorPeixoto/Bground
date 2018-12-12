package nightknight;

import java.util.ArrayList;
import java.util.HashMap;
import nightknight.constants.Sizes;
import nightknight.interfaces.Changeable;
import nightknight.interfaces.KeyboardListener;
import nightknight.interfaces.Renderable;
import nightknight.interfaces.Shiftable;
import static nightknight.interfaces.Shiftable.shift;
import nightknight.model.Block;
import nightknight.model.Chunk;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Vitor
 */
public class Map implements Renderable, Changeable, KeyboardListener, Shiftable {
    private float maxlinha, maxcoluna;
    private ArrayList<ArrayList<Block>> tiles;
    
    private HashMap<String, Chunk> chunks;
    
    private Map(int maxlinha, int maxcoluna/* Map */) {
        tiles = new ArrayList<>();
        
        //chunks = new HashMap<>();
        chunks = new HashMap<>(9);
        this.maxlinha = maxlinha;
        this.maxcoluna = maxcoluna;
        
        /*ArrayList<Block> tileLine;
        for(int linha = Sizes.MAP_HEIGHT; linha > 0; linha--) {
            tileLine = new ArrayList<>();
            Block tile;
            for(int coluna = 0; coluna < Sizes.MAP_LENGTH; coluna++) {                
                tile = new Block(coluna, linha, BlockGenerator.generateBlock(linha));
                if(linha == BlockGenerator.getSurface()) tile.setSighted(true);
                tileLine.add(tile);
                if(tile.getBlockType() != BlockType.AIR) {
                    CollisionController.getInstance().addRectangleObject(tile);
                }
            }
            tiles.add(tileLine);
        }*/
        
    }

    public ArrayList<ArrayList<Block>> getTiles() {
        return tiles;
    }
    
    public void removeTile(Block t) {
        for(int i = 0; i < tiles.size(); i++)
            tiles.get(i).remove(t);
    }
    
    @Override
    public void render(Graphics g) {
        g.pushTransform();
            /*int fline = (int) (Sizes.MAP_HEIGHT - shift.height - (maxlinha/2));
            int fcol  = (int) (shift.width - (maxcoluna/2));
            
            fcol = (fcol < 0 ? 0 : fcol);
            fcol = (fcol > tiles.get(0).size() ? tiles.get(0).size() : fcol);
            
            fline = (fline < 0 ? 0 : fline);
            fline = (fline > tiles.size() ? tiles.size() : fline);
            
            g.translate(-(float)shift.getWidth()*Sizes.TILE_SIZE, -(Sizes.MAP_HEIGHT-(float)shift.getHeight())*Sizes.TILE_SIZE);
            for(int l = fline; l < tiles.size(); l++) {
                for(int c = fcol; c < tiles.get(0).size(); c++) {
                    tiles.get(l).get(c).render(g);
                }
            }*/
            g.translate(-(float)shift.width*Sizes.TILE_SIZE, -(/*Sizes.MAP_HEIGHT*/(float)shift.height)*Sizes.TILE_SIZE);            
            chunks.forEach((String key, Chunk c) -> c.render(g));
        g.popTransform();
    }

    @Override
    public void atualiza() {
        loadChunks();
        chunks.forEach((String s, Chunk c) -> (c.atualiza()));
    }
    
    private void loadChunks() {
        int x;
        if(shift.width >= 0)
            x = shift.width/(Sizes.CHUNK_SIZE);
        else
            x = (shift.width-Sizes.CHUNK_SIZE+1)/(Sizes.CHUNK_SIZE);
        
        int y = shift.height/(Sizes.CHUNK_SIZE);
        for(int i = x-Sizes.CHUNK_RADIUS; i <= x+Sizes.CHUNK_RADIUS; i++) {
            for(int j = y-Sizes.CHUNK_RADIUS; j <= y+Sizes.CHUNK_RADIUS; j++) {
                loadChunk(i, j);
            }
        }
    }
        
    private void loadChunk(int x, int y) {
        y = (y < 0 ? 0 : y);
        String key = ""+x+y;
        if(!chunks.containsKey(key)) {
            Chunk c = new Chunk(x, y);
            c.load();
            System.out.println("Loaded chunk "+key);
            System.out.println("Chunks loaded: "+chunks.size());
            chunks.put(key, c);
        }
    }

    
    public static Map getInstance() {
        return MapHolder.INSTANCE;
    }
    
    private static class MapHolder {
        private static final Map INSTANCE = new Map(17, 30);
    }
}
