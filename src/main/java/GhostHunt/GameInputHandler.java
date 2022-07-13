package GhostHunt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class handles keyboard events (key presses) captured by a GameGUI object
 * that are passed to an instance of this class. 
 */
public class GameInputHandler implements KeyListener {

    GameEngine engine;      //GameEngine that this class calls methods from
    
    /**
     * Constructor that forms a connection between a GameInputHandler object and
     * a GameEngine object.
     */
    public GameInputHandler(GameEngine eng) {
        engine = eng;
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Method to handle key presses captured by the GameGUI. 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: engine.movePlayerLeft(); break;  //handle left arrow key
            case KeyEvent.VK_RIGHT: engine.movePlayerRight(); break;//handle right arrow
            case KeyEvent.VK_UP: engine.movePlayerUp(); break;      //handle up arrow
            case KeyEvent.VK_DOWN: engine.movePlayerDown(); break;  //handle down arrow
        }
        engine.doTurn();    //any key press will result in this method being called
    }

    
    @Override
    public void keyReleased(KeyEvent e) {}
    
}
