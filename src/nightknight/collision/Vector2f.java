package nightknight.collision;

/**
 *
 * @author Vitor
 */
public class Vector2f {
    public float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f() {}
    
    public float dotProduct(Vector2f axis) { return (x * axis.x) + (y * axis.y); }
    public float getNorm() { return (float) Math.sqrt(x * x + y * y); }

    public static Vector2f normalized(Vector2f v) { return v.divided(v.getNorm()); }

    public Vector2f tripleProduct(Vector2f v1, Vector2f v2) { return (v1.scaled(this.dotProduct(v2)).subtracted(this.scaled(v1.dotProduct(v2)))); }

    public boolean equals(Vector2f v) {
        return (x == v.x && y == v.y);
    }

    public Vector2f added(Vector2f v) {
        return new Vector2f(x+v.x, y+v.y);
    }
    
    public Vector2f subtracted(Vector2f v) {
        return new Vector2f(x-v.x, y-v.y);
    }
    
    public Vector2f subtracted(float number) {
        return new Vector2f(x-number, y-number);
    }
    
    public Vector2f negated() {
        return new Vector2f(-x, -y);
    }
    
    public Vector2f scaled(float number) {
        return new Vector2f(x*number, y*number);
    }

    public Vector2f divided(float number) {
        return new Vector2f(x/number, y/number);
    }

    public void add(Vector2f v) {
        this.x += v.x;
        this.y += v.y;
    }
    
    public void subtract(Vector2f v) {
        this.x -= v.x;
        this.y -= v.y;
    }
    
    public void subtract(float number) {
        this.x -= number;
        this.y -= number;
    }
    
    public void scale(float number) {
        this.x *= number;
        this.y *= number;
    }
    
    public void divide(float number) {
        this.x /= number;
        this.y /= number;
    }

    public void multiply(Vector2f v) {
        this.x *= v.x;
        this.y *= v.y;
    }

    @Override
    public String toString() {
        return "Vector2f{" + "x=" + x + ", y=" + y + '}';
    }
}
