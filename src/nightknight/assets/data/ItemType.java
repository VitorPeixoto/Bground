package nightknight.assets.data;

import nightknight.assets.images.ImageAssets;
import org.newdawn.slick.Image;

/**
 *
 * @author Vitor
 */
public enum ItemType {
    STONE("stone", "Stone", "stone.png", true, 64),
    GRASS("grass_block", "grass_side.png", "Grass", true, 64),
    DIRT("dirt", "Dirt", "dirt.png", true, 64),
    COBBLESTONE("cobblestone", "Cobblestone", "stonebrick", true, 64),
    GOLD_ORE("gold_ore", "Gold Ore", "oreGold.png", true, 64),
    IRON_ORE("iron_ore", "Iron Ore", "oreIron.png", true, 64),
    COAL_ORE("coal_ore", "Coal Ore", "oreCoal.png", true, 64),
    
    COAL("coal", "Coal", "coal.png", true, 64),
    DIAMOND("diamond", "Diamond", "diamond.png", true, 64),
    IRON_INGOT("iron_ingot", "Iron Ingot", "ingotIron.png", true, 64),
    GOLD_INGOT("gold_ingot", "Gold Ingot", "ingotGold.png", true, 64),
    REDSTONE("redstone", "Redstone Dust", "redstone.png", true, 64),
    LAZULI("lapis_lazuli", "Lapis Lazuli", "dyePowder_blue.png", true, 64),;
    
    private ItemType(String id, String name, String imageName, boolean stackable, int maxAmount) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.stackable = stackable;
        this.maxAmount = maxAmount;
    }

    public String getId() {
        return id;
    }

    public boolean isStackable() {
        return stackable;
    }

    public int getMaxAmount() {
        return maxAmount;
    }
    
    public Image getImage() {
        return ImageAssets.getImage(folder, this.imageName);
    }
    
    private final String id;
    private final String name;
    private final String imageName;
    private final boolean stackable;
    private final int maxAmount;
    private static final String folder = "items/";
}
