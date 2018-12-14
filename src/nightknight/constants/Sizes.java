package nightknight.constants;

/**
 *
 * @author Vitor
 */
public final class Sizes {
    public static int SCREEN_WIDTH = 1920;
    public static int SCREEN_HEIGHT = 1080;    
    
    public static final int TILE_SIZE = 64;
    public static final int MAP_LENGTH = 100;
    public static final int MAP_HEIGHT = 60;
    
    public static int LINES_PER_SCREEN = SCREEN_HEIGHT/TILE_SIZE;
    public static int COLUMNS_PER_SCREEN = SCREEN_WIDTH/TILE_SIZE;    
    
    public static final int INVENTORY_SLOT_AMOUNT = 40;
    public static final int INVENTORY_SLOT_COLUMN = 10;
    public static final int INVENTORY_SLOT_SIZE = 64;
    public static final int INVENTORY_SLOT_GAP = 2;
    public static final int INVENTORY_SLOT_ITEM_SIZE = 30;
    public static final int INVENTORY_MARGIN_X = 20;
    public static final int INVENTORY_MARGIN_Y = 30;
    
    public static final int PLAYER_SIGHT = 1;
    
    public static final int CHUNK_SIZE = 15;
    public static final int CHUNK_RADIUS = 1;
}
