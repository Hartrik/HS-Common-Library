package cz.hartrik.common;

import java.util.function.Consumer;

/**
 * Mutable objekt, který se částečně podobá {@link java.util.Optional}.
 * Využívá se hlavně při práci s lambda výrazy, kde musejí být všechny proměnné
 * <i>final</i> nebo <i>effectively final</i>.
 *
 * @version 2015-07-27
 * @author Patrik Harag
 * @param <T> typ uloženého objektu
 */
public class Container<T> {

    private T value;

    private Container(T value) {
        this.value = value;
    }

    // metody

    /**
     * Vrátí hodnotu, může to být {@code null}.
     *
     * @return uložená hodnota
     */
    public T get() {
        return value;
    }

    /**
     * Vrátí hodnotu, pokud obsahuje {@code null}, vrátí výchozí hodnotu.
     *
     * @param defaultValue výchozí hodnota
     * @return hodnota nebo výchozí hodnota předaná parametrem metody
     */
    public T getOrDefault(T defaultValue) {
        return (isPresent()) ? value : defaultValue;
    }

    /**
     * Nastaví novou hodnotu.
     *
     * @param value hodnota, může být {@code null}
     */
    public void set(T value) {
        this.value = value;
    }

    /**
     * Nastaví hodnotu na {@code null}.
     */
    public void setNull() {
        this.value = null;
    }

    /**
     * Vrátí {@code true}, pokud obsahuje hodnotu, jinak {@code false}.
     *
     * @return boolean
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * Zavolá funkci, pokud obsahuje hodnotu.
     *
     * @param consumer funkce
     */
    public void ifPresent(Consumer<T> consumer) {
        if (isPresent())
            consumer.accept(value);
    }

    // ---- ---- statické metody ---- ----

    /**
     * Vytvoří prázdný kontejner.
     *
     * @param <T> typ hodnoty
     * @return prázdný kontejner
     */
    public static <T> Container<T> empty() {
        return new Container<>(null);
    }

    /**
     * Vytvoří kontejner.
     *
     * @param <T> typ hodnoty
     * @param value hodnota, nesmí být {@code null}
     * @return kontejner
     */
    public static <T> Container<T> of(T value) {
        return new Container<>(Checker.requireNonNull(value));
    }

    /**
     * Vytvoří kontejner.
     *
     * @param <T> typ hodnoty
     * @param value hodnota nebo {@code null}
     * @return kontejner
     */
    public static <T> Container<T> ofNullable(T value) {
        return new Container<>(value);
    }

}