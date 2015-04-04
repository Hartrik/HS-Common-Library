package cz.hartrik.common.ui.javafx;

import java.util.function.Function;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * Zjednodušuje tvorbu cell factory pro {@link ListView}.
 * 
 * @version 2015-01-31
 * @author Patrik Harag
 * @param <T> typ
 */
public abstract class SimpleCellFactory<T>
        implements Callback<ListView<T>, ListCell<T>> {
    
    @Override 
    public ListCell<T> call(ListView<T> list) {
        return new ListCell<T>() {

            @Override
            public void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(new Pane());
                } else {
                    setGraphic(createContent(item));
                }
            }
        };
    }
    
    protected abstract Node createContent(T item);
    
    /**
     * Tovární metoda pro jednoduchou tvorbu cell factory s použitím lambda
     * výrazu.
     * 
     * @param <T> typ obsahu {@link ListView}
     * @param function funkce vytvářející jednotlivá políčka {@link ListView}
     * @return cell factory
     */
    public static <T> SimpleCellFactory<T> of(
            final Function<T, ? extends Node> function) {

        return new SimpleCellFactory<T>() {

            @Override
            protected Node createContent(T item) {
                return function.apply(item);
            }
        };
    }

}