package napakalaki;

/**
 * Clase que maneja la información de los monstruos
 * @author trouner
 */
public class Monster implements Card {

    //String con el Nombre del monstruo
    private String name;
    //Entero con el nivel de combate del monstruo
    private int combatLevel;
    //Premio que el monstruo da cuando muere
    private Prize p;
    //BadStuff que el monstruo aplica cuando te derrota
    private BadStuff bs;
    //Nivel cuando combate contra un cultista
    private int levelChangeAgainstCultistPlayer;

    /**
     * Constructor por parámetros
     * @param name String con el nombre del monstruo
     * @param combatLevel Entero con el nivel del monstruo
     * @param bs BadStuff que el monstruo aplica
     * @param p Prize (Premio) que el monstruo otorga
     * @param levelChangeAgainstCultistPlayer Entero con el nivel del monstruo contra un cultista
     */
    public Monster(String name, int combatLevel, BadStuff bs, Prize p, int levelChangeAgainstCultistPlayer) {
        this.name = name;
        this.combatLevel = combatLevel;
        this.p = p;
        this.bs = bs;
        this.levelChangeAgainstCultistPlayer = levelChangeAgainstCultistPlayer;
    }
    
    /**
     * Constructor por parámetros
     * @param name String con el nombre del monstruo
     * @param combatLevel Entero con el nivel del monstruo
     * @param bs BadStuff que el monstruo aplica
     * @param p Prize (Premio) que el monstruo otorga
     */

    public Monster(String name, int combatLevel, BadStuff bs, Prize p) {
        this.name = name;
        this.combatLevel = combatLevel;
        this.bs = bs;
        this.p = p;
        this.levelChangeAgainstCultistPlayer = 0;
    }

    /**
     * Constructor por parametro
     * @param name String con el nombre del monstruo
     * @param combatLevel Entero con el nivel de combate del monstruo
     */
    public Monster(String name, int combatLevel) {
        this.name = name;
        this.combatLevel = combatLevel;
        this.levelChangeAgainstCultistPlayer = 0;
    }

    /**
     * Funcion para acceder al nombre del monstruo
     * @return String con el nombre del monstruo
     */
    public String getName() {
        return name;
    }
    
    /**
     * Funcion para acceder al nivel del monstruo
     * @return Entero con el nivel del monstruo
     */
    public int getCombatLevel() {
        return combatLevel;
    }

    /**
     * Funcion para acceder al numero tesoros ocultos del BadStuff del monstruo
     * @return Entero con el numero de tesoros ocultos
     */
    public int getnHiddenTreasures() {
        return bs.getnHiddenTreasures();
    }

    /**
     * Funcion para acceder al BadStuff del monstruo
     * @return BadStuff del monstruo
     */
    public BadStuff getBadStuff() {
        return bs;
    }

    /**
     * Funcion para acceder al numero tesoros visibles del BadStuff del monstruo
     * @return Entero con el numero de tesoros visibles
     */
    public int getnVisibleTreasures() {
        return bs.getnVisibleTreasures();
    }

    /**
     * Funcion para acceder al numero tesoros que otorga el premio del monstruo
     * @return Entero con el numero de tesoros
     */
    public int getTreasuresGained() {
        return p.getTreasures();
    }

    /**
     * Funcion para acceder al numero de niveles que otorga el premio del monstruo
     * @return Entero con el numero de niveles
     */
    public int getLevelsGained() {
        return p.getLevel();
    }

    /**
     * Funcion que comprueba si el BadStuff del monstruo es de muerte
     * @return True si el BadStuff es muerte o false en caso contrario
     */
    public boolean kills() {
        return bs.isDeath();
    }

    /**
     * Funcion que transforma el tipo en un String
     * @return Devuelve un String con todos los atributos del monstruo
     */
    public String toString() {
        return "\nName = " + this.name + "\nCombat Level = " + Integer.toString(this.combatLevel)
                + "\nBad Stuff :" + this.bs.toString() + "\nPrice :" + this.p.toString();
    }

    /**
     * Funcion que accede al nivel de combate normal del monstruo
     * @return Entero con el nivel de combate
     */
    @Override
    public int getBasicValue() {
        return getCombatLevel();
    }

    /**
     * Funcion que accede al nivel de combate contra cultistas del monstruo
     * @return Entero con el nivel de combate
     */
    @Override
    public int getSpecialValue() {
        return getCombatLevel()+this.levelChangeAgainstCultistPlayer;
    }

}
