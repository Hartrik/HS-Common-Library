package cz.hartrik.common.io;

import cz.hartrik.common.Exceptions;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Sleduje určitou složku (popřípadě i její podsložky) a při každé změně uvnitř
 * této složky dá zprávu všem posluchačům.
 * 
 * @version 2015-03-30
 * @author Patrik Harag
 */
public class SimpleDirWatcher {

    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;
    private final boolean recursive;

    private final List<Runnable> listeners = new LinkedList<>();
    
    public SimpleDirWatcher(Path dir, boolean recursive) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<>();
        this.recursive = recursive;

        if (recursive)
            registerAll(dir);
        else
            register(dir);
    }

    private void register(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, 
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE, 
                StandardWatchEventKinds.ENTRY_MODIFY);
        
        keys.put(key, dir);
    }

    private void registerAll(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(
                    Path dir, BasicFileAttributes attrs) throws IOException {
                
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
    
    public void processEvents() {
        for (;;) {

            Optional<WatchKey> hide = Exceptions.call(watcher::take);
            if (!hide.isPresent()) return;
            WatchKey key = hide.get();
            
            Path dir = keys.get(key);
            if (dir == null) continue;

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == StandardWatchEventKinds.OVERFLOW)
                    continue;

                @SuppressWarnings("unchecked")
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                
                Path name = ev.context();
                Path child = dir.resolve(name);

                if (recursive && (kind == StandardWatchEventKinds.ENTRY_CREATE)) 
                    if (Files.isDirectory(child, LinkOption.NOFOLLOW_LINKS)) 
                        Exceptions.hide(this::registerAll, child);
            }

            if (!key.reset()) {
                keys.remove(key);

                if (keys.isEmpty()) {
                    break;
                }
            }
            
            callListeners();
        }
    }

    private void callListeners() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }
    
    public void addListener(Runnable runnable) {
        listeners.add(runnable);
    }
    
}