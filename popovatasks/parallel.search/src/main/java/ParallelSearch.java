import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import static java.nio.file.FileVisitResult.*;

@ThreadSafe
public class ParallelSearch {
    private final String root;
    private final String text;
    private final List<String> exts;
    private final SearchEngineText searchEngineText = new SearchEngineText();
    private volatile Boolean finish = false;

    @GuardedBy("this")
    private final Queue<String> files = new LinkedBlockingQueue<>();

    @GuardedBy("this")
    private final List<String> paths = new ArrayList<>();


    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    public void init() throws InterruptedException {

        search.start();
        read.start();
        search.join();
        read.join();
        System.out.println("Search finished");
    }


    public synchronized Set<String> result() {
        return new HashSet<>(this.paths);
    }


    private class SearchEngineText extends SimpleFileVisitor<Path> {

        /**
         * Добавляет файлы в список с нужной дирректорией files
         *
         * @param dir
         * @param attr
         * @return
         */
        @Override
        public FileVisitResult visitFile(Path dir, BasicFileAttributes attr) {
            synchronized (files) {
                for (String ex : exts) {
                    if (dir.getFileName().toString().endsWith("." + ex)) {
                        files.add(root + "\\" + dir.getFileName().toString());
                    }
                }
            }
            return CONTINUE;
        }
    }


    private final Thread search = new Thread() {
        @Override
        public void run() {
            try {
                synchronized (finish) {
                    Files.walkFileTree(Paths.get(root), searchEngineText);
                }
                finish = true;
            } catch (IOException io) {
                System.out.println(io.getCause());
            }
        }
    };


    private final Thread read = new Thread() {
        @Override
        public void run() {
            try {
                synchronized (paths) {
                    while (finish && !files.isEmpty()) {
                        Path path = Paths.get(files.poll());
                        /* и так readAllLines поиск работает и так readAllBytes, просто хотелось проверить.
                        List<String> content =  Files.readAllLines(path, StandardCharsets.UTF_8);
                        for (String pies : content) {
                            if (pies.contains(text)) {
                                paths.add(path.getFileName().toString());
                            }
                        }*/
                        String content = new String(Files.readAllBytes(path));
                        if (content.contains(text)) {
                            paths.add(path.getFileName().toString());
                        }
                    }
                }
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    };
}
