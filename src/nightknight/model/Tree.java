package nightknight.model;

import java.util.ArrayList;
import nightknight.assets.data.TreeType;
import nightknight.interfaces.Renderable;
import org.newdawn.slick.Graphics;

/**
 *
 * @author peixoto
 */
public class Tree implements Renderable {
    private ArrayList<Block> trunk;
    private ArrayList<Block> leaves;
    private TreeType type;

    public Tree(ArrayList<Block> trunk, ArrayList<Block> leaves, TreeType type) {
        this.trunk = trunk;
        this.leaves = leaves;
        this.type = type;
    }

    @Override
    public void render(Graphics g) {
        trunk.forEach((Renderable r) -> (r.render(g)));
        leaves.forEach((Renderable r) -> (r.render(g)));
    }

}
