/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author trouner
 */
public class Player {

    private boolean dead = true;
    private String name;
    private int level;
    private ArrayList<Treasure> hiddenTreasures = new ArrayList();
    private ArrayList<Treasure> visibleTreasures = new ArrayList();
    private BadStuff pendingBadStuff = new BadStuff("", false);

    public Player(String name) {
        this.name = name;
        this.level = 1;
    }

    public Player(Player P) {

        this.dead = P.isDead();
        this.level = P.getLevel();
        this.name = P.getName();
        this.visibleTreasures = P.getVisibleTreasures();
        this.hiddenTreasures = P.getHiddenTreasures();
        this.pendingBadStuff = P.getPendingBadStuff();
    }

    public BadStuff getPendingBadStuff() {
        return pendingBadStuff;
    }

    public int getOponentLevel(Monster m) {
        return m.getBasicValue();
    }

    public String getName() {
        return name;
    }

    public boolean shouldConvert() {
        return true;
    }

    public boolean isDead() {
        return dead;
    }

    public int getCombatLevel() {
        int comlvl = this.getLevel();
        boolean hasNeck = false;

        for (Treasure tre : visibleTreasures) {
            if (howManyVisibleTreasures(TreasureKind.necklace) > 0) {
                hasNeck = true;
            }
        }
        if (hasNeck) {
            for (Treasure tre : visibleTreasures) {
                comlvl += tre.getMaxBonus();
            }
        }
        else {
            for (Treasure tre : visibleTreasures) {
                comlvl += tre.getMinBonus();
            }
        }
        return comlvl;
    }

    public CombatResult combat(Monster m) {
        CombatResult combatResult;
        int myLevel = this.getCombatLevel();
        int monsterLevel = this.getOponentLevel(m);
        if (myLevel > monsterLevel) {
            this.applyPrice(m);
            if (this.level >= 10) {
                combatResult = CombatResult.WinAndWinGame;
            }
            else {
                combatResult = CombatResult.Win;
            }
        }
        else {
            Dice dice = Dice.getInstance();
            int escape = dice.nextNumber("No tienes nivel suficiente intentas escapar","Si sacas 5 o 6 escapas del monstruo");
            if (escape < 5) {
                boolean amIDead = m.kills();
                if (amIDead) {
                    this.die();
                    combatResult = CombatResult.LoseAndDie;
                }
                else {
                    if(dice.nextNumber("Intentas convertirte al cultismo","Si sacas un 6 te conviertes") == 6 && this.shouldConvert())
                        combatResult = CombatResult.LoseAndConvert;
                    else
                        combatResult = CombatResult.Lose;
                    BadStuff bad = m.getBadStuff();
                    this.applyBadStuff(bad);
                }
            }
            else {
                combatResult = CombatResult.LoseAndEscape;
            }
            this.discardNeclaceIfVisible();
        }
        return combatResult;
    }

    public ArrayList<Treasure> getVisibleTreasures() {
        return visibleTreasures;
    }

    public ArrayList<Treasure> getHiddenTreasures() {
        return hiddenTreasures;
    }

    private void bringToLife() {
        this.dead = false;

    }

    private void incrementLevels(int l) {
        this.level += l;
    }

    private void decrementLevels(int l) {
        if (this.level - l < 1) {
            this.level = 1;
        }
        else {
            this.level -= l;
        }
    }

    private void setPendingBadStuff(BadStuff pendingBadStuff) {
        this.pendingBadStuff = pendingBadStuff;
    }

    private void die() {
        this.level = 1;
        CardDealer dealer = CardDealer.getInstance();
        for (Treasure tre : this.visibleTreasures) {
            dealer.giveTreasureBack(tre);
        }

        this.visibleTreasures.clear();

        for (Treasure tre : this.hiddenTreasures) {
            dealer.giveTreasureBack(tre);
        }

        this.hiddenTreasures.clear();

        this.dieIfNoTreasures();

    }

    private void discardNeclaceIfVisible() {
        CardDealer dealer = CardDealer.getInstance();
        boolean encontrado=false;
        Treasure tre= null;
        for (Iterator<Treasure> it = visibleTreasures.iterator(); it.hasNext()&&!encontrado;) {
            tre = it.next();
            
            if (tre.getType().equals(TreasureKind.necklace)) {
                encontrado = true;
            }
        }
        if(encontrado&&tre!=null){            
                dealer.giveTreasureBack(tre);
                this.discardVisibleTreasure(tre);
        }

    }

    private void dieIfNoTreasures() {
        if (this.visibleTreasures.isEmpty() && this.hiddenTreasures.isEmpty()) {
            this.dead = true;
        }
    }

    protected int computeGoldCoinsValue(ArrayList<Treasure> t) {
        int totalvalue = 0;
        for (Treasure tre : t) {
            totalvalue += tre.getGoldCoins();
        }
        return totalvalue / 1000;

    }

    private boolean canIBuyLevels(float l) {
        return this.getLevel() + l < 10;
    }

    private int howManyVisibleTreasures(TreasureKind tKind) {
        int i = 0;
        for (Treasure tre : visibleTreasures) {
            if (tre.getType().equals(tKind)) {
                i++;
            }
        }
        return i;
    }

    public void applyBadStuff(BadStuff bad) {
        int nLevels = bad.getLevels();
        this.decrementLevels(nLevels); //Arreglar con implementación otros
        this.setPendingBadStuff(bad.adjustToFitTreasureList(this.visibleTreasures, this.hiddenTreasures));
    }

    private boolean canMakeTreasureVisible(Treasure t) {
        System.out.println("Armas de mierda:" + t.toString() + " "+
                this.howManyVisibleTreasures(TreasureKind.oneHand)+ " "+ 
                this.howManyVisibleTreasures(TreasureKind.bothHand));
        if (t.getType().equals(TreasureKind.bothHand) 
                && this.howManyVisibleTreasures(TreasureKind.oneHand) > 0){
            return false;
        }
        if (t.getType().equals(TreasureKind.oneHand)
                && this.howManyVisibleTreasures(TreasureKind.bothHand) < 1
                && this.howManyVisibleTreasures(TreasureKind.oneHand) < 2) {
            return true;
        }
        else if(t.getType().equals(TreasureKind.oneHand)
                && this.howManyVisibleTreasures(TreasureKind.bothHand)>0)
            return false;
        
        else if (this.howManyVisibleTreasures(t.getType()) < 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public void makeTreasureVisible(Treasure t) {
        if (this.canMakeTreasureVisible(t)) {
            this.visibleTreasures.add(t);
            this.hiddenTreasures.remove(t);
        }
    }

    public void discardVisibleTreasure(Treasure t) {
        this.visibleTreasures.remove(t);
        if (this.pendingBadStuff != null && !this.pendingBadStuff.isEmpty()) {
            this.pendingBadStuff.substractVisibleTreasure(t);
        }
        this.dieIfNoTreasures();
    }

    public void discardHiddenTreasure(Treasure t) {
        this.hiddenTreasures.remove(t);
        if (this.pendingBadStuff != null && !this.pendingBadStuff.isEmpty()) {
            this.pendingBadStuff.substractHiddenTreasure(t);
        }
        this.dieIfNoTreasures();
    }

    public boolean buyLevels(ArrayList<Treasure> visibleIn, ArrayList<Treasure> hiddenIn) {

        ArrayList<Treasure> visible = new ArrayList<>(visibleIn);
        ArrayList<Treasure> hidden = new ArrayList<>(hiddenIn);
        boolean ret = false;
        int levelsMayBought;
        levelsMayBought = this.computeGoldCoinsValue(hidden);
        levelsMayBought += this.computeGoldCoinsValue(visible);
        if (this.canIBuyLevels(levelsMayBought)) {
            this.incrementLevels(levelsMayBought);
            ret = true;
        }
        CardDealer dealer = CardDealer.getInstance();
        for (Treasure tre : visible) {
            this.visibleTreasures.remove(tre);
            dealer.giveTreasureBack(tre);
        }

        for (Treasure tre : hidden) {
            this.hiddenTreasures.remove(tre);
            dealer.giveTreasureBack(tre);
        }
        return ret;
    }

    public boolean validState() {
        if (pendingBadStuff == null) {
            return true;
        }
        return pendingBadStuff.isEmpty() == true && this.hiddenTreasures.size() < 5;

    }

    public void initTreasures() {
        CardDealer dealer = CardDealer.getInstance();
        Dice dice = Dice.getInstance();
        this.bringToLife();
        Treasure tre = dealer.nextTreasure();
        this.hiddenTreasures.add(tre);
        int number = dice.nextNumber("Numero de tesoros con los que vas a comenzar",
                "1 -> 1 Tesoro | 2-5 -> 2 Tesoros | 6 -> 3 Tesoros");
        if (number > 1) {
            tre = dealer.nextTreasure();
            this.hiddenTreasures.add(tre);
        }
        if (number == 6) {
            tre = dealer.nextTreasure();
            this.hiddenTreasures.add(tre);
            
        }
    }

    public boolean hasVisibleTreasures() {
        return this.visibleTreasures.isEmpty();
    }

    public int getLevel() {
        return level;
    }

    private void applyPrice(Monster m) {
        int nLevels = m.getLevelsGained();
        this.incrementLevels(nLevels);
        int nTreasures = m.getTreasuresGained();
        if (nTreasures > 0) {
            CardDealer dealer = CardDealer.getInstance();
            for (int i = 0; i < nTreasures; i++) {
                Treasure tre = dealer.nextTreasure();
                this.hiddenTreasures.add(tre);
            }
        }
    }

    public String to_s() {
        String texto;
        texto = "\nName = " + this.name 
                + "\nCombat Level = " + Integer.toString(this.level) 
                + "\nBadStuff: " + this.pendingBadStuff.toString()
                + "\nDead: " + Boolean.toString(dead);
        for(Treasure t: visibleTreasures)
            texto += t.toString();
        for(Treasure t: hiddenTreasures)
            texto += t.toString();
        return texto;
    }

    
}
