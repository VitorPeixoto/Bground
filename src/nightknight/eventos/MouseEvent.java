package nightknight.eventos;

import org.newdawn.slick.geom.Point;

/**
 *
 * @author Vitor
 */
public class MouseEvent {
    public static final int LEFT_BUTTON = 0;
    public static final int RIGHT_BUTTON = 1;
    public static final int MIDDLE_BUTTON = 2;
    
    private int button;
    private Point location;

    public MouseEvent(int button, Point location) {
        this.button = button;
        this.location = location;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
    
}
