package nightknight.model.items;

import nightknight.assets.data.ItemType;
import nightknight.model.Item;

/**
 *
 * @author Vitor
 */
public class FueledBlockItem extends Item implements BlockItem, FuelItem {
    
    public FueledBlockItem(ItemType type, int amount) {
        super(type, amount);
    }
    
}
