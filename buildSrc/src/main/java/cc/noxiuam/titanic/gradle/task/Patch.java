package cc.noxiuam.titanic.gradle.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.gradle.api.Action;
import org.gradle.api.Task;
import org.gradle.api.UncheckedIOException;

import cc.noxiuam.titanic.gradle.TitanicGradlePlugin;
import cc.noxiuam.titanic.gradle.Util;
import codechicken.diffpatch.PatchOperation;
import codechicken.diffpatch.util.PatchMode;

public class Patch implements Action<Task> {

    private final TitanicGradlePlugin plugin;

    public Patch(TitanicGradlePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Task task) {
        try {
            Util.removeDirectory(plugin.getMinecraftSrc());

            // make sure git keeps it
            Files.createDirectories(plugin.getMinecraftSrc());
            Files.createFile(plugin.getMinecraftSrc().resolve(".gitkeep"));

            Path src = plugin.getMcpSrc();
            Path dst = plugin.getMinecraftSrc();
            for (Path file : Util.walk(src)) {
                Path out = dst.resolve(src.relativize(file));

                if (Files.exists(out)) {
                    continue;
                }

                Files.copy(file, out);
            }

            PatchOperation.builder().basePath(plugin.getMinecraftSrc()).patchesPath(plugin.getPatches())
                    .outputPath(plugin.getMinecraftSrc()).mode(PatchMode.OFFSET).build().doPatch();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
