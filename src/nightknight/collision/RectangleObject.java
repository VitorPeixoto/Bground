package nightknight.collision;

/**
 *
 * @author Vitor
 */
public class RectangleObject extends Rectangle {
    protected final String id;

    public RectangleObject() {
        this.id = ""+this.hashCode();
    }
    
    public RectangleObject(String id) {
        this.id = id;
    }

    public RectangleObject(float x, float y, float width, float height, String id) {
        super(x, y, width, height);
        this.id = id;
    }
    
    public RectangleObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.id = ""+this.hashCode();
    }
    
    public RectangleObject(float x, float y, float width, float height, float scale) {
        super(x, y, width, height, scale);
        this.id = ""+this.hashCode();
    }

    public String getId() {
        return id;
    }
}
