package cc.noxiuam.titanic.gradle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class Util {

    public static void removeDirectory(Path dir) throws IOException {
        if (!Files.isDirectory(dir)) {
            return;
        }

        for (Path file : walk(dir, true)) {
            Files.deleteIfExists(file);
        }
    }

    public static Iterable<Path> walk(Path dir) throws IOException {
        return walk(dir, false);
    }

    public static Iterable<Path> walk(Path dir, boolean reverse) throws IOException {
        return Files.walk(dir).sorted(reverse ? Comparator.reverseOrder() : Comparator.naturalOrder())::iterator;
    }
}
