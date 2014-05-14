/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;

/**
 *
 * @author trouner
 */
public class CultistPlayer extends Player {

    private static int totalCultistPlayers = 0;
    private Cultist myCultistCard;

    public CultistPlayer(Player p, Cultist C) {
        super(p);
        myCultistCard = C;
        totalCultistPlayers++;
    }
    
    @Override
    public int computeGoldCoinsValue(ArrayList<Treasure> t) {
        int totalvalue = 0;
        for (Treasure tre : t) {
            totalvalue += tre.getGoldCoins()*2;
        }
        return totalvalue / 1000;

    }
    
    
    @Override
    public int getCombatLevel() {
        return super.getCombatLevel() + this.myCultistCard.getSpecialValue();
    }

    @Override
    public int getOponentLevel(Monster m) {
        return m.getSpecialValue();
    }

    @Override
    public boolean shouldConvert() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    public static int getTotalCultistPlayers() {
        return totalCultistPlayers;
    }
    
    public Cultist getCultistCard(){
        return myCultistCard;
    }

}
