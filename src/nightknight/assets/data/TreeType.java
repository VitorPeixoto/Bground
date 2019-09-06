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
        this.leafType = BlockType.OAK_LEAVES;
        this.trunkType = BlockType.OAK_WOOD;
    }

    private TreeType(int minSize, int maxSize, BlockType trunkType, BlockType leafType) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.trunkType = trunkType;
        this.leafType  = leafType;
    }
    
    public Tree generateTree(int x) {
        ArrayList<Block> trunk = new ArrayList<>();
        
        int topY = BlockGenerator.getSurface() + minSize + (int)(Math.random()*((maxSize+1)-minSize));
        
        for(int height = topY; height > BlockGenerator.getSurface(); height--)
            trunk.add(new Block(x, height, this.trunkType, true));        
        
        return new Tree(trunk, generateLeaves(x, topY), this);
    }
    
    private ArrayList<Block> generateLeaves(int x, int topY) {
        ArrayList<Block> leaves = new ArrayList<>();
        switch(this) {
            case ACACIA_TREE:
                break;
            case BIRCH_TREE:
                break;
            case DARK_OAK_TREE:
                break;
            case JUNGLE_TREE:
                break;
            case OAK_TREE:
                leaves.add(new Block(x-1, topY, this.leafType, true));
                leaves.add(new Block(x+1, topY, this.leafType, true));
                leaves.add(new Block(x+1, topY-1, this.leafType, true));
                leaves.add(new Block(x-1, topY-1, this.leafType, true));
                leaves.add(new Block(x+2, topY-1, this.leafType, true));
                leaves.add(new Block(x-2, topY-1, this.leafType, true));
                leaves.add(new Block(x, topY+1, this.leafType, true));
                break;
            case SPRUCE_TREE:
                break;
        }
        return leaves;
    }

    private final int minSize, maxSize;
    private final BlockType trunkType, leafType;
}
