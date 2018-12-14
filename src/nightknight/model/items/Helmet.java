package nightknight.model.items;

import nightknight.assets.data.ItemType;
import nightknight.model.Item;

/**
 *
 * @author peixoto
 */
public class Helmet extends Item implements ArmorItem {
    
    public Helmet(ItemType type, int amount) {
        super(type, amount);
    }
    
}
