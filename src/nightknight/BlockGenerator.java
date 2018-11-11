package nightknight;

import java.util.Random;
import nightknight.assets.data.BlockType;
import nightknight.constants.Sizes;

/**
 *
 * @author Vitor
 */
public class BlockGenerator {
    private static Random random = new Random();
    
    
    public static BlockType generateBlock(int y) {
        if(y > Sizes.MAP_HEIGHT-2) return BlockType.AIR;
        else if(y == getSurface()) return BlockType.GRASS;
        else if(y > Sizes.MAP_HEIGHT-8) return BlockType.DIRT;
        else if(y == 1) return BlockType.BEDROCK;
        else if(y < (Sizes.MAP_HEIGHT*0.1)) return generateDeepestBlocks();
        else if(y < (Sizes.MAP_HEIGHT*0.5)) return generateDeepBlocks();        
        else return generateSurfaceBlocks();
    }

    private static BlockType generateSurfaceBlocks() {
        int r = random.nextInt(100);
        if(r < 50) return BlockType.DIRT;
        else return BlockType.STONE;
    }

    private static BlockType generateDeepBlocks() {
        int r = random.nextInt(100);
        
        if (r >=0 && r <= 2) return BlockType.COAL_ORE;
        else if (r >=3 && r <= 5) return BlockType.IRON_ORE;
        else if (r >=6 && r <= 8) return BlockType.LAPIS_ORE;
        else if (r >=9 && r <= 12) return BlockType.REDSTONE_ORE;
        else return BlockType.STONE;
    }

    private static BlockType generateDeepestBlocks() {
        int r = random.nextInt(100);
        
        if (r >=0 && r <= 2) return BlockType.COAL_ORE;
        else if (r >=3 && r <= 5) return BlockType.IRON_ORE;
        else if (r >=6 && r <= 8) return BlockType.LAPIS_ORE;
        else if (r >=9 && r <= 12) return BlockType.REDSTONE_ORE;
        else if (r == 13) return BlockType.DIAMOND_ORE;
        else if (r == 14) return BlockType.GOLD_ORE;
        else return BlockType.STONE;
    }
    
    public static int getSurface() {
        return Sizes.MAP_HEIGHT-2;
    }
}
