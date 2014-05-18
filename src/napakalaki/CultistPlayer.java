package napakalaki;

import java.util.ArrayList;

/**
 * Clase que implementa un jugador cultista que hereda de la clase Player
 * @author trouner
 */
public class CultistPlayer extends Player {

    //Numero total de Jugadores cultistas en la partida
    private static int totalCultistPlayers = 0;
    //Datos propios de la parte Cultista
    private Cultist myCultistCard;

    /**
     * Constructor por parámetros
     * @param p Player que se le pasa para construir
     * @param C Cultist que se para construir
     */
    public CultistPlayer(Player p, Cultist C) {
        super(p);
        myCultistCard = C;
        totalCultistPlayers++;
    }
    
    /**
     * Función que sobreescribe la función propia de Player
     * @see Player.computeGoldCoinsValue
     * @param t Lista de tesoros donde computar las monedas de oro
     * @return Entero con el valor de los tesoros de la Lista t
     */
    @Override
    public int computeGoldCoinsValue(ArrayList<Treasure> t) {
        int totalvalue = 0;
        for (Treasure tre : t) {
            totalvalue += tre.getGoldCoins()*2;
        }
        return totalvalue / 1000;

    }
    
    /**
     * Funcion que sobreescribe a la funcion propia de la clase Player
     * Calcula el nivel del jugador a partir del valor especial de la carta cultista
     * @return Entero con el nivel del jugador cultista
     */
    @Override
    public int getCombatLevel() {
        return super.getCombatLevel() + this.myCultistCard.getSpecialValue();
    }
    
    /**
     * Funcion que accede al valor especial del nivel del Monstruo m
     * @param m Monstruo del cual queremos saber su nivel especial
     * @return Entero con el nivel especial del monstruo
     */

    @Override
    public int getOponentLevel(Monster m) {
        return m.getSpecialValue();
    }

    /**
     * Funcion que nos dice si puede o no convertirse en Cultista
     * @return False siempre debido a que el jugador ya es cultista
     */
    @Override
    public boolean shouldConvert() {
        return false; 
    }

    /**
     * Funcion de acceso a la variable goblal que determina el numero de jugadores
     * cultistas en la partida
     * @return Entero con el numero de cultistas en juego
     */
    public static int getTotalCultistPlayers() {
        return totalCultistPlayers;
    }
    /**
     * Funcion de acceso a la carta de cultista asociada al jugador
     * @see Cultist
     * @return Cultist con la carta de cultista
     */
    public Cultist getCultistCard(){
        return myCultistCard;
    }

}
