package nightknight.collision;

import org.newdawn.slick.geom.Point;

/**
 *
 * @author Vitor
 */
public class Rectangle {
    protected Vector2f position;
    protected float width, height;
    protected float scale;
    
    public Rectangle(float x, float y, float width, float height) {
        position = new Vector2f(x, y);
        this.width = width;
        this.height = height;
        this.scale = 1.0f;
    }
    
    public Rectangle(float x, float y, float width, float height, float scale) {
        position = new Vector2f(x, y);
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    public Rectangle() {
        position = new Vector2f();
        scale = 1.0f;
    }
    
    public float getX() {
        return position.x*scale;
    }

    public void setX(float x) {
        this.position.x = x;
    }

    public float getY() {
        return position.y*scale;
    }

    public void setY(float y) {
        this.position.y = y;
    }

    public float getWidth() {
        return width*scale;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height*scale;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setLocation(float x, float y) {
        this.setX(x);
        this.setY(y);
    }
    
    public void setLocation(Point p) {
        this.setX(p.getX());
        this.setY(p.getY());
    }
    
    public void setDimension(float width, float height) {
        this.setWidth(width);
        this.setHeight(height);
    }
    
    public void setBounds(float x, float y, float width, float height) {
        this.setLocation(x, y);
        this.setDimension(width, height);
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
    
    public boolean contains(Point p) {
        return (p.getX() >= getX() && p.getX() <= getX()+getWidth()) && (p.getY() >= getY() && p.getY() <= getY()+getHeight());
    }
    
    public boolean inside(Point p) {
        return (p.getX() > getX() && p.getX() < getX()+getWidth()) && (p.getY() > getY() && p.getY() < getY()+getHeight());
    }
    
    public boolean overlap(Rectangle rectangleObject) {
        if(this.getX() >= rectangleObject.getX() + rectangleObject.getWidth()|| rectangleObject.getX() >= this.getX() + this.getWidth()){
            return false;
        }
        if(this.getY() >= rectangleObject.getY() + rectangleObject.getHeight()|| rectangleObject.getY() >= this.getY() + this.getHeight()){
           return false;
        }

        return true;
    }
}
