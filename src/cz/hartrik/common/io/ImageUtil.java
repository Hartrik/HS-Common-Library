package cz.hartrik.common.io;

import cz.hartrik.common.reflect.LibraryClass;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Obsahuje statické metody pro načtení a převod obrázků. Pracuje s obrázky typů
 * {@link Image}, {@link ImageIcon} a {@link BufferedImage}.
 * 
 * @version 1.0 /2013-05-23
 * @author Patrik Harag
 */
@LibraryClass
public final class ImageUtil {

    private ImageUtil() {}
    
    // ------------ načítání obrázků pomocí třídy File ------------
    
    /**
     * Načte obrázek a vrátí jej jako <code>Image</code>.
     * 
     * @param file soubor s obrázkem
     * @return načtený obrázek
     * @throws IOException
     */
    public static Image getImage(File file) throws IOException {
        return ImageIO.read(file);
    }
    
    /**
     * Načte obrázek a vrátí jej jako <code>ImageIcon</code>.
     * 
     * @param file soubor s obrázkem
     * @return načtený obrázek
     * @throws IOException
     */
    public static ImageIcon getImageIcon(File file) throws IOException {
        Image image = getImage(file);
        return toImageIcon(image);
    }
    
    /**
     * Načte obrázek a vrátí jej jako <code>BufferedImage</code>.
     * 
     * @param file soubor s obrázkem
     * @return načtený obrázek
     * @throws IOException
     */
    public static BufferedImage getBufferedImage(File file) throws IOException {
        return ImageIO.read(file);
    }
    
    // ------------ načítání obrázků pomocí URL ------------
    
    /**
     * Načte obrázek a vrátí jej jako <code>Image</code>.
     * 
     * @param resource cesta k obrázku
     * @return načtený obrázek
     */
    public static Image getImage(URL resource) {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Image image = defaultToolkit.getImage(resource);
        return image;
    }
    
    /**
     * Načte obrázek a vrátí jej jako <code>ImageIcon</code>.
     * 
     * @param resource cesta k obrázku
     * @return načtený obrázek
     */
    public static ImageIcon getImageIcon(URL resource) {
        ImageIcon imageIcon = new ImageIcon(resource);
        return imageIcon;
    }
    
    /**
     * Načte obrázek a vrátí jej jako <code>BufferedImage</code>.
     * 
     * @param resource cesta k obrázku
     * @return načtený obrázek
     * @throws IOException
     */
    public static BufferedImage getBufferedImage(URL resource) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(resource);
        return bufferedImage;
    }
    
    // ------------ převody ------------
    
    /**
     * Převede typ <code>ImageIcon</code> na <code>Image</code>.
     * 
     * @param imageIcon obrázek k převedení
     * @return převedený obrázek
     */
    public static Image toImage(ImageIcon imageIcon) {
        return imageIcon.getImage();
    }
    
    /**
     * Převede typ <code>BufferedImage</code> na <code>Image</code>.
     *
     * @param bufferedImage obrázek k převedení
     * @return převedený obrázek
     */
    public static Image toImage(BufferedImage bufferedImage) {
        return (Image) bufferedImage;
    }
    
    /**
     * Převede typ <code>Image</code> na <code>ImageIcon</code>.
     *
     * @param image obrázek k převedení
     * @return převedený obrázek
     */
    public static ImageIcon toImageIcon(Image image) {
        return new ImageIcon(image);
    }
    
    /**
     * Převede typ <code>BufferedImage</code> na <code>ImageIcon</code>.
     *
     * @param bufferedImage obrázek k převedení
     * @return převedený obrázek
     */
    public static ImageIcon toImageIcon(BufferedImage bufferedImage) {
        Image image = toImage(bufferedImage);
        return toImageIcon(image);
    }
    
    /**
     * Převede typ <code>Image</code> na <code>BufferedImage</code>.
     *
     * @param image obrázek k převedení
     * @return převedený obrázek
     */
    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) return (BufferedImage) image;
        
        BufferedImage bufferedImage = new BufferedImage(
                image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }
    
    /**
     * Převede typ <code>ImageIcon</code> na <code>BufferedImage</code>.
     *
     * @param imageIcon obrázek k převedení
     * @return převedený obrázek
     */
    public static BufferedImage toBufferedImage(ImageIcon imageIcon) {
        Image image = toImage(imageIcon);
        return toBufferedImage(image);
    }
    
}