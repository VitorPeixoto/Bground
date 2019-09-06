package nightknight.assets.data;

import nightknight.assets.images.ImageAssets;
import nightknight.model.Drop;
import org.newdawn.slick.Image;

/**
 *
 * @author Vitor
 */
public enum BlockType {
    AIR("Air", null, 0, null),
    STONE("Stone", "stone.png", 150),
    GRASS("Grass", "grass_side.png", 100, new Drop(ItemType.DIRT)),
    DIRT("Dirt", "dirt.png", 100, new Drop(ItemType.DIRT)),
    BEDROCK("Bedrock", "bedrock.png", 0),
    GOLD_ORE("Gold Ore", "oreGold.png", 200, new Drop(ItemType.GOLD_ORE)),
    IRON_ORE("Iron Ore", "oreIron.png", 200, new Drop(ItemType.IRON_ORE)),
    COAL_ORE("Coal Ore", "oreCoal.png", 200, new Drop(ItemType.COAL)),
    LAPIS_ORE("Lapis Lazuli Ore", "oreLapis.png", 200, new Drop(ItemType.LAZULI, 4, 8, 1)),
    DIAMOND_ORE("Diamond Ore", "oreDiamond.png", 200, new Drop(ItemType.DIAMOND)),
    REDSTONE_ORE("Redstone Ore", "oreRedstone.png", 200, new Drop(ItemType.REDSTONE, 4, 5, 1)),
    
    OAK_WOOD("Oak Wood", "oak_wood.png", 200),
    OAK_LEAVES("Oak Leaves", "oak_leaves.png", 20),
    ;
    
    private BlockType(String name, String imageName, int hardness) {
        this.name = name;
        this.imageName = imageName;
        this.hardness = hardness;
        drop = new Drop(ItemType.STONE);
    }
    
    private BlockType(String name, String imageName, int hardness, Drop drop) {
        this.name = name;
        this.imageName = imageName;
        this.hardness = hardness;
        this.drop = drop;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }
    
    public Image getImage() {
        return ImageAssets.getImage(folder, this.imageName);
    }

    public int getHardness() {
        return hardness;
    }

    public Drop getDrop() {
        return drop;
    }
    
    private final String name;
    private final String imageName;
    private final Drop drop;
    private final int hardness;
    private static final String folder = "blocks/";
}
