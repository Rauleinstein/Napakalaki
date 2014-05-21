package napakalaki;

import java.util.ArrayList;

/**
 * Clase Principal del juego que maneja todos los demás componentes
 * @author Trouner
 */
public class Napakalaki {

    private ArrayList<Player> players = new ArrayList();
    private Player currentPlayer;
    private Monster currentMonster;
    private CardDealer dealer;

    private static Napakalaki instance = null;

    /**
     * Constructor por defecto
     */
    private Napakalaki() {
        currentPlayer = null;
        currentMonster = null;
        dealer = CardDealer.getInstance();
    }

    /**
     * Crea el conjuto de jugadores de acuerdo a la lista de nombres
     * @param names ArrayList<String> para iniciar los nombres de los jugadores
     */
    private void initPlayers(ArrayList<String> names) {
        Player pl;
        for (String nombre : names) {
            pl = new Player(nombre);
            players.add(pl);
        }
    }

    /**
     * Funcion para saber cual es el siguiente jugador
     * @return Devuelve un jugador aleatorio si es la primera vez que se invoca
     * si no devuelve el siguente jugador en la lista y va ciclando. 
     */
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

    /**
     * Funcion booleana para indicar si un jugador puede pasar de turno
     * @return Devuelve true si puede pasar de turno y false en caso contrario
     */
    public boolean nextTurnAllowed() {
        if(currentPlayer == null)
            return true;
        else
            return currentPlayer.validState();
    }

    /**
     * TO DO
     * @return 
     */
    public static Napakalaki getInstance() {

        if (instance == null) {
            instance = new Napakalaki();
        }
        return instance;

    }

    /**
     * Funcion que desarrolla el combate entre el jugador actual y el monstruo actual
     * tambien decide si el jugador se tiene que convertir en cultista y lo hace. 
     * @return Devuelve un enumerado del conjunto de CombatResult en función del resultado
     * del combate
     * @see CombatResult 
     */
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

    /**
     * 
     * @param treasures 
     */
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
