package nightknight;

import java.util.ArrayList;
import nightknight.collision.CollisionController;
import nightknight.constants.Debug;
import nightknight.constants.Sizes;
import nightknight.eventos.MouseEvent;
import nightknight.interfaces.Changeable;
import nightknight.interfaces.KeyboardListener;
import nightknight.interfaces.MouseListener;
import nightknight.interfaces.Renderable;
import nightknight.model.Player;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Vitor
 */
public class DefaultGameState implements GameState, KeyboardListener, MouseListener {
    public static final int ID = 123123;
    private ArrayList<Renderable> renderables;
    private ArrayList<Changeable> changeables;
    private Player player;
    private InventoryController inventoryController;
    private DropPool dp;
    private Map map;
    
    private Sky sky;
    
    private CollisionController tree;
    
    public DefaultGameState() {
        tree = CollisionController.getInstance();
        inventoryController = InventoryController.getInstance();
        dp = DropPool.getInstance();
        
        changeables = new ArrayList<>();
        renderables = new ArrayList<>();
        
        player = new Player(0, BlockGenerator.getSurface()+1);
        sky = new Sky();
        //player = new Player(10, 10);
    }
    
    @Override
    public int getID() {
        return DefaultGameState.ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {            
        map = Map.getInstance();
        
        changeables.add(player);
        changeables.add(map);
        changeables.add(inventoryController);
        
        renderables.add(map); 
        renderables.add(player);
        renderables.add(dp);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        //Skybox
        sky.render(grphcs);
        // Game stuff
        grphcs.pushTransform();
            grphcs.translate(Sizes.SCREEN_WIDTH/2, (Sizes.SCREEN_HEIGHT-(Sizes.TILE_SIZE/2)-(Sizes.SCREEN_HEIGHT/2)));
            GL11.glRotatef(180, 1, 0, 0);
            
            grphcs.pushTransform();
                renderables.forEach((renderable) -> renderable.render(grphcs));
            grphcs.popTransform();
            
            //Debug stuff
            /*grphcs.setColor(Color.yellow);
            grphcs.drawLine(-3000, 0, 6000, 0);
            grphcs.fillRect(-10, -10, 20, 20);
            grphcs.setColor(Color.red);        
            grphcs.drawLine(-3000, 100, 6000, 100);
            grphcs.fillRect(-10+30, 90, 20, 20);*/
        grphcs.popTransform();
        //UI
        inventoryController.render(grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        changeables.forEach((changeable) -> changeable.atualiza());
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

    @Override
    public void mouseWheelMoved(int i) {

    }

    @Override
    public void mouseClicked(int i, int i1, int i2, int i3) {
        
    }

    @Override
    public void mousePressed(int i, int x, int y) {
        MouseListener.addPressEvent(new MouseEvent(i, new Point(x, y)));
    }

    @Override
    public void mouseReleased(int i, int i1, int i2) {

    }

    @Override
    public void mouseMoved(int oldX, int oldY, int newX, int newY) {
        MouseListener.addMoveEvent(new Point(newX, newY));
    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {

    }

    @Override
    public void setInput(Input input) {

    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inputStarted() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(int i, char c) {
        if(i == Input.KEY_ESCAPE) {
            System.exit(0);
        }
        if(c == 'e') {
            inventoryController.toggleHidden();
        }
        if(c == 'v') {
            Debug.DEBUG = !Debug.DEBUG;
        }
        KEYBOARD.put(i, true);
    }

    @Override
    public void keyReleased(int i, char c) {
        KEYBOARD.put(i, false);
    }

    @Override
    public void controllerLeftPressed(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controllerLeftReleased(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controllerRightPressed(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controllerRightReleased(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controllerUpPressed(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controllerUpReleased(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controllerDownPressed(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controllerDownReleased(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controllerButtonPressed(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controllerButtonReleased(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
