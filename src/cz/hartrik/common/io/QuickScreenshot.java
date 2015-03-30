
package cz.hartrik.common.io;

import cz.hartrik.common.reflect.LibraryClass;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 * Třída pro rychlé vytváření screenshotů. Výchozí formát pro ukládání obrázků
 * je PNG, výchozí složka odpovídá <code>System.getProperty("user.dir")</code>
 * a název <code>yyyy-MM-dd HH-mm-ss</code>.
 * 
 * @version 2014-03-28
 * @author Patrik Harag
 */
@LibraryClass
public final class QuickScreenshot {

    private QuickScreenshot() {}
    
    public final static class FileFormat {
        public static final String PNG = "png";
        public static final String JPEG = "jpg";
    }
    
    // ukládání
    
    public static Path saveImage(RenderedImage image) {
        Path path = createPath();
        return saveImage(image, path) ? path : null;
    }
    
    public static boolean saveImage(RenderedImage image, Path path) {
        return saveImage(image, path, FileFormat.PNG);
    }
    
    public static boolean saveImage(RenderedImage image, Path path, String ext) {
        try {
            ImageIO.write(image, ext, path.toFile());
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    public static Path saveImage(Image image) {
        Path path = createPath();
        return saveImage(image, path) ? path : null;
    }
    
    public static boolean saveImage(Image image, Path path) {
        return saveImage(image, path, FileFormat.PNG);
    }
    
    public static boolean saveImage(Image image, Path path, String ext) {
        return saveImage(SwingFXUtils.fromFXImage(image, null), path, ext);
    }
    
    // timestamp
    
    public static final DateTimeFormatter DEFAULT_FORMATTER =
            new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ISO_LOCAL_DATE)
                .appendLiteral(" ")
                .appendPattern("HH-mm-ss").toFormatter();
    
    public static String createTimestamp() {
        return createTimestamp(DEFAULT_FORMATTER);
    }
    
    public static String createTimestamp(DateTimeFormatter formatter) {
        return formatter.format(ZonedDateTime.now());
    }
    
    // path
    
    /** Výchozí složka - většinou složka, ve které byl program spuštěn. */
    public static final Path DEFAULT_FOLDER =
            Paths.get(System.getProperty("user.dir"));
    
    public static Path createPath() {
        return createPath(DEFAULT_FOLDER, DEFAULT_FORMATTER, FileFormat.PNG);
    }
    
    public static Path createPath(Path folder) {
        return createPath(folder, DEFAULT_FORMATTER, FileFormat.PNG);
    }
    
    public static Path createPath(Path folder, String ext) {
        return createPath(folder, DEFAULT_FORMATTER, ext);
    }
    
    public static Path createPath(Path folder, DateTimeFormatter formatter,
            String ext) {
        
        return folder.resolve(createTimestamp(formatter) + "." + ext);
    }
    
}