/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class Napakalaki {

    private ArrayList<Player> players = new ArrayList();
    private Player currentPlayer;
    private Monster currentMonster;
    private CardDealer dealer;

    private static Napakalaki instance = null;

    private Napakalaki() {
        currentPlayer = null;
        currentMonster = null;
        dealer = CardDealer.getInstance();
    }

    private void initPlayers(ArrayList<String> names) {
        Player pl;
        for (String nombre : names) {
            pl = new Player(nombre);
            players.add(pl);
        }
    }

    private Player nextPlayer() {
        Player jugador;
        int indice = 0;
        if (currentPlayer == null) {
            indice = (int) (Math.random() * (players.size() - 1));
            return players.get(indice);
        } else {
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).equals(currentPlayer)) {
                    indice = i;
                }
            }
            indice = (indice + 1) % players.size();
        }
        return players.get(indice);
    }

    public boolean nextTurnAllowed() {
        if(currentPlayer == null)
            return true;
        else
            return currentPlayer.validState();
    }

    public static Napakalaki getInstance() {

        if (instance == null) {
            instance = new Napakalaki();
        }
        return instance;

    }

    public CombatResult developCombat() {
        CombatResult resultado;
        Cultist c;
        resultado = currentPlayer.combat(currentMonster);
        if(resultado == CombatResult.LoseAndConvert){
            c = dealer.nextCultist();
            CultistPlayer cp = new CultistPlayer(currentPlayer, c);
            players.remove(currentPlayer);
            players.add(cp);
            currentPlayer = cp;
        }
        dealer.giveMonsterBack(currentMonster);
        return resultado;
    }

    public void discardVisibleTreasures(ArrayList<Treasure> treasures) {
        for (Treasure tesoro : treasures) {
            currentPlayer.discardVisibleTreasure(tesoro);
            dealer.giveTreasureBack(tesoro);
        }
    }

    public void discardHiddenTreasures(ArrayList<Treasure> treasures) {
        for (Treasure tesoro : treasures) {
            currentPlayer.discardHiddenTreasure(tesoro);
            dealer.giveTreasureBack(tesoro);
        }
    }

    public void makeTreasuresVisible(ArrayList<Treasure> treasures) {
        for (Treasure tesoro : treasures) {
            currentPlayer.makeTreasureVisible(tesoro);
        }
    }

    public boolean buyLevels(ArrayList<Treasure> visible, ArrayList<Treasure> hidden) {
        return currentPlayer.buyLevels(visible, hidden);
    }

    public void initGame(ArrayList<String> players) {
        this.initPlayers(players);
        dealer.initCards();
        this.nextTurn();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Monster getCurrentMonster() {
        return currentMonster;
    }

    public boolean nextTurn() {
        boolean stateOK = this.nextTurnAllowed();
        if (stateOK) {
            currentMonster = dealer.nextMonster();
            currentPlayer = this.nextPlayer();
            if (currentPlayer.isDead()) {
                currentPlayer.initTreasures();
            }
        }
        return stateOK;

    }

    public boolean endOfGame(CombatResult result) {
        return result == CombatResult.WinAndWinGame;
    }

}
