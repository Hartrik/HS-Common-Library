package cz.hartrik.common.ui.swing;

import cz.hartrik.common.io.BufferedImageUtil;
import cz.hartrik.common.io.ImageUtil;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.swing.JPanel;

/**
 * Panel, který na sobě zobrazuje určitou texturu.
 *
 * @version 1.1 /2013-06-23
 * @author Patrik Harag
 */
public class TexturePanel extends JPanel {

    private BufferedImage image;
    
    /**
     * Vytvoří panel vyplněný texturou.
     * 
     * @param pathToResource cesta k obrázku uvnitř balíčkové struktury
     * @throws RuntimeException pokud obrázek nebyl nalezen
     */
    public TexturePanel(String pathToResource) throws RuntimeException {
        this(TexturePanel.class.getResource(pathToResource));
    }
    
    /**
     * Vytvoří panel vyplněný texturou.
     * 
     * @param resource cesta k obrázku
     * @throws RuntimeException pokud obrázek nebyl nalezen
     */
    public TexturePanel(URL resource) throws RuntimeException {
        this(loadImage(resource));
    }
    
    /**
     * Vytvoří panel vyplněný texturou.
     * 
     * @param texture textura, která bude vyplňovat panel
     */
    public TexturePanel(Image texture) {
        this.image = ImageUtil.toBufferedImage(texture);
    }
    
    private static BufferedImage loadImage(URL resource) throws RuntimeException {
        try {
            return ImageUtil.getBufferedImage(resource);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        BufferedImage background = BufferedImageUtil
                .createTiledImage(image, getWidth(), getHeight());
        g.drawImage(background, 0, 0, null);
    }

}