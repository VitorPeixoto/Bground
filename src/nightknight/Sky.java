package nightknight;

import nightknight.assets.images.ImageAssets;
import nightknight.constants.Sizes;
import nightknight.interfaces.Renderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author Vitor
 */
public class Sky implements Renderable {
    private Image skybox, sunMoon;
    private Color color;
    private double pace = 0.001;
    private float angle = 0;
    
    public Sky() {
        this.skybox = ImageAssets.getImage("Skybox.png");
        this.sunMoon = ImageAssets.getImage("SunMoon2.png");
        //color = new Color(108, 134, 206);
        color = new Color(90, 120, 220);
        //color = new Color(90, 120, 220, 0.2f);
    }

    @Override
    public void render(Graphics g) {
        g.pushTransform();
            //g.scale(Sizes.SCREEN_WIDTH, Sizes.SCREEN_HEIGHT);
            skybox.draw(0, 0, Sizes.SCREEN_WIDTH, Sizes.SCREEN_HEIGHT, color);
            //g.rotate(0.5f*Sizes.SCREEN_WIDTH, 0.5f*Sizes.SCREEN_HEIGHT, angle);
            g.translate(0, -angle*10);
            sunMoon.draw(0, 0, Sizes.SCREEN_WIDTH, (int)(Sizes.SCREEN_WIDTH*1.125));
        g.popTransform();
        cicle();
    }
    
    public void cicle() {
        if((color.a + pace) < 0.3 || color.a + pace > 1.0) pace *= -1;
        color.a += pace;
        if((angle+=0.01) >= /*360.0*/(sunMoon.getHeight()/10)) angle = 0;
    }
}
