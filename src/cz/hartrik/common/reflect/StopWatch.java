package cz.hartrik.common.reflect;

/**
 * Jednoduché stopky.
 *
 * @version 2015-06-30
 * @author Patrik Harag
 */
public class StopWatch {

    public static final StopWatch measure(Runnable runnable) {
        StopWatch watch = new StopWatch();
        watch.start();

        runnable.run();

        watch.stop();
        return watch;
    }

    private boolean running = false;
    private long start = 0;
    private long stop = 0;

    /** Spustí stopky */
    public void start() {
        if (!running) {
            running = true;
            start = System.currentTimeMillis();
        }
    }

    /** Zastaví stopky. */
    public void stop() {
        stop = System.currentTimeMillis();
        running = false;
    }

    public long getMillis() {
        return (running ? System.currentTimeMillis() : stop) - start;
    }

    public long getSec()    { return getMillis() / 1000; }
    public long getMin()    { return getSec()    / 60; }
    public long getHour()   { return getMin()    / 60; }
    public long getDay()    { return getHour()   / 24; }

}