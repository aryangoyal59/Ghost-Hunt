package GhostHunt;

/**The Ghost class is a subclass of Entity and adds specific state and
 * behaviour 
 */
public class Ghost extends Entity {
    
    /**
     * maxHealth stores the maximum possible health for this ghost
     */
    private final int maxHealth;
    
    /**
     * health stores the current health for this ghost
     */
    private int health;
    
    /**
     * This constructor is used to create a Ghost object to use in the game
     */
    public Ghost(int maxHealth, int x, int y) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        xPos = x;
        yPos = y;
    }
    
    /**
     * Changes the current health value for this Ghost, setting the health to
     * maxHealth if the change would cause the health attribute to exceed maxHealth
      */
    public void changeHealth(int change) {
        health += change;
        if (health > maxHealth)
            health = maxHealth;
       
    }
    
    /**
     * Returns the current health value for this Ghost
    */
    public int getHealth() {
        return health;
    }
    
    /**
     * Returns the maxHealth value for this Ghost
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    
    
}
