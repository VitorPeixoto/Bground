package nightknight.assets.data;

/**
 *
 * @author Vitor
 */
public enum ItemCategory {
    BLOCK(),
    ARMOR(),
        HELMET(ARMOR),
        CHESTPLATE(ARMOR),
        PANTS(ARMOR),
        BOOTS(ARMOR),
    ;
    
    private ItemCategory() {
        this.parentCategory = null;
    }
    
    private ItemCategory(ItemCategory parentCategory) {
        this.parentCategory = parentCategory;
    }
    
    private final ItemCategory parentCategory;
}
