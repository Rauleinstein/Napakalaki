/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;


/**
 *
 * @author trouner
 */
public class BadStuff {
    private String text;
    private int levels = 0;
    private int nVisibleTreasures = 0;  //cuando pierdes tesoros sin importar el que
    private int nHiddenTreasures = 0;   //cuando pierdes tesoros sin importar el que
    private boolean death = false;
    private ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList();
    private ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList();

    public BadStuff(String text, int levels, ArrayList<TreasureKind> tVisible,
ArrayList<TreasureKind> tHidden)
    {
        this.text = text;
        this.levels = levels;
        this.specificHiddenTreasures = tHidden;
        this.specificVisibleTreasures = tVisible;
    }
  
    public BadStuff(String text, int levels, int nVisible, int nHidden)
    {
        this.text = text;
        this.levels = levels;
        this.nVisibleTreasures = nVisible;
        this.nHiddenTreasures = nHidden;
    }
    
    public BadStuff(String text, boolean death)
    {
        this.text = text;
        this.death = death;
    }
    
    public String getText()
    {
        return this.text;
    }
    
    public int getLevels()
    {
        return this.levels;
    }

    public int getnHiddenTreasures() {
        return nHiddenTreasures;
    }

    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return specificHiddenTreasures;
    }

    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return specificVisibleTreasures;
    }
    

    public int getnVisibleTreasures() {
        return nVisibleTreasures;
    }

    public boolean isDeath() {
        return death;
    }
    
    public boolean isEmpty(){
        boolean vacio = false;
        
        //No tiene tesosoros visible ni ocultos o los arrays estan a 0
        if( (nVisibleTreasures <= 0 && nHiddenTreasures <= 0) && 
                (specificHiddenTreasures.size() == 0 && specificVisibleTreasures.size() == 0) 
           ){ 
                
            vacio = true;
        }
           
        return vacio;
    }
    
    public void substractVisibleTreasure (Treasure t){
        
        if (nVisibleTreasures > 0)
            nVisibleTreasures--;
        else{
            for (TreasureKind temp: specificVisibleTreasures ){
                if (temp.equals(t.getType())){
                    specificVisibleTreasures.remove(temp);
                    //Para eliminar solo una hago el return
                    return;
                }
            }
        }
    }
    
    public void substractHiddenTreasure (Treasure t){
        
        if (nHiddenTreasures > 0)
            nHiddenTreasures--;
        else{
            for (TreasureKind temp: specificHiddenTreasures ){
                if (temp.equals(t.getType())){
                    specificHiddenTreasures.remove(temp);
                    //Para eliminar solo una hago el return
                    return;
                }
            }
        }
    }
    
    public boolean myBadStuffIsDeath(){
        return death;
    }
    
    public String toString()
    {
        return "\nText = " + this.text + "\nLevels = " + Integer.toString(this.levels)    
                + "\nTreasures =" + Integer.toString(this.nHiddenTreasures) + " Hidden and "
                + Integer.toString(this.nVisibleTreasures) + "Visible" + "\nTesoros visibles: " + this.specificVisibleTreasures.toString() +
                "\nTesoros ocultos: " + this.specificHiddenTreasures.toString();
    }
    
    public BadStuff adjustToFitTreasureList(final ArrayList<Treasure> v, final ArrayList<Treasure> h){
        
        BadStuff bs;
        
        //Diferenciar tipo de BF
        //Si es del tipo numerico
        if (nVisibleTreasures > 0 || nHiddenTreasures > 0){
            int nTesorosVisiblesPerdidos;
            int nTesorosOcultosPerdidos;
            if (v.size() < nVisibleTreasures)
                nTesorosVisiblesPerdidos = v.size();
            else
                nTesorosVisiblesPerdidos = nVisibleTreasures;

            if (h.size() < nHiddenTreasures)
                nTesorosOcultosPerdidos = h.size();
            else
                nTesorosOcultosPerdidos = nHiddenTreasures;

            bs = new BadStuff("", 0, nTesorosVisiblesPerdidos, nTesorosOcultosPerdidos);
        }
       
        //Es del tipo elementos concretos
        else{
            ArrayList<TreasureKind> tesorosVisibles = new ArrayList<>();
            ArrayList<TreasureKind> tesorosOcultos = new ArrayList<>();
        
            ArrayList<TreasureKind> tesorosJugador = new ArrayList<>();
            for (Treasure temp: v){
                tesorosJugador.add(temp.getType());
            }
            
            for(TreasureKind objetoAEliminar: specificVisibleTreasures){
                while (tesorosJugador.contains(objetoAEliminar)){
                   tesorosVisibles.add(objetoAEliminar);
                   tesorosJugador.remove(objetoAEliminar);
                }
                
            }
            
            tesorosJugador = new ArrayList<>();
            for (Treasure temp: h){
                tesorosJugador.add(temp.getType());
            }
            
            for(TreasureKind objetoAEliminar: specificHiddenTreasures){
                while (tesorosJugador.contains(objetoAEliminar)){
                   tesorosOcultos.add(objetoAEliminar);
                   tesorosJugador.remove(objetoAEliminar);
                }
                
            }
            
            bs = new BadStuff("", 0, tesorosVisibles, tesorosOcultos);
        }
        
        return bs;
    }
     
}