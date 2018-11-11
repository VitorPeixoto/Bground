package nightknight.graphics;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;

/**
 *
 * @author Vitor
 */
public class ScrollPanel extends JScrollPane {
    
    public ScrollPanel(Component view) {
        super(view);
        
        this.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
        this.getHorizontalScrollBar().setPreferredSize (new Dimension(0,0));
        this.setWheelScrollingEnabled(false);
        
        this.setBorder(null);
        //this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
    
    public void verticalIncrement() {
        this.getVerticalScrollBar().setValue(this.getVerticalScrollBar().getValue()+10);
    }
    
    public void verticalDecrement() {
        this.getVerticalScrollBar().setValue(this.getVerticalScrollBar().getValue()-10);
    }
    
    public void horizontalIncrement() {
        this.getHorizontalScrollBar().setValue(this.getHorizontalScrollBar().getValue()+10);
    }
    
    public void horizontalDecrement() {
        this.getHorizontalScrollBar().setValue(this.getHorizontalScrollBar().getValue()-10);
    }
}
