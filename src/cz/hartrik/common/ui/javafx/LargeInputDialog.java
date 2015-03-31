package cz.hartrik.common.ui.javafx;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Dialog s velkým ({@link TextArea}) vstupním textovým polem. Tento dialog je
 * vhodný na delší texty než {@link TextInputDialog}.
 * 
 * @version 2015-03-31
 * @author Patrik Harag
 */
public class LargeInputDialog extends Dialog<String> {
    
    private final TextArea textArea;
    
    public LargeInputDialog() {
        this("", "");
    }
    
    public LargeInputDialog(String initial, String prompt) {
        this.textArea = init(initial, prompt);
    }
    
    private TextArea init(String initial, String prompt) {
        TextArea area = new TextArea(initial);
        area.setPromptText(prompt);
        area.setMaxWidth(Double.MAX_VALUE);
        area.setMaxHeight(Double.MAX_VALUE);
        area.setEditable(true);
        
        VBox vBox = new VBox(area);
        vBox.setPadding(new Insets(10));
        VBox.setVgrow(area, Priority.ALWAYS);
        
        getDialogPane().setExpandableContent(vBox);
        getDialogPane().setExpanded(true);
        getDialogPane().getButtonTypes().add(ButtonType.OK);
        
        setResultConverter(b -> (b == ButtonType.OK) ? area.getText() : null);
        
        return area;
    }

    public TextArea getTextArea() {
        return textArea;
    }
    
}