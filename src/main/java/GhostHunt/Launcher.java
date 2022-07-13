package GhostHunt;

import java.awt.EventQueue;

/**
 * This class is the entry point for the project, containing the main method that
 * starts a game. */
public class Launcher {
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
        
            /**
             * The run method starts the game in a separate thread. */
            @Override
            public void run() {
                GameGUI gui = new GameGUI();            //create GUI
                gui.setVisible(true);                   //display GUI
                GameEngine eng = new GameEngine(gui);   //create engine
                GameInputHandler i = new GameInputHandler(eng);   //create input handler
                gui.registerKeyHandler(i);              //registers handler with GUI
                eng.startGame();                        //starts the game
            }
        });
    }
    
}
