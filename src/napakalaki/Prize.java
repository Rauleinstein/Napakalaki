package napakalaki;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trouner
 */
public class Prize {
    private int treasures;
    private int level;
    
    public Prize(int treasures, int level)
    {
            this.treasures = treasures;
            this.level = level;
    }
    
    public int getTreasures()
    {
        return this.treasures;
    }
    
    public int getLevel()
    {
        return this.level;
    }
    
    public String toString()
    {
        return "\nTreasures = " + Integer.toString(treasures) + "\nLevels = " + Integer.toString(level) +"\n";
    }
}
