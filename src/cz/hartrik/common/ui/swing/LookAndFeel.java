package cz.hartrik.common.ui.swing;

import cz.hartrik.common.reflect.LibraryClass;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Bezpečně nastaví Look &amp; Feel grafického uživatelského rozhraní.
 *
 * @version 1.2 /2014-02-10
 * @author Patrik Harag
 */
@LibraryClass
public final class LookAndFeel {

    private LookAndFeel() {}
    
    /**
     * Nastaví Look &amp; Feel aplikace.
     * 
     * @param lookAndFeel vzhled, který má být nastaven
     * @return úspěšnost operace
     */
    public static boolean setLaF(String lookAndFeel) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (lookAndFeel.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    return true;
                }
            }
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException e) {}
        return false;
    }
    
    /**
     * Nastaví Look &amp; Feel Windows.
     * 
     * @return úspěšnost operace
     */
    public static boolean lafWindows() {
        return setLaF("Windows");
    }
    
    /**
     * Nastaví Look &amp; Feel Metal.
     * 
     * @return úspěšnost operace
     */
    public static boolean lafMetal() {
        return setLaF("Metal");
    }
    
    /**
     * Nastaví Look &amp; Feel Nimbus.
     * 
     * @return úspěšnost operace
     */
    public static boolean lafNimbus() {
        return setLaF("Nimbus");
    }
    
    /**
     * Nastaví systémové Look &amp; Feel, pokud je k dispozici.
     * 
     * @return úspěšnost, systémové Look &amp; Feel nemusí být k dispizici.
     */
    public static boolean systemLaF() {
        try {
            UIManager.setLookAndFeel(UIManager
                    .getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            return false;
        }
        return true;
    }
    
}