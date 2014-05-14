/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;

/**
 *
 * @author jose
 */
public class Sonido {

    /**
     * Método que reproduce un sonido
     *
     * @param filename Ruta del archivo a reproducir.
     */
    public static void play(final String path) {
        try {
            AudioClip clip = Applet.newAudioClip(new URL("file:"+path));
            clip.loop();
            System.out.println("Sonido de fondo activado, Press any key to stop.");
            System.in.read();
            clip.stop();
            // clip.loop();
        } catch (MalformedURLException murle) {
            System.out.println(murle);
        } catch (IOException ex) {
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que reproduce un sonido mientras muestra un JDialog.
     *
     * @param path Ruta del archivo a reproducir.
     * @param dial Dialogo a mostrar.
     */
    public static void playAndShow(String path, JDialog dial) {
        try {
            AudioClip clip = Applet.newAudioClip(new URL("file:" + path));
            clip.loop();
            dial.setVisible(true);
            clip.stop();
            // clip.loop();
        } catch (MalformedURLException murle) {
            System.out.println(murle);
        } catch (IOException ex) {
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
