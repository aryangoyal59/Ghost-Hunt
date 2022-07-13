package GhostHunt;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;


public class GameEngine {

    
    public enum TileType {
        WALL, FLOOR1, FLOOR2, BANK, BREACH, DOOR;
    }

    // The width of the level, measured in tiles. 
    public static final int LEVEL_WIDTH = 35;

   // The height of the level, measured in tiles.
    public static final int LEVEL_HEIGHT = 18;

    //A random number generator 
    private Random rng = new Random();

    //The current level number for the game. 
    private int levelNumber = 1;  //current level

    
     //The current turn number. 
    private int turnNumber = 1;

    
    private GameGUI gui;

    // The 2 dimensional array of level the represent the current level.
    private TileType[][] level;

    /**
     * An ArrayList of Point objects used to create and track possible locations
     * to place the player and ghosts when a new level is created.
     */
    private ArrayList<Point> spawnLocations;

    // A Player object that is the current player. 
    private Player player;

    /// An array of Ghost objects that represents the ghosts in the current level of the game. 
    private Ghost[] ghosts;

    /**
     * Constructor that creates a GameEngine object and connects it with a
     * GameGUI object.
      */
    public GameEngine(GameGUI gui) {
        this.gui = gui;
    }

    /**
     * Generates a new level. 
     */
     Random r=new Random();
      int im=r.nextInt(30);
      int ir=r.nextInt(15);
    private TileType[][] generateLevel() {
        level = new TileType[35][18];
     

        for (int i = 0; i < 35; i++) {
            for (int j = 0; j < 18; j++) {
                if(i==0 || j==0 ||j==17||i==34 )
                level[i][j]=TileType.WALL;
                else if(i==22 && j==14)
                 level[i][j]=TileType.BREACH;
                else if(j==ir || i==im )
                    level[i][j]=TileType.FLOOR1;
                else if(i==im+1 && j==ir+1)
                    level[i][j]=TileType.BANK;
                
                else 
                     level[i][j]=TileType.FLOOR2;
                
            }
            
 
        }
        
        return level;        //change this to return the 2D arrayof TileType values that you create above
    }

    /**
     * Generates spawn points for the player and ghosts. 
     */
    private ArrayList<Point> getSpawns() {
        ArrayList<Point> s = new ArrayList<Point>();
        return s;
    }

    /**
     * Adds ghosts in suitable locations in the current level.
     */
   
    private Ghost[] addGhosts() {
        Random r=new Random();
    int defaultmaxHealth = 10;  // Whatever value it should have
    int initialX = r.nextInt(7);  // Whatever value it should have
    int initialY = r.nextInt(3); 
    Ghost g=new Ghost(10,initialX,initialY);
    ghosts = new Ghost[nom+2];
    for(int i=0;i<nom+1;i++)
    {
    g=new Ghost(10+(2*nom),initialX*i,initialY*i);
    ghosts[i]=g;
    }
    return ghosts;        //change this to return an array of ghost objects
    }

    /**
     * Creates a Player object in the game. 
     */
    private Player createPlayer() {
        int defaultMaxEnergy = 10;  // Whatever value it should have
   int initialX = 1;  // Whatever value it should have
   int initialY = 1;  // Whatever value it should have
   player = new Player(10, 1, 1);
   return player;      //change this to return a Player object
    }

    /**
     * Handles the movement of the player when attempting to move left in the
     * game.
     */
    public void movePlayerLeft()  {
        if(player.xPos!=1)
        {
        player.xPos--;
         for (int i = 0; i < ghosts.length; i++) {
             if(ghosts[i]!=null)
            if(player.xPos==ghosts[i].xPos && player.yPos==ghosts[i].yPos)
                hitGhost(ghosts[i]);
        }
         if(player.xPos==im+1 && player.yPos==ir+1){
                 if(player.getEnergy()<=1)
                                player.changeEnergy(10);

                    player.depositGhost();
                    flag=0;
                    nm++;
                }
         if(player.xPos==22 && player.yPos==14 && flag==1){
             nom++;

              level[22][14]=TileType.FLOOR1;
           Ghost g=new Ghost(10+(2*nom),22,14);
        ghosts[nom]=g;
         }
         if(nm==nom+2){
        nextLevel();
        }
        }
    }

    /**
     * Handles the movement of the player when attempting to move right in the
     * game. 
     */
    int flag=0;
    public void movePlayerRight() {
        if(player.xPos!=33){
        player.xPos++;
          for (int i = 0; i < ghosts.length; i++) {
              if(ghosts[i]!=null)
            if(player.xPos==ghosts[i].xPos && player.yPos==ghosts[i].yPos)
                hitGhost(ghosts[i]);
          }
        if(player.xPos==im+1 && player.yPos==ir+1){
                                     if(player.getEnergy()<=1)
                                player.changeEnergy(10);

                    player.depositGhost();
                    nm++;flag=0;
                }
        if(player.xPos==22 && player.yPos==14 && flag==1){
             nom++;            

              level[22][14]=TileType.FLOOR1;
           Ghost g=new Ghost(10+(2*nom),22,14);
        ghosts[nom]=g;
         }
        if(nm==nom+1){
        nextLevel();
        }
        }
    }

    /**
     * Handles the movement of the player when attempting to move up in the
     * game. 
     */
    public void movePlayerUp() {
        if(player.yPos!=1){
     player.yPos--;  
        for (int i = 0; i < ghosts.length; i++) {
            if(ghosts[i]!=null)
            if(player.xPos==ghosts[i].xPos && player.yPos==ghosts[i].yPos)
                hitGhost(ghosts[i]);
        }
        if(player.xPos==im+1 && player.yPos==ir+1){
                    player.depositGhost();            
                    if(player.getEnergy()<=1)
                                player.changeEnergy(10);

                    nm++;flag=0;
                }
        if(player.xPos==22 && player.yPos==14 && flag==1){

             nom++;
              level[22][14]=TileType.FLOOR1;
           Ghost g=new Ghost(10+(2*nom),22,14);
        ghosts[nom]=g;
         }
        if(nm==nom+1){
        nextLevel();
        }
        
        }
    }

    /**
     * Handles the movement of the player when attempting to move down in the
     * game. 
     */
    public void movePlayerDown() {
        if(player.yPos!=16){
        player.yPos++;
         for (int i = 0; i < ghosts.length; i++) {
             if(ghosts[i]!=null)
            if(player.xPos==ghosts[i].xPos && player.yPos==ghosts[i].yPos)
              hitGhost(ghosts[i]);
         } 
        if(player.xPos==im+1 && player.yPos==ir+1){
                    player.depositGhost();
                    if(player.getEnergy()<=1)
                                player.changeEnergy(10);

                     nm++;flag=0;

                }
        if(player.xPos==22 && player.yPos==14 && flag==1){
             nom++;
              level[22][14]=TileType.FLOOR1;
           Ghost g=new Ghost(10+(2*nom),22,14);
        ghosts[nom]=g;
         }
         if(nm==nom+1){
        nextLevel();
        }
    }}

    /**
     * Reduces a ghost's health in response to the player attempting to move
     * into the same square as the ghost (attacking the ghost).
     */
    private void hitGhost(Ghost g) {
        if(!player.hasGhost())
        g.changeHealth(-2);
    }

    /**
     * Moves all ghosts on the current level. 
     */
    private void moveGhosts() {
        for (int i = 0; i < ghosts.length; i++) {
            moveGhost(ghosts[i]);
        }
        
    }

    /**
     * Moves a specific ghost in the game. T
     */
    private void moveGhost(Ghost g) {
       {
        Random  r = new Random();
        int i=r.nextInt(4);
        if(g!=null)
        {
         if(Math.abs(player.xPos-g.xPos)<4 && Math.abs(player.yPos-g.yPos)<4)
         {
             if(g.xPos==0){
                 g.xPos++;
             }
             else if(g.xPos==34)
                 g.xPos--;
             else if(g.yPos==0)
                 g.yPos++;
             else if(g.yPos==17)
                 g.yPos--;
                       
         }
         else
         {

        if(i==0 && g.xPos!=0)
          g.xPos--;
        else  if(i==1 && g.xPos!=34)
          g.xPos++;
        else  if(i==2 && g.yPos!=0)
          g.yPos--;
        else  if(i==3 && g.yPos!=17)
          g.yPos++;
         }
        }
        }
    }

    /**
     * Processes the ghosts array to find any Ghost in the array with 0 or less
     * health. 
     */
    int nm=0;
    private void cleanDefeatedGhosts() {
        for (int i = 0; i < ghosts.length; i++) {
            if(ghosts[i]!=null)
            if(ghosts[i].getHealth()<=1)
            {
                player.changeEnergy(-5);
                ghosts[i]=null;
                player.captureGhost();
                flag=1;

                
            }
        }
    }

    /**
     * This method is called when the number of ghosts in the level is zero and
     * the player is also not currently carrying a captured ghost, meaning that the
     * player has captured all ghosts and deposited them all, "completing" the level.
     */
    static int nom=3;
    private void nextLevel() {
        int f=0;
        for(int i=0;i<ghosts.length;i++)
        if(ghosts[i]!=null){
            f=1;
            
        }
       if(f==0){
        nom+=1;
        GameGUI gui = new GameGUI();            //create GUI
                gui.setVisible(true);                   //display GUI
                GameEngine eng = new GameEngine(gui);   //create engine
                GameInputHandler i = new GameInputHandler(eng);   //create input handler
                gui.registerKeyHandler(i);              //registers handler with GUI
                eng.startGame();  
        }
    }

    /**
     * The first version of this method should place the player in the game level
     * by setting new, fixed X and Y values for the player object in this class.
     */
    private void placePlayer() {
        
    }

    /**
     * Performs a single turn of the game when the user presses a key on the
     * keyboard. */
    public void doTurn() {
        cleanDefeatedGhosts();
        moveGhosts();
        gui.updateDisplay(level, player, ghosts);
    }

    /**
     * Starts a game. 
     */
    public void startGame() {
        level = generateLevel();
        spawnLocations = getSpawns();
        ghosts = addGhosts();
        player = createPlayer();
        gui.updateDisplay(level, player, ghosts);
    }
}
