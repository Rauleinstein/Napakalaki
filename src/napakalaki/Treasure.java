/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package napakalaki;

/**
 *
 * @author jose
 */
public class Treasure implements Card{
    private String name;
    private int goldCoins; 
    private int minBonus; 
    private int maxBonus;
    private TreasureKind type;

    public Treasure(String name, int goldCoins, int minBonus, int maxBonus, TreasureKind type) {
        this.name = name;
        this.goldCoins = goldCoins;
        this.minBonus = minBonus;
        this.maxBonus = maxBonus;
        this.type = type;
    }

    Treasure() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public int getGoldCoins() {
        return goldCoins;
    }

    public int getMinBonus() {
        return minBonus;
    }

    public int getMaxBonus() {
        return maxBonus;
    }

    public TreasureKind getType() {
        return type;
    }
    
    public String toString(){
        return "\nName = " + this.name + "Gold Coins" + this.goldCoins;
    }

    @Override
    public int getBasicValue() {
        return getMinBonus();
    }

    @Override
    public int getSpecialValue() {
        return getMaxBonus();
    }
}