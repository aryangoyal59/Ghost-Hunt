package GhostHunt;


public abstract class Entity {
    
    
    protected int xPos;
    
    
    protected int yPos;
        
    
    public int getX() {
        return xPos;
    }
    
    
    public int getY() {
        return yPos;
    }
    
    
    public void setPosition (int x, int y) {
        xPos = x;
        yPos = y;
    }
    
}


