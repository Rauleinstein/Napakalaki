package napakalaki;

/**
 * Clase que modela el tipo de Cultista
 * @author trouner
 */
public class Cultist implements Card{
    //Nombre del cultista
    private String name;
    //Niveles que lleva ganados
    private int gainedLevels;
    
    /**
     * Constructor por parámetros
     * @param name String con el nombre del cultista
     * @param gainedLevels Entero con el numero de niveles
     */

    public Cultist(String name, int gainedLevels) {
        this.name = name;
        this.gainedLevels = gainedLevels;
    }
    
    /**
     * Funcion para consultar el nombre
     * @return Devuelve un String con el nombre del cultista
     */
    public String getName(){return name;}

    /**
     * Funcion para consultar los niveles
     * Version BasicValue
     * @return Devuelve los niveles que ha ganado el jugador
     */
    @Override
    public int getBasicValue() {
        return gainedLevels;
    }

    /**
     * Funcion para consultar los niveles
     * Version SpecialValue
     * @return Devuelve los niveles que ha ganado el jugador cultista multiplicados por el
     * numero total de cultistas en juego
     */
    @Override
    public int getSpecialValue() {
        return gainedLevels*CultistPlayer.getTotalCultistPlayers();
    }
    
    
    
}
