package cz.hartrik.common.ui.javafx;

import cz.hartrik.common.reflect.TODO;
import java.util.function.Function;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * @version 2015-01-31
 * @author Patrik Harag
 * @param <T> typ
 */
@TODO
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