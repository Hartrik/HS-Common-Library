package cz.hartrik.common.reflect;

/**
 * Jednoduché stopky, které slouží hlavně k testování.
 *
 * @version 2015-07-25
 * @author Patrik Harag
 */
public class StopWatch {

    // --- statické metody ---

    /**
     * Vytvoří stopky, spustí metodu {@link Runnable#run()} a vrátí stopky s
     * výsledným časem.
     *
     * @param runnable instance s metodou <code>run</code>
     * @return stopky s časem
     */
    public static final StopWatch measure(Runnable runnable) {
        StopWatch watch = new StopWatch();
        watch.start();

        runnable.run();

        watch.stop();
        return watch;
    }

    /**
     * Spustí metodu {@link Runnable#run()} a výsledný čas v milisekundách
     * vypíše na standardní výstup v určitém formátu. <p>
     * Ukázka:
     * <pre>{@code
     *  StopWatch.measureAndPrint("Načtení: %d ms", () -> {
     *      ...
     *  });}</pre>
     *
     * @param format formát, ve kterém bude výsledný čas zapsán
     * @param runnable instance s metodou <code>run</code>
     * @see String#format(String, Object...)
     */
    public static final void measureAndPrint(String format, Runnable runnable) {
        String text = String.format(format, measure(runnable).getMillis());
        System.out.println(text);
    }

    // --- třída ---

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

    public long getSec()  { return getMillis() / 1000; }
    public long getMin()  { return getSec()    / 60; }
    public long getHour() { return getMin()    / 60; }
    public long getDay()  { return getHour()   / 24; }

}