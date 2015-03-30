package cz.hartrik.common.ui.swing;

import cz.hartrik.common.reflect.LibraryClass;
import javax.swing.UIManager;

/**
 * Třída obsahuje statické metody pro nastavení českých popisků do
 * některých swingových komponent.
 * 
 * <ul>
 *   <li>{@link javax.swing.JFileChooser}</li>
 *   <li>{@link javax.swing.JOptionPane}</li>
 * </ul>
 * 
 * @version 1.1 /2013-06-23
 * @author Patrik Harag
 */
@LibraryClass
public final class CzechUI {
    
    private CzechUI() {}
    
    /**
     * Nastaví české popisky pro {@link javax.swing.JFileChooser}.
     */
    public static void czJFileChooser() {
        UIManager.put("FileChooser.lookInLabelText", "Hledat");
        UIManager.put("FileChooser.folderNameLabelText", "Název adresáře:");
        UIManager.put("FileChooser.directoryDescriptionText","Adresář");
        UIManager.put("FileChooser.acceptAllFileFilterText", "Všechny soubory (*.*)");
        
        UIManager.put("FileChooser.upFolderToolTipText", "O úroveň výš");
        UIManager.put("FileChooser.upFolderAccessibleName","Nahoru");
        
        UIManager.put("FileChooser.listViewButtonToolTipTextlist", "Seznam");
        UIManager.put("FileChooser.listViewButtonAccessibleName","Seznam");
        
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Podrobnosti");
        UIManager.put("FileChooser.detailsViewButtonAccessibleName", "Podrobnosti");
        
        UIManager.put("FileChooser.homeFolderToolTipText", "Domů");
        UIManager.put("FileChooser.homeFolderAccessibleName","Domů");
        
        UIManager.put("FileChooser.newFolderToolTipText", "Nový adresář");
        UIManager.put("FileChooser.newFolderAccessibleName","Nový adresář");
        UIManager.put("FileChooser.newFolderErrorText", "Chyba při vytvoření adresáře");
        
        UIManager.put("FileChooser.filesOfTypeLabelText", "Soubory typu:");
        UIManager.put("FileChooser.fileNameLabelText", "Název souboru:");
        UIManager.put("FileChooser.fileDescriptionText", "Generický soubor");
        UIManager.put("FileChooser.fileSizeHeaderText","Velikost:");
        UIManager.put("FileChooser.fileDateHeaderText", "Modifikováno:");
        
        UIManager.put("FileChooser.saveButtonText", "Uložit");
        UIManager.put("FileChooser.saveButtonToolTipText", "Uložit");
        
        UIManager.put("FileChooser.openButtonText", "Otevřít");
        UIManager.put("FileChooser.openButtonToolTipText", "Otevření vybraného souboru");
        UIManager.put("FileChooser.openDialogTitleText", "Otevřít");
        
        UIManager.put("FileChooser.cancelButtonText", "Storno");
        UIManager.put("FileChooser.cancelButtonToolTipText",  "Ukončení výběrového dialogu");
        
        UIManager.put("FileChooser.updateButtonText", "Obnovit");
        UIManager.put("FileChooser.updateButtonToolTipText", "Obnova výpisu");
        
        UIManager.put("FileChooser.helpButtonText", "Nápověda");
        UIManager.put("FileChooser.helpButtonToolTipText", "Nápověda");
    }
    
    /**
     * Nastaví česká tlačítka pro {@link javax.swing.JOptionPane}.
     */
    public static void czJOptionPane() {
        UIManager.put("OptionPane.yesButtonText", "Ano");
        UIManager.put("OptionPane.noButtonText", "Ne");
        UIManager.put("OptionPane.cancelButtonText", "Zrušit");
        UIManager.put("OptionPane.okButtonText", "OK");
    }
    
}