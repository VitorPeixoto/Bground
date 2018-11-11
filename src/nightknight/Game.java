package nightknight;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Vitor
 */
public class Game extends StateBasedGame {
    private static final String GAME_NAME = "BGround";
    
    public Game() {
        super(Game.GAME_NAME);
    }
    
    public Game(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new DefaultGameState());
    }
    
}
