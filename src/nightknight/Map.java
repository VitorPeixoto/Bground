package nightknight;

import java.util.ArrayList;
import javafx.stage.Screen;
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
        g.pushTransform();
            int fline = (int) (Sizes.MAP_HEIGHT - shift.height - (maxlinha/2));
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
            }
        g.popTransform();
    }

    @Override
    public void atualiza() {}
    
    public static Map getInstance() {
        return MapHolder.INSTANCE;
    }
    
    private static class MapHolder {
        private static final Map INSTANCE = new Map(17, 30);
    }
}
