package napakalaki;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Alvaro
 */
public class CardDealer {

    /*Se coloca una variable con el mismo nombre de la clase, por convenio se pone
     * instance. Esta es la base del template singleton. Private para que no se acceda 
     * desde fuera de la clase
     */
    private static CardDealer instance = null;
    private ArrayList<Treasure> unusedTreasures = new ArrayList<Treasure>();
    private ArrayList<Treasure> usedTreasures = new ArrayList<>();
    private ArrayList<Monster> unusedMonsters = new ArrayList();
    private ArrayList<Monster> usedMonsters = new ArrayList<>();
    private ArrayList<Cultist> unusedCultist = new ArrayList<>();

    /**
     * Constructor por defecto. Private para que no se pueda instanciar mas de
     * una vez la clase.
     */
    private CardDealer() {
    }

    /**
     * Devuelve la instancia de la clase. Si no existia una la crea. Solo puede
     * crear una.
     *
     * @return
     */
    public static CardDealer getInstance() {
        if (instance == null) {
            instance = new CardDealer();
        }

        return instance;
    }

    /**
     * Inicializa la baraja de tesoros
     */
    private void initTreasureCardDeck() {

        unusedTreasures.add(new Treasure("Si mi amo", 0, 4, 7, TreasureKind.helmet));
        unusedTreasures.add(new Treasure("Botas de investigacion", 600, 3, 4, TreasureKind.shoe));
        unusedTreasures.add(new Treasure("Capucha de Cthulhu", 500, 3, 5, TreasureKind.helmet));
        unusedTreasures.add(new Treasure("A prueba de babas", 400, 2, 5, TreasureKind.armor));
        unusedTreasures.add(new Treasure("Botas de lluvia acida", 800, 1, 1, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Casco minero", 400, 2, 4, TreasureKind.helmet));
        unusedTreasures.add(new Treasure("Ametralladora Thompson", 600, 4, 8, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Camiseta de la UGR", 100, 1, 7, TreasureKind.armor));
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario", 400, 3, 6, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano", 300, 2, 3, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Fez alopodo", 700, 3, 5, TreasureKind.helmet));
        unusedTreasures.add(new Treasure("Hacha prehistorica", 500, 2, 5, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla", 900, 4, 8, TreasureKind.armor));
        unusedTreasures.add(new Treasure("Gaita", 500, 4, 5, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Insecticida", 300, 2, 3, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Escopeta de 3 canones", 700, 4, 6, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Garabato mistico", 300, 2, 2, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("La fuerza de Mr.T", 1000, 0, 0, TreasureKind.necklace));
        unusedTreasures.add(new Treasure("Lanzallamas", 800, 4, 8, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("La rebeca metalica", 400, 2, 3, TreasureKind.armor));
        unusedTreasures.add(new Treasure("Mazo de los antiguos", 200, 3, 4, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Necro-playboycon", 300, 3, 5, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Necro-comicon", 100, 1, 1, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Necronomicon", 800, 5, 7, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Necro-gnomicon", 200, 2, 4, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Necrotelecom", 300, 2, 3, TreasureKind.helmet));
        unusedTreasures.add(new Treasure("Linterna a 2 manos", 400, 3, 6, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Porra preternatural", 200, 2, 3, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Tentaculo de pega", 200, 0, 1, TreasureKind.helmet));
        unusedTreasures.add(new Treasure("Zapato deja-amigos", 500, 0, 1, TreasureKind.shoe));
        unusedTreasures.add(new Treasure("Shogulador", 600, 1, 1, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Varita de atizamiento", 400, 3, 4, TreasureKind.oneHand));
    }

    /**
     * Inicializa la baraja de los monstruos
     */
    private void initMostersCardDeck() {

        Prize p;
        BadStuff bs;

        //Yskhtihyssg-Goth
        bs = new BadStuff("No le hace gracia que pronuncien mal su nombre. Estas muerto", true);
        p = new Prize(3, 1);
        unusedMonsters.add(new Monster("Yskhtihyssg-Goth", 12, bs, p));

        //Rey de rosa
        bs = new BadStuff("Pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0);
        p = new Prize(4, 2);
        unusedMonsters.add(new Monster("El rey de la rosa", 13, bs, p));

        //Ángeles de la noche Ibicenca
        ArrayList<TreasureKind> tvp = new ArrayList();
        tvp.add(TreasureKind.oneHand);
        ArrayList<TreasureKind> top = new ArrayList();
        top.add(TreasureKind.oneHand);
        bs = new BadStuff("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta", 0, tvp, top);
        p = new Prize(4, 1);
        unusedMonsters.add(new Monster("Angeles", 14, bs, p));

        //Byakhees de bonanza
        tvp = new ArrayList();
        top = new ArrayList();
        top.add(TreasureKind.armor);
        tvp.add(TreasureKind.armor);
        bs = new BadStuff("Pierdes tu armadura visible y otra oculta", 0, tvp, top);
        p = new Prize(2, 1);
        unusedMonsters.add(new Monster("3 Byakhees de bonanza", 8, bs, p));

        //Chibithulhu
        tvp = new ArrayList();
        top = new ArrayList();
        tvp.add(TreasureKind.helmet);
        bs = new BadStuff("Embobados con el lindo primigenio te descartas de tu casco visible", 0, tvp, top);
        p = new Prize(1, 1);
        unusedMonsters.add(new Monster("Chibithulhu", 2, bs, p));

        //El sopor de Dunwich
        tvp = new ArrayList();
        top = new ArrayList();
        tvp.add(TreasureKind.shoe);
        bs = new BadStuff("El primordial bostezo contagioso. Pierdes el calzado visible", 0, tvp, top);
        p = new Prize(1, 1);
        unusedMonsters.add(new Monster("Dunwich", 2, bs, p));

        //El gorron en el umbral
        tvp = new ArrayList();
        top = new ArrayList();
        tvp.add(TreasureKind.oneHand);
        bs = new BadStuff("Te intentas escaquear. Pierdes una mano visible.", 0, tvp, top);
        p = new Prize(3, 1);
        unusedMonsters.add(new Monster("umbral", 10, bs, p));

        //H.P. Munchcraft
        tvp = new ArrayList();
        top = new ArrayList();
        tvp.add(TreasureKind.armor);
        bs = new BadStuff("Pierdes la armadura visible", 0, tvp, top);
        p = new Prize(2, 1);
        unusedMonsters.add(new Monster("HP Munchcraft", 6, bs, p));

        //Bichgooth
        tvp = new ArrayList();
        top = new ArrayList();
        tvp.add(TreasureKind.armor);
        bs = new BadStuff("Sientes bichos bajo la ropa. Descarta la armadura visible", 0, tvp, top);
        p = new Prize(1, 1);
        unusedMonsters.add(new Monster("Bichgooth", 2, bs, p));

        //La que redacta en las tinieblas
        bs = new BadStuff("Toses los pulmones y pierdes 2 niveles", 2, 0, 0);
        p = new Prize(1, 1);
        unusedMonsters.add(new Monster("tinieblas", 2, bs, p));

        //Los hondos
        bs = new BadStuff("Estos monstruos resultan bastante superciales y te aburren  mortalmente. Estas muerto", true);
        p = new Prize(2, 1);
        unusedMonsters.add(new Monster("Los hondos", 8, bs, p));

        //Roboggoth
        tvp = new ArrayList();
        top = new ArrayList();
        tvp.add(TreasureKind.bothHand);
        bs = new BadStuff("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible", 2, tvp, top);
        p = new Prize(2, 1);
        unusedMonsters.add(new Monster("Roboggoth", 8, bs, p));

        //Pollipolipo volante
        tvp = new ArrayList();
        tvp.add(TreasureKind.bothHand);
        bs = new BadStuff("Da mucho asquito. Pierdes 3 niveles.", 3, 0, 0);
        p = new Prize(1, 1);
        unusedMonsters.add(new Monster("Pollipolipo volante", 3, bs, p));

        //Semillas Cthulhu
        bs = new BadStuff("Pierdes 2 niveles y 2 tesoros ocultos.", 2, 0, 2);
        p = new Prize(2, 1);
        unusedMonsters.add(new Monster("Semillas Cthulhu", 4, bs, p));

        //El espia
        tvp = new ArrayList();
        top = new ArrayList();
        tvp.add(TreasureKind.helmet);
        bs = new BadStuff("Te asusta en la noche. Pierdes un casco visible.", 2, tvp, top);
        p = new Prize(1, 1);
        unusedMonsters.add(new Monster("El espia", 5, bs, p));

        //Familia Feliz
        bs = new BadStuff("La familia te atrapa. Estas muerto.", true);
        p = new Prize(4, 1);
        unusedMonsters.add(new Monster("Familia feliz", 1, bs, p));

        //Dameargo
        tvp = new ArrayList();
        top = new ArrayList();
        tvp.add(TreasureKind.oneHand);
        bs = new BadStuff("Te intentas escaquear. Pierdes una mano visible.", 2, tvp, top);
        p = new Prize(2, 1);
        unusedMonsters.add(new Monster("Dameargo", 0, bs, p));

        //El Lenguas
        bs = new BadStuff("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles.", 2, 5, 0);
        p = new Prize(2, 1);
        unusedMonsters.add(new Monster("El lenguas", 20, bs, p));

        //Bicéfalo
        bs = new BadStuff("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.", 3, 99, 0);
        p = new Prize(2, 1);
        unusedMonsters.add(new Monster("Bicefalo", 20, bs, p));
        
        //El mal impronunciable        
        tvp = new ArrayList();
        top = new ArrayList();
        tvp.add(TreasureKind.oneHand);
        bs = new BadStuff("Pierdes una mano visible", 0, tvp, top);
        p = new Prize(3, 1);
        unusedMonsters.add(new Monster("El mal", 10, bs, p, -2));
        
        //Testigos Oculares
        bs = new BadStuff("Pierdes tus tesoros visibles. Ja ja ja", 3, 99, 0);
        p = new Prize(2, 1);
        unusedMonsters.add(new Monster("Testigos oculares", 6, bs, p, 2));
        
        //El gran cthulhu
        bs = new BadStuff("Hoy no es tu d ́ıa de suerte. Mueres.",true);
        p = new Prize(2, 5);
        unusedMonsters.add(new Monster("El gran cthulhu", 20, bs, p, 4));
        
        //Serpiente Polıtico
        bs = new BadStuff("Tu gobierno te recorta 2 niveles.", 2, 0, 0);
        p = new Prize(2, 1);
        unusedMonsters.add(new Monster("Serpiente politico", 8, bs, p,-2));
        
        //Felpuggoth        
        tvp = new ArrayList();
        top = new ArrayList();
        tvp.add(TreasureKind.armor);        
        tvp.add(TreasureKind.helmet);        
        top.add(TreasureKind.oneHand);        
        top.add(TreasureKind.oneHand);        
        top.add(TreasureKind.oneHand);                
        top.add(TreasureKind.oneHand);        
        top.add(TreasureKind.bothHand);        
        top.add(TreasureKind.bothHand);        
        top.add(TreasureKind.bothHand);        
        top.add(TreasureKind.bothHand);
        bs = new BadStuff("Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas.", 3, tvp, top);
        p = new Prize(2, 1);
        unusedMonsters.add(new Monster("Felpuggoth", 20, bs, p, 5));
        
        //Shoggoth
        bs = new BadStuff("Pierdes 2 niveles.", 2, 0, 0);
        p = new Prize(4, 2);
        unusedMonsters.add(new Monster("Shoggoth", 16, bs, p, -4));
        
        //Lolitagooth
        bs = new BadStuff("Pintalabios negro. Pierdes 2 niveles.", 2, 0, 0);
        p = new Prize(1, 1);
        unusedMonsters.add(new Monster("Lolitagooth", 2, bs, p, 3));

    }
    
    private void initCultistCardDeck(){
        unusedCultist.add(new Cultist("Sectario1", 1));
        unusedCultist.add(new Cultist("Sectario2", 2));
        unusedCultist.add(new Cultist("Sectario3", 1));
        unusedCultist.add(new Cultist("Sectario4", 1));
        unusedCultist.add(new Cultist("Sectario5", 2));
        unusedCultist.add(new Cultist("Sectario6", 1));
    }
    

    /**
     * Baraja el mazo de tesoros
     */
    private void suffleTreasures() {

        Collections.shuffle(unusedTreasures);
    }

    /**
     * Baraja el mazo de monstruos
     */
    private void suffleMonsters() {

        Collections.shuffle(unusedMonsters);
    }
    private void suffleCultist() {

        Collections.shuffle(unusedCultist);
    }

    /**
     * Encuentra y devuelve el siguiente tesoro de la baraja
     *
     * @return Siguiente tesoro de la baraja
     */
    public Treasure nextTreasure() {

        if (unusedTreasures.isEmpty()) {
            unusedTreasures.addAll(usedTreasures);
            suffleTreasures();
            usedTreasures.clear();
        }

        return unusedTreasures.remove(0);

    }
    public Cultist nextCultist() {

        return unusedCultist.remove(0);

    }

    /**
     * Encuentra y devuelve el siguiente monstruo de la baraja
     *
     * @return Siguiente monstruo de la baraja
     */
    public Monster nextMonster() {

        if (unusedMonsters.isEmpty()) {
            unusedMonsters.addAll(usedMonsters);
            suffleMonsters();
            usedMonsters.clear();
        }

        return unusedMonsters.remove(0);
    }

    /**
     * Devuelve una carta de tesoro a la baraja
     *
     * @param t Carta a devolver
     */
    public void giveTreasureBack(Treasure t) {

        usedTreasures.add(t);
    }

    /**
     * Devuelve una carta de monstruo a la baraja
     *
     * @param m Carta que se devuelve
     */
    public void giveMonsterBack(Monster m) {

        usedMonsters.add(m);
    }

    /**
     * 
     */
    public void initCards() {
        this.initTreasureCardDeck();
        this.suffleTreasures();
        this.initMostersCardDeck();
        this.suffleMonsters();
        this.initCultistCardDeck();
        this.suffleCultist();
    }

}
