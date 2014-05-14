/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import Utilidades.Sonido;

/**
 *
 * @author bott
 */
public class SonidoFondo extends Thread{
    
    public void run(String path){
        Sonido.play(path);
    }
}
