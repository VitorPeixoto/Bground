package nightknight.assets.data;

import java.util.ArrayList;
import nightknight.BlockGenerator;
import nightknight.model.Block;
import nightknight.model.Tree;

/**
 *
 * @author peixoto
 */
public enum TreeType {
    OAK_TREE(),
    SPRUCE_TREE(),
    BIRCH_TREE(),
    JUNGLE_TREE(),
    ACACIA_TREE(),
    DARK_OAK_TREE(),
    ;

    private TreeType() {
        this.minSize = 3;
        this.maxSize = 5;
        this.leafType = BlockType.GOLD_ORE;
        this.trunkType = BlockType.STONE;
    }

    private TreeType(int minSize, int maxSize, BlockType trunkType, BlockType leafType) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.trunkType = trunkType;
        this.leafType = leafType;
    }
    
    public Tree generateTree(int x) {
        ArrayList<Block> trunk = new ArrayList<>();
        ArrayList<Block> leaves = new ArrayList<>();
        
        trunk.add(new Block(x, BlockGenerator.getSurface()+1, this.trunkType));
        trunk.add(new Block(x, BlockGenerator.getSurface()+2, this.trunkType));
        trunk.add(new Block(x, BlockGenerator.getSurface()+3, this.trunkType));
        
        leaves.add(new Block(x-1, BlockGenerator.getSurface()+3, this.leafType));
        leaves.add(new Block(x-1, BlockGenerator.getSurface()+3, this.leafType));
        leaves.add(new Block(x, BlockGenerator.getSurface()+4, this.leafType));
        
        return new Tree(trunk, leaves, this);
    }
    
    private final int minSize, maxSize;
    private final BlockType trunkType, leafType;
}
