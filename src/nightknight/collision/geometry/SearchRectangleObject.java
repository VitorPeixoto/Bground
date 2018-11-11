package nightknight.collision.geometry;

import nightknight.collision.RectangleObject;


public class SearchRectangleObject extends RectangleObject {
    public SearchRectangleObject(){
        super(0.0f, 0.0f, 0.0f, 0.0f, "testId");
    }

    public SearchRectangleObject(float x, float y, float w, float h){
        super(x, y, w, h, "testId");
    }

    public SearchRectangleObject(float x, float y, float w, float h, String id){
        super(x, y, w, h, id);
    }
}
