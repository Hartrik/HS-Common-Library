package cz.hartrik.common.io;

import cz.hartrik.common.reflect.LibraryClass;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Obsahuje statické metody pro práci s obrázky typu {@link BufferedImage}.
 * 
 * @see ImageUtil
 * @see BufferedImage
 * 
 * @version 1.2 /2013-06-21
 * @author Patrik Harag
 */
@LibraryClass
public final class BufferedImageUtil {

    private BufferedImageUtil() {}

    /**
     * Vytvoří prázdný obrázek typu {@link BufferedImage} s průhlednými pixely.
     * 
     * @param width šířka obrázku
     * @param height výška obrázku
     * @return prázdný obrázek
     */
    public static BufferedImage createEmpty(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }
    
    /**
     * Vytvoří obrázek typu {@link BufferedImage} s určitou barvou pozadí.
     * 
     * @param color barva pozadí
     * @param width šířka obrázku
     * @param height výška obrázku
     * @return nový obrázek
     * @see {@link #createEmpty(int, int)}
     * @return nový obrázek s určitým pozadím
     */
    public static BufferedImage createColored(Color color, int width, int height) {
        BufferedImage image = createEmpty(width, height);
        Graphics2D g = image.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.dispose();
        return image;
    }
    
    /**
     * Vrátí kopii obrázku.
     * 
     * @param image obrázek ke zkopírování
     * @return kopie obrázku
     */
    public static BufferedImage copy(BufferedImage image) {
        BufferedImage clon = createEmpty(image.getWidth(), image.getHeight());
        Graphics2D g = clon.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return clon;
    }
    
    /**
     * Horizontálně převrátí obrázek (zrcadlově).
     * Co je vpravo, bude vlevo a naopak.
     * 
     * @param image obrázek k převrácení
     * @return převrácený obrázek
     */
    public static BufferedImage flipHorizontally(BufferedImage image) {
        int width = image.getWidth(), height = image.getHeight();
        
        BufferedImage newImage = createEmpty(width, height);
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, width, 0, 0, height, null);
        g2d.dispose();
        return newImage;
    }

    /**
     * Vertikálně převrátí obrázek (zrcadlově).
     * Co je nahoře, bude dole a naopak.
     * 
     * @param image obrázek k převrácení
     * @return převrácený obrázek
     */
    public static BufferedImage flipVertically(BufferedImage image) {
        int width = image.getWidth(), height = image.getHeight();
        
        BufferedImage newImage = createEmpty(width, height);
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, 0, height, width, 0, null);
        g2d.dispose();
        return newImage;
    }

    /**
     * Vrátí obrázek otočený o požadovaný počet stupňů.
     * Při otáčení může docházet ke ztrátě ostrosti.
     * 
     * @param image obrázek, který se bude otáčet
     * @param angle úhel otočení (ve stupních)
     * @return otočený obrázek
     */
    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(Math.toRadians(angle)));
        double cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int oldWidth  = image.getWidth();
        int oldHeight = image.getHeight();
        int newWidth  = (int) Math.floor(oldWidth * cos + oldHeight * sin);
        int newHeight = (int) Math.floor(oldHeight * cos + oldWidth * sin);
        
        BufferedImage temp = createEmpty(newWidth, newHeight);
        Graphics2D g = temp.createGraphics();
        g.translate((newWidth - oldWidth) / 2, (newHeight - oldHeight) / 2);
        g.rotate(Math.toRadians(angle), oldWidth / 2, oldHeight / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return temp;
    }
    
    /**
     * Vrátí obrázek změněný na požadovanou velikost.
     * 
     * @param image obrázek, který se bude měnit
     * @param width požadovaná šířka
     * @param height požadovaná výška
     * @return změněný obrázek
     */
    public static BufferedImage resize(BufferedImage image, int width, int height) {
        if (height > 0 && width > 0) {
            Image scaled = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return ImageUtil.toBufferedImage(scaled);
        } else {
            return image;
        }
    }
    
    /**
     * Vrátí obrázek s vyplněnými průhlednými pixely.
     * 
     * @param image obrázek
     * @param color barva pozadí
     * @return ne-průhledný obrázek
     */
    public static BufferedImage fillTransparentPixels
            (BufferedImage image, Color color) {
        
        int w = image.getWidth();
        int h = image.getHeight();
        
        BufferedImage newImage = 
                new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g = newImage.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, w, h);
        g.drawRenderedImage(image, null);
        g.dispose();
        return newImage;
    }
    
    /**
     * Vrátí nový obrázek o určitých rozměrech, poskládaný
     * z požadovaných obrázků.
     * 
     * @param image obrázek, ze kterého se bude skládat
     * @param width šířka výsledného obrázku
     * @param height výška výsledného obrázku
     * @return poskládaný obrázek
     */
    public static  BufferedImage createTiledImage
            (BufferedImage image, int width, int height){
        
        BufferedImage bufferedImage = 
                new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        
        int numX = (width / imageWidth) + 2;
        int numY = (height / imageHeight) + 2;

        BufferedImage temp = copy(image);
        Graphics2D g2d = bufferedImage.createGraphics();
        for (int y = 0; y < numY; y++) {
            for (int x = 0; x < numX; x++) {
                g2d.drawImage(temp, x * imageWidth, y * imageHeight, null);
            }
        }
        return bufferedImage;
    }
    
}