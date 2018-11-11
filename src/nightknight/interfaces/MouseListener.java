package nightknight.interfaces;

import java.util.concurrent.LinkedBlockingQueue;
import nightknight.eventos.MouseEvent;
import org.newdawn.slick.geom.Point;

/**
 *
 * @author Vitor
 */
public interface MouseListener {
    /*public Boolean dirty = false;
    public Point location = new Point(-1, -1);*/
    public LinkedBlockingQueue<MouseEvent> pressEvents = new LinkedBlockingQueue(1);
    public LinkedBlockingQueue<Point> moveEvents = new LinkedBlockingQueue(1);
    
    
    public static void addMoveEvent(Point p) {
        if(moveEvents.remainingCapacity() == 0)
            moveEvents.remove();
        moveEvents.add(p);
    }
    
    public static void addPressEvent(MouseEvent event) {
        if(pressEvents.remainingCapacity() == 0)
            pressEvents.remove();
        pressEvents.add(event);
    }
}
