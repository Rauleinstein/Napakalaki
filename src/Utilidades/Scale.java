package Utilidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Clase abstracta con metodos staticos con idea de no tener que realizar instancias de la misma.
 * @author Álvaro Martínez Campos
 */
public abstract class Scale {
    
    /**
     * Tipos de formatos de salida de imagen
     */
    public String PNG = "png";
    public String GIF = "gif";
    public String JPG = "jpg";
    

    /**
     * Escala y guarda, en disco, una imagen apartir de una ruta, se puede definir el tipo de algoritmo usado en el proceso.
     * 
     * @param pathIn Ruta de la imagen original
     * @param pathOUT Ruta de la nueva imagen escalada
     * @param formatOUT Formato de salida. Los formatos permitidos son accesibles desde esta propia clase.
     * @param newWidth Ancho de la imagen escalada
     * @param newHigh Altura de la imagen escalada
     * @param tipoEscalado Tipo de algoritmo usado para hacer la imagen escalada. Accesibles desde la clase Image.
     */
    public static void scaleAndSaveIMG(String pathIn, String pathOUT, String formatOUT,  int newWidth, int newHigh,
            int tipoEscalado) {
        try {
            //Apertura de la imagen
            Image img = ImageIO.read(new File(pathIn));
            //Difinicion del escalado
            Image scaledImage = img.getScaledInstance(newWidth, newHigh, tipoEscalado);
            BufferedImage imageBuff = new BufferedImage(newWidth, newHigh, BufferedImage.TYPE_INT_RGB);
            //Dibuja la imagen
            Graphics g = imageBuff.createGraphics();
            g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
            g.dispose();//Liberacion del recurso

            ImageIO.write(imageBuff, formatOUT, new File(pathOUT));//Escritura de la imagen en disco
        } catch (IOException ex) {
            Logger.getLogger(Scale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Crea una copia, en memoria, escalada de la imagen, se puede definir el tipo de algoritmo usado en el proceso.
     * @param pathIn Ruta de la imagen
     * @param newWidth Ancho de la imagen escalada
     * @param newHigh Altura de la imagen escala
     * @param tipoEscalado Tipo de algoritmo usado en la escala. Accesible desde la clase Image
     * @return Imagen escalada si se ha podido realizar o null si no se pudo realizar
     */
    public static BufferedImage scaleIMG(String pathIn, int newWidth, int newHigh, int tipoEscalado) {
        try {
            //Obtencion de la imagen
            Image img = ImageIO.read(new File(pathIn));
            //Definicion del escalado
            Image scaledImage = img.getScaledInstance(newWidth, newHigh, tipoEscalado);
            BufferedImage imageBuff = new BufferedImage(newWidth, newHigh, BufferedImage.TYPE_INT_RGB);
            //Dibuja la imagen
            Graphics g = imageBuff.createGraphics();
            g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
            g.dispose();//Libera el recurso

            //Devolucion de la imagen como BufferedImage
            return imageBuff;
        } catch (IOException ex) {
            Logger.getLogger(Scale.class.getName()).log(Level.SEVERE, null, ex);
            //Si no se pudo completar la operacion devulve null
            return null;
        }
        
    }
}
