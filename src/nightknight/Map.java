package nightknight;

import java.util.ArrayList;
import nightknight.assets.data.BlockType;
import nightknight.collision.CollisionController;
import nightknight.constants.Sizes;
import nightknight.interfaces.Changeable;
import nightknight.interfaces.KeyboardListener;
import static nightknight.interfaces.KeyboardListener.KEYBOARD;
import nightknight.interfaces.Renderable;
import nightknight.interfaces.Shiftable;
import static nightknight.interfaces.Shiftable.shift;
import nightknight.model.Block;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 *
 * @author Vitor
 */
public class Map implements Renderable, Changeable, KeyboardListener, Shiftable {
    private float maxlinha, maxcoluna;
    private ArrayList<ArrayList<Block>> tiles;
    
    private Map(int maxlinha, int maxcoluna/* Map */) {
        tiles = new ArrayList<>();
        
        this.maxlinha = maxlinha;
        this.maxcoluna = maxcoluna;
        
        ArrayList<Block> tileLine;
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
        }
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
        /*for(ArrayList<Tile> list : tiles)
            for(Tile t : list)
                t.render(g);*/
        
        g.translate(-(float)shift.getWidth()*Sizes.TILE_SIZE, -(float)shift.getHeight()*Sizes.TILE_SIZE);
        
        for(int l = (int)shift.getHeight(); l < shift.getHeight() + maxlinha; l++) {
            for(int c = (int)shift.getWidth(); c < shift.getWidth() + maxcoluna; c++) {
                tiles.get(l).get(c).render(g);
            }
        }
        g.translate((float)shift.getWidth()*Sizes.TILE_SIZE, (float)shift.getHeight()*Sizes.TILE_SIZE);
    }
    
    public void incrementaLinha() {
        if((shift.getHeight()+maxlinha+1) <= Sizes.MAP_HEIGHT) shift.setSize(shift.getWidth(), shift.getHeight()+1);
    }
    
    public void decrementaLinha() {
        if(shift.getHeight()-1 >= 0) shift.setSize(shift.getWidth(), shift.getHeight()-1);
    }
    
    public void incrementaColuna() {
        if(shift.getWidth()+maxcoluna+1 <= Sizes.MAP_LENGTH) shift.setSize(shift.getWidth()+1, shift.getHeight());
    }
    
    public void decrementaColuna() {
        if(shift.getWidth()-1 >= 0) shift.setSize(shift.getWidth()-1, shift.getHeight());
    }

    @Override
    public void atualiza() {
        if(KEYBOARD.containsKey(Input.KEY_DOWN) && KEYBOARD.get(Input.KEY_DOWN)) {
            this.incrementaLinha();
        }
        if(KEYBOARD.containsKey(Input.KEY_UP) && KEYBOARD.get(Input.KEY_UP)) {
            this.decrementaLinha();
        }
        if(KEYBOARD.containsKey(Input.KEY_LEFT) && KEYBOARD.get(Input.KEY_LEFT)) {
            this.decrementaColuna();
        }
        if(KEYBOARD.containsKey(Input.KEY_RIGHT) && KEYBOARD.get(Input.KEY_RIGHT)) {
            this.incrementaColuna();
        }
    }
    
    public static Map getInstance() {
        return MapHolder.INSTANCE;
    }
    
    private static class MapHolder {
        private static final Map INSTANCE = new Map(17, 30);
    }
}
