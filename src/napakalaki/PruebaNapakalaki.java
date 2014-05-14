package napakalaki;

import GUI.NapakalakiView;
import GUI.PlayerNamesCapture;
import Utilidades.Tags;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PruebaNapakalaki {

    public static void main(String[] args) {
        
        Napakalaki napakalakiModel;
        napakalakiModel = Napakalaki.getInstance();
        NapakalakiView napakalakiView = new NapakalakiView();
       
        Dice.createInstance(napakalakiView);
        ArrayList<String> names = new ArrayList();
        PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView,true);
        namesCapture.setLocationRelativeTo(null);
        names = namesCapture.getNames();
        napakalakiModel.initGame(names);
        napakalakiView.setNapakalaki(napakalakiModel);
        napakalakiView.showView();
        
        //Sonido de fondo en el menu principal
        SonidoFondo sf = new SonidoFondo();
        sf.run(Tags.PRINCIPAL);
        
        

        
        
//        Napakalaki napa;
//        napa = Napakalaki.getInstance();
//        ArrayList <String> players = new ArrayList();
//        
//        players.add("Carlos");
//        
//        CombatResult cr = null;
//        
//        napa.initGame(players);
//               
//        System.out.println("Player actual antes de comprar niveles: " + napa.getCurrentPlayer().to_s());
//        
//        System.out.println(napa.buyLevels(napa.getCurrentPlayer().getVisibleTreasures(), napa.getCurrentPlayer().getHiddenTreasures()));
//        
//        System.out.println("Player actual tras intentar comprar niveles: " + napa.getCurrentPlayer().to_s());
//        
//        while(cr != CombatResult.WinAndWinGame){
//            
//            System.out.println("Player actual " + napa.getCurrentPlayer().to_s());
//            
//            ArrayList <Treasure> tr = new ArrayList <> ();
//            tr.addAll(napa.getCurrentPlayer().getHiddenTreasures());
//            
//            napa.makeTreasuresVisible(tr);
//      
//            System.out.println("Player actual tras equiparse " + napa.getCurrentPlayer().to_s());
//
//            System.out.println("Monstruo actual " + napa.getCurrentMonster().toString());
//
//            cr = napa.developCombat();
//
//            System.out.println("Resultado del combate " + cr);
//            
//            if(cr == CombatResult.WinAndWinGame){
//                System.out.println("El ganador es: " + napa.getCurrentPlayer().to_s());
//            }
//            
//            ArrayList <Treasure> quitar2 = new ArrayList();
//                
//            while(napa.getCurrentPlayer().getHiddenTreasures().size()>4){
//                    
//                quitar2.add(napa.getCurrentPlayer().getHiddenTreasures().get(0));
//                napa.discardHiddenTreasures(quitar2);
//                quitar2.clear();
//                                    
//            }
//            
//            while(!napa.nextTurn()){
//                
//                BadStuff aux = napa.getCurrentPlayer().getPendingBadStuff();
//                
//                ArrayList <Treasure> quitar = new ArrayList();
//                
//                while(aux.getnVisibleTreasures() != 0){
//                
//                    quitar.add(napa.getCurrentPlayer().getVisibleTreasures().get(0));
//                    napa.discardVisibleTreasures(quitar);
//                    quitar.clear();
//
//                }
//                
//                while(aux.getnHiddenTreasures() != 0){
//                
//                    quitar.add(napa.getCurrentPlayer().getHiddenTreasures().get(0));
//                    napa.discardHiddenTreasures(quitar);
//                    quitar.clear();
//                }
//                
//                if(!aux.getSpecificHiddenTreasures().isEmpty()){
//                
//                    for(TreasureKind i: aux.getSpecificHiddenTreasures()){
//                    
//                        for(Treasure j: napa.getCurrentPlayer().getHiddenTreasures()){
//                        
//                            if(j.getType() == i){
//                            
//                                quitar.add(j);
//                                napa.discardHiddenTreasures(quitar);
//                                quitar.clear();
//                            
//                            }
//                            
//                        }
//                    
//                    }
//                    
//                }
//                
//                if(!aux.getSpecificVisibleTreasures().isEmpty()){
//                
//                    for(TreasureKind i: aux.getSpecificVisibleTreasures()){
//                    
//                        for(Treasure j: napa.getCurrentPlayer().getVisibleTreasures()){
//                        
//                            if(j.getType() == i){
//                            
//                                quitar.add(j);
//                                
//                            
//                            }
//                            
//                        }
//                    
//                    }
//                
//                    napa.discardVisibleTreasures(quitar);
//                    quitar.clear();
//                    
//                }
//                
//                System.out.println("Mal rollo tras aplicarse " + aux.toString());
//                System.out.println("Player tras aplicar mal rollo" + napa.getCurrentPlayer().to_s());
//            }
//       
//        }
    }
}