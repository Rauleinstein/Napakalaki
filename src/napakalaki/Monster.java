/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

/**
 *
 * @author trouner
 */
public class Monster implements Card {

    private String name;
    private int combatLevel;
    private Prize p;
    private BadStuff bs;
    private int levelChangeAgainstCultistPlayer;

    public Monster(String name, int combatLevel, BadStuff bs, Prize p, int levelChangeAgainstCultistPlayer) {
        this.name = name;
        this.combatLevel = combatLevel;
        this.p = p;
        this.bs = bs;
        this.levelChangeAgainstCultistPlayer = levelChangeAgainstCultistPlayer;
    }

    public Monster(String name, int level, BadStuff bc, Prize price) {
        this.name = name;
        this.combatLevel = level;
        this.bs = bc;
        this.p = price;
        this.levelChangeAgainstCultistPlayer = 0;
    }

    public Monster(String name, int combatLevel) {
        this.name = name;
        this.combatLevel = combatLevel;
        this.levelChangeAgainstCultistPlayer = 0;
    }

    public String getName() {
        return name;
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    public int getnHiddenTreasures() {
        return bs.getnHiddenTreasures();
    }

    public BadStuff getBadStuff() {
        return bs;
    }

    public int getnVisibleTreasures() {
        return bs.getnVisibleTreasures();
    }

    public int getTreasuresGained() {
        return p.getTreasures();
    }

    public int getLevelsGained() {
        return p.getLevel();
    }

    public boolean kills() {
        return bs.isDeath();
    }

    public String toString() {
        return "\nName = " + this.name + "\nCombat Level = " + Integer.toString(this.combatLevel)
                + "\nBad Stuff :" + this.bs.toString() + "\nPrice :" + this.p.toString();
    }

    @Override
    public int getBasicValue() {
        return getCombatLevel();
    }

    @Override
    public int getSpecialValue() {
        return getCombatLevel()+this.levelChangeAgainstCultistPlayer;
    }

}
