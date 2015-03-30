package cz.hartrik.common;

import cz.hartrik.common.reflect.LibraryClass;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Obsahuje statické metody pro práci s výjimkami.
 * 
 * @version 2015-03-30
 * @author Patrik Harag
 */
@LibraryClass
public final class Exceptions {

    private Exceptions() { }
    
    // --- unchecked
    
    // runnable
    
    public static void unchecked(ThrowableRunnable runnable) {
        runnable.run();
    }
    
    public static interface ThrowableRunnable extends Runnable {
        public void runChecked() throws Throwable;

        @Override
        public default void run() {
            try {
                runChecked();
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
    // supplier
    
    public static <T> T unchecked(ThrowableSupplier<T> supplier) {
        return supplier.get();
    }
    
    public static interface ThrowableSupplier<T> extends Supplier<T> {
        public T getChecked() throws Throwable;
        
        @Override
        public default T get() {
            try {
                return getChecked();
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
    // consumer
    
    public static <T> void unchecked(ThrowableConsumer<T> consumer, T parameter) {
        consumer.accept(parameter);
    }
    
    public static interface ThrowableConsumer<T> extends Consumer<T> {
        public void acceptChecked(T parameter) throws Throwable;
        
        @Override
        public default void accept(T parameter) {
            try {
                acceptChecked(parameter);
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
    // function
    
    public static <T, R> R unchecked(ThrowableFunction<T, R> function, T parameter) {
        return function.apply(parameter);
    }
    
    public static interface ThrowableFunction<T, R> extends Function<T, R> {
        public R applyChecked(T parameter) throws Throwable;

        @Override
        public default R apply(T parameter) {
            try {
                return applyChecked(parameter);
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
    // --- pass
    
    // runnable
    
    public static boolean pass(ThrowableRunnable runnable) {
        try {
            runnable.runChecked();
        } catch (Throwable ex) {
            return false;
        }
        return true;
    }
    
    // consumer
    
    public static <T> boolean pass(ThrowableConsumer<T> consumer, T parameter) {
        try {
            consumer.acceptChecked(parameter);
        } catch (Throwable ex) {
            return false;
        }
        return true;
    }
    
    // --- hide
    
    // runnable
    
    public static void hide(ThrowableRunnable runnable) {
        try {
            runnable.runChecked();
        } catch (Throwable ex) { }
    }
    
    // consumer
    // nesmí mít stejný název jako call(ThrowableFunction<T, R> function, ...
    public static <T> void hide(ThrowableConsumer<T> consumer, T parameter) {
        try {
            consumer.acceptChecked(parameter);
        } catch (Throwable ex) { }
    }
    
    // supplier
    
    
    public static <T> Optional<T> call(ThrowableSupplier<T> supplier) {
        try {
            return Optional.ofNullable(supplier.getChecked());
        } catch (Throwable ex) {
            return Optional.empty();
        }
    }
    
    // function
    
    public static <T, R> Optional<R> call(
            ThrowableFunction<T, R> function, T parameter) {
        
        try {
            return Optional.ofNullable(function.apply(parameter));
        } catch (Throwable ex) {
            return Optional.empty();
        }
    }
    
}